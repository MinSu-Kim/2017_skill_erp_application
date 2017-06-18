package erp_application.dao;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Department;
import erp_application.jdbc.DBCon;
import erp_application.jdbc.JdbcUtil;

public class ChartDao {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	private static final ChartDao instance = new ChartDao();
	
	private ChartDao(){}
	
	public static ChartDao getInstance() {
		return instance;
	}

/*	private String[] deptTitles;	//부서명	department
	private int [] deptEmpCnt;		//부서별 인원수 department, employee
	private int totalCnt;			//총인원수     employee
*/	
	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
	}

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

	
}
