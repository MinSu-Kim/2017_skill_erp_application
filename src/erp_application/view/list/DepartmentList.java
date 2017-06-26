package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import erp_application.Erp_Application;
import erp_application.dao.DepartmentDao;
import erp_application.dto.Department;
import erp_application.view.DepartmentView;

@SuppressWarnings("serial")
public class DepartmentList extends AbstractList {

	public DepartmentList(String title, Erp_Application main) {
		super(title, main);
	}

	@Override
	protected String[] getColumns() {
		return new String[]{"부서번호", "부서명", "위치"};
	}

	@Override
	protected String[][] getRows() {
		List<Department> lists = DepartmentDao.getInstance().selectByAllItems();
		arDatas = new String[lists.size()][];
		
		for(int i=0; i<arDatas.length; i++){
			arDatas[i] = lists.get(i).toArray();
		}
		return arDatas;
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

	@Override
	public Object getSelectedData() {
		String strDno = arDatas[selectedIdx][0].toString();
		int dno = Integer.parseInt(strDno.substring(1));
		return DepartmentDao.getInstance().selectByItem(dno);
	}

	@Override
	protected void updateItem() {
		Department selectItem  = (Department)getSelectedData();
		DepartmentView deptView = new DepartmentView("부서 수정", false, main);
		deptView.setObject(selectItem);
		main.replacePanel(deptView);		
	}

	@Override
	protected void deleteItem() {
		Department selectItem  = (Department)getSelectedData();
		DepartmentDao.getInstance().deleteItem(selectItem.getDeptNo());
		JOptionPane.showMessageDialog(null, selectItem + " 삭제 되었습니다.");
		main.reloadList();		
	}

	@Override
	protected void insertItem() {
		DepartmentView deptView = new DepartmentView("부서 추가", true, main);
		main.replacePanel(deptView);
		table.clearSelection();
	}
}
