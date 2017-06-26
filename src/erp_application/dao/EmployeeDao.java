package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Department;
import erp_application.dto.Employee;
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
			sql = "insert into Employee values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, item.getEmpNo());
			pstmt.setString(2, item.getEmpName());
			pstmt.setInt(3, item.getTitle().getNo());
			pstmt.setInt(4, item.getSalary());
			pstmt.setInt(5, item.getDept().getDeptNo());
			pstmt.setString(6, item.getPost());
			pstmt.setString(7, item.getAddr());
			pstmt.setBytes(8, item.getPic());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public int updateItem(Employee item) {
		sql = "update employee set empname = ?, title = ?, salary = ?, dno = ?, post=?, address=?, pic=? where empno=?";
		int res = -1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getEmpName());
			pstmt.setInt(2, item.getTitle().getNo());
			pstmt.setInt(3, item.getSalary());
			pstmt.setInt(4, item.getDept().getDeptNo());
			pstmt.setString(5, item.getPost());
			pstmt.setString(6, item.getAddr());
			pstmt.setBytes(7, item.getPic());
			pstmt.setInt(8, item.getEmpNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	public int selectEmployeeByDepartment(Department dept){
		sql = "select count(*) from employee where dno=?";
		int cnt=0;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return cnt;
	}
	
	public int selectEmployeeByTitle(Title title){
		sql = "select count(*) from employee where title=?";
		int cnt=0;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setInt(1, title.getNo());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return cnt;
	}
	
	@Override
	public Employee selectByItem(int idx) {
		Employee emp = null;
		sql = "select empno, empname, title, salary, dno, post, address, pic from employee where empno = ?";

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
		sql = "select empno, empname, title, salary, dno, post, address, pic from employee";
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
		//사원번호", "사원명", "직책", "급여", "부서", "우편번호", "주소", "세부 주소"
		Title title = TitleDao.getInstance().selectByItem(rs.getInt("title"));
		Department dept = DepartmentDao.getInstance().selectByItem(rs.getInt("dno"));
		return new Employee(rs.getInt("empno"), rs.getString("empname"), title, rs.getInt("salary"), dept, rs.getString("post"), rs.getString("address"),rs.getBytes("pic"));
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

	@Override
	public int rowCnt() {
		sql = "select count(*) from employee";
		int cnt = -1;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return cnt;
	}

	@Override
	public int selectNextNo() {
		sql = "select Max(empno) from employee";
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
