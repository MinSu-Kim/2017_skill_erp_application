package erp_application.dao;

import java.util.Arrays;
import java.util.List;

import erp_application.dto.Chart;
import erp_application.dto.Department;
import erp_application.dto.Title;

public class ChartDao {

	private static final ChartDao instance = new ChartDao();
	
	private ChartDao(){}
	
	public static ChartDao getInstance() {
		return instance;
	}

	public Chart selectByChartDatas(boolean isDept) {
		if (isDept){
			return getDeptChartData();
		}else{
			return getTitleChartData();
		}
	}

	private Chart getTitleChartData() {
		int totalCnt = EmployeeDao.getInstance().rowCnt();//총 인원수
		List<Title> titles = TitleDao.getInstance().selectByAllItems();
		String[] names = new String[titles.size()]; //부서명
		for(int i=0; i<names.length; i++){
			names[i] = titles.get(i).getTitle();
		}
		
		int [] empCnts = getCountByTitle(titles);//직책별 인원수
		System.out.println(Arrays.toString(empCnts));
		
		Chart chart = new Chart();
		chart.setNames(names);
		chart.setEmpCnts(empCnts);
		chart.setTotalCnt(totalCnt);
		return chart;
	}

	private int[] getCountByTitle(List<Title> titles) {
		int [] empCnts = new int[titles.size()];
		for(int i=0; i<empCnts.length; i++){
			empCnts[i]=EmployeeDao.getInstance().selectEmployeeByTitle(titles.get(i));
			System.out.println(empCnts[i]);
		}
		return empCnts;
	}

	private Chart getDeptChartData() {
		List<Department> depts= DepartmentDao.getInstance().selectByAllItems();
		String[] names = new String[depts.size()]; //부서명
		for(int i=0; i<names.length; i++){
			names[i] = depts.get(i).getDeptName();
		}
		 
		int [] empCnts = getCountByDepartment(depts);//부서별 인원수
		
		int totalCnt = EmployeeDao.getInstance().rowCnt();//총 인원수
		
		Chart chart = new Chart();
		chart.setNames(names);
		chart.setEmpCnts(empCnts);
		chart.setTotalCnt(totalCnt);
		return chart;
	}
	


	private int[] getCountByDepartment(List<Department> depts) {
		int [] empCnts = new int[depts.size()];
		for(int i=0; i<empCnts.length; i++){
			empCnts[i]=EmployeeDao.getInstance().selectEmployeeByDepartment(depts.get(i));
		}
		return empCnts;
	}




	
}
