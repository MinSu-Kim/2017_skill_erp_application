package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.EmployeeDetail;
import erp_application.jdbc.DBCon;
import erp_application.jdbc.JdbcUtil;

public class EmployeeDetailDao implements Dao<EmployeeDetail>{
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	private static final EmployeeDetailDao instance = new EmployeeDetailDao();
	
	private EmployeeDetailDao(){}
	
	public static EmployeeDetailDao getInstance() {
		return instance;
	}

	@Override
	public int insertItem(EmployeeDetail item) {
		return -1;
	}

	@Override
	public int updateItem(EmployeeDetail item) {
		sql = "update employee_detail set post = ?, address = ?, addr_etc = ?, dependent = ?, married = ? where empno=?";
		int res=-1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getPost());
			pstmt.setString(2, item.getAddr());
			pstmt.setString(3, item.getAddr_etc());
			pstmt.setBoolean(4, item.isDependent());
			pstmt.setBoolean(5, item.isMarried());
			pstmt.setInt(6, item.getEmpNO());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public EmployeeDetail selectByItem(int idx) {
		EmployeeDetail empDetail = null;
		sql = "select empno, post, addr, addr_etc, dependent, married from employee_detail where empno = ?";

		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()){
				empDetail = getEmployeeDetail(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return empDetail;
	}

	@Override
	public List<EmployeeDetail> selectByAllItems() {
		List<EmployeeDetail> emps = new ArrayList<>();
		sql = "select empno, post, addr, addr_etc, dependent, married from employee_detail";
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				emps.add(getEmployeeDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return emps;
	}

	private EmployeeDetail getEmployeeDetail(ResultSet rs) throws SQLException {
		return new EmployeeDetail(rs.getInt("empNO"), rs.getString("addr"), rs.getString("addr_etc"), rs.getBoolean("isMarried"), rs.getBoolean("isDependent"), rs.getString("post"), rs.getBytes("pic"));
	}

	@Override
	public void deleteItem(int idx) {
		sql = "delete from getEmployeeDetail where empno=?";
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}		
	}
	
}
