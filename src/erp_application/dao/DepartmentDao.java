package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Department;
import erp_application.jdbc.DBCon;
import erp_application.jdbc.JdbcUtil;

public class DepartmentDao implements Dao<Department>{
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	private static final DepartmentDao instance = new DepartmentDao();
	
	private DepartmentDao(){}
	
	public static DepartmentDao getInstance() {
		return instance;
	}

	@Override
	public int insertItem(Department item) {
		sql = "insert into department values(?, ?, ?)";
		int res=-1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, item.getDeptNo());
			pstmt.setString(2, item.getDeptName());
			pstmt.setInt(3, item.getFloor());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public int updateItem(Department item) {
		sql = "update department set deptname = ?, floor = ? where deptno=?";
		int res=-1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getDeptName());
			pstmt.setInt(2, item.getFloor());
			pstmt.setInt(3, item.getDeptNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public Department selectByItem(int idx) {
		sql = "select * from department where deptno=?";
		Department dept = null;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()){
				dept = getDepartment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return dept;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
	}

	@Override
	public List<Department> selectByAllItems() {
		List<Department> depts = new ArrayList<Department>();
		sql = "select deptno, deptname, floor from department";

		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				depts.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return depts;
	}

	@Override
	public void deleteItem(int idx) {
		sql = "delete from department where deptno=?";
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

	public int selectNextNo() {
		sql = "select Max(deptno) from Department";
		int nextNo = -1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()){
				nextNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return nextNo;
	}
	
}
