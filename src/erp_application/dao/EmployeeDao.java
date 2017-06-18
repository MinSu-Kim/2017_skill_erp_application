package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Department;
import erp_application.dto.Employee;
import erp_application.dto.EmployeeDetail;
import erp_application.dto.Title;
import erp_application.jdbc.DBCon;
import erp_application.jdbc.JdbcUtil;

public class EmployeeDao implements Dao<Employee> {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	private static final EmployeeDao instance = new EmployeeDao();

	private EmployeeDao() {
	}

	public static EmployeeDao getInstance() {
		return instance;
	}

	@Override
	public int insertItem(Employee item) throws SQLException {

		int res = -1;
		try {
			DBCon.getConnection().setAutoCommit(false);
			sql = "insert into Employee values(?, ?, ?, ?, ?, ?)";
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, item.getEmpNo());
			pstmt.setString(2, item.getEmpName());
			pstmt.setInt(3, item.getTitle().getNo());
			pstmt.setInt(4, item.getManager().getEmpNo());
			pstmt.setInt(5, item.getSalary());
			pstmt.setInt(6, item.getDept().getDeptNo());
			res = pstmt.executeUpdate();
/*			rollback test
 * 			res = -1;
			if (res == -1) throw new SQLException();*/
			
			EmployeeDetail itemDetail = item.getDetail();
			sql = "insert into employee_detail values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, itemDetail.getEmpNO());
			pstmt.setString(2, itemDetail.getPost());
			pstmt.setString(3, itemDetail.getAddr());
			pstmt.setString(4, itemDetail.getAddr_etc());
			pstmt.setBoolean(5, itemDetail.isDependent());
			pstmt.setBoolean(6, itemDetail.isMarried());
			pstmt.setBytes(7, itemDetail.getPic());
			res = pstmt.executeUpdate();
			DBCon.getConnection().commit();
		} catch (SQLException e) {
			try {
				DBCon.getConnection().rollback();
			} catch (SQLException e1) {
				throw new SQLException(e);
			}
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public int updateItem(Employee item) {
		sql = "update employee set empname = ?, title = ?, manager = ?, salary = ?, dno = ? where empno=?";
		int res = -1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getEmpName());
			pstmt.setInt(2, item.getTitle().getNo());
			pstmt.setInt(3, item.getManager().getEmpNo());
			pstmt.setInt(4, item.getSalary());
			pstmt.setInt(5, item.getDept().getDeptNo());
			pstmt.setInt(6, item.getEmpNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public Employee selectByItem(int idx) {
		Employee emp = null;
		sql = "select empno, empname, title, manager, salary, dno from employee where empno = ?";

		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				emp = getEmployee(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return emp;
	}

	@Override
	public List<Employee> selectByAllItems() {
		List<Employee> emps = new ArrayList<Employee>();
		sql = "select empno, empname, title, manager, salary, dno from employee";
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emps.add(getEmployee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return emps;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		Title title = TitleDao.getInstance().selectByItem(rs.getInt("title"));
		Department dept = DepartmentDao.getInstance().selectByItem(rs.getInt("dno"));
		return new Employee(rs.getInt("empno"), rs.getString("empname"), title, new Employee(rs.getInt("manager")), rs.getInt("salary"), dept);
	}

	@Override
	public void deleteItem(int idx) {
		sql = "delete from employee where empno=?";
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

	public List<Employee> selectEmployeeByManager() {
		sql = "select * from employee where title in (1, 2,3)";
		List<Employee> emps = new ArrayList<Employee>();
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emps.add(getEmployee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return emps;
	}

}
