package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import erp_application.Erp_Application;
import erp_application.dao.EmployeeDao;
import erp_application.dto.Employee;
import erp_application.view.EmployeeView;

@SuppressWarnings("serial")
public class EmployeeList extends AbstractList {

	public EmployeeList(String title, Erp_Application main) {
		super(title, main);
	}

	protected String[] getColumns() {
		return new String[]{"사원번호", "사원명", "직책", "급여", "부서", "주소"};
	}

	protected String[][] getRows() {
		List<Employee> lists = EmployeeDao.getInstance().selectByAllItems();
		arDatas = new String[lists.size()][];
		
		for(int i=0; i<arDatas.length; i++){
			arDatas[i] = lists.get(i).toArray();
		}
		return arDatas;
	}

	@Override
	protected void reAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(80, 80, 60, 110, 60, 400);		
	}

	@Override
	protected void reSize() {
		scrollPane.setPreferredSize(new Dimension(450, 300));		
	}

	@Override
	public Object getSelectedData() {
		String eno = arDatas[selectedIdx][0].toString();
		int empNo = Integer.parseInt(eno.substring(1));
		return EmployeeDao.getInstance().selectByItem(empNo);
	}

	@Override
	protected void updateItem() {
		Object selectItem  = getSelectedData();
		EmployeeView empView = new EmployeeView("사원 수정", false, main);
		empView.setObject((Employee)selectItem);
		main.replacePanel(empView);
		
	}

	@Override
	protected void insertItem() {
		EmployeeView empView = new EmployeeView("사원 추가", true, main);
		main.replacePanel(empView);
		table.clearSelection();
	}

	@Override
	protected void deleteItem() {
		Employee selectItem  = (Employee)getSelectedData();
		EmployeeDao.getInstance().deleteItem(selectItem.getEmpNo());
		JOptionPane.showMessageDialog(null, selectItem + " 삭제 되었습니다.");
		main.reloadList();
	}
}
