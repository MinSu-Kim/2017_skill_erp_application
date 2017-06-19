package erp_application.view;


import javax.swing.JOptionPane;

import erp_application.Erp_Application;
import erp_application.dao.DepartmentDao;
import erp_application.dto.Department;
import erp_application.view.panel.DepartmentPanel;

@SuppressWarnings("serial")
public class DepartmentView extends AbstractView<Department> {

	public DepartmentView(String title, boolean isAdd, Erp_Application main) {
		super(title, isAdd, main);
		setSize(300, 200);
	}	
	
	protected void btnAddActionPerformed() {
		try {
			Department addDept = pMain.getObject();
			DepartmentDao.getInstance().insertItem(addDept);
			JOptionPane.showMessageDialog(null, "추가 되었습니다.");
			dispose();
			main.reloadList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			pMain.setSelectedTitle();
		} 
	}

	public void setObject(Department dept){
		pMain.setObject(dept);
	}

	@Override
	protected void createMainPanel() {
		pMain = new DepartmentPanel();
	}

	@Override
	protected void btnUpdateActionPerformed() {
		try {
			Department updateDept = pMain.getObject();
			DepartmentDao.getInstance().updateItem(updateDept);
			JOptionPane.showMessageDialog(null, "수정 되었습니다.");
			dispose();
			main.reloadList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			pMain.setSelectedTitle();
		} 		
	}
}
