package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.SwingConstants;

import erp_application.dao.DepartmentDao;
import erp_application.dto.Department;

@SuppressWarnings("serial")
public class DepartmentList extends AbstractList {

	public DepartmentList(String title) {
		super(title);
	}

	@Override
	protected String[] getColumns() {
		return new String[]{"부서번호", "부서명", "위치"};
	}

	@Override
	protected String[][] getRows() {
		List<Department> lists = DepartmentDao.getInstance().selectByAllItems();
		String[][] arDepts = new String[lists.size()][];
		
		for(int i=0; i<arDepts.length; i++){
			arDepts[i] = lists.get(i).toArray();
		}
		return arDepts;
	}

	@Override
	protected void reAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(100, 200, 100);		
	}

	@Override
	protected void reSize() {
		scrollPane.setPreferredSize(new Dimension(450, 300));		
	}
}
