package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.SwingConstants;

import erp_application.dao.EmployeeDao;
import erp_application.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeList extends AbstractList {

	public EmployeeList(String title) {
		super(title);
	}

	protected String[] getColumns() {
		return new String[]{"사원번호", "사원명", "직책", "급여", "부서", "주소"};
	}

	protected String[][] getRows() {
		List<Employee> lists = EmployeeDao.getInstance().selectByAllItems();
		String[][] arTitles = new String[lists.size()][];
		
		for(int i=0; i<arTitles.length; i++){
			arTitles[i] = lists.get(i).toArray();
		}
		return arTitles;
	}

	@Override
	protected void reAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4);
		tableCellAlignment(SwingConstants.RIGHT, 3);
//		tableCellAlignment(SwingConstants.LEFT, 5, 6);
		//사원번호", "사원명", "직책", "급여", "부서", "주소", "세부 주소"
		tableSetWidth(80, 80, 60, 110, 60, 400);		
	}

	@Override
	protected void reSize() {
		scrollPane.setPreferredSize(new Dimension(450, 300));		
	}

}
