package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Chart;
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


	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
	}

	public Chart selectByChartDatas(boolean isDept) {
		if (isDept){
			return getDeptChartData();
		}else{
			return getTitleChartData();
		}
	}

/*	private String[] names;	//부서명, 직책명
	private int [] empCnts;	//부서별 인원수, 직책별 인원수
	private int totalCnt;	//총인원수
*/	

	private Chart getDeptChartData() {
		int titalCnt = DepartmentDao.getInstance().rowCnt();
		String[] names = DepartmentDao.getInstance().getDeptNames();
		int [] empCnts = new int[names.length];
		//부서별 인원수 (Employee)
		//직책별 인원수 (Employee);
		
		List<Department> depts = new ArrayList<Department>();
		sql = "select deptname from department";

		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				rs.getString("deptname");
				depts.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return null;
	}
	
	private Chart getTitleChartData() {
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
		
		return null;
	}


	
}
