package erp_application.view;


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
	
	protected void btnAddActionPerformed() throws Exception{
		Department addDept = pMain.getObject();
		DepartmentDao.getInstance().insertItem(addDept);
	}

	public void setObject(Department dept){
		pMain.setObject(dept);
	}

	@Override
	protected void createMainPanel() {
		pMain = new DepartmentPanel();
	}

	@Override
	protected void btnUpdateActionPerformed() throws Exception{
		Department updateDept = pMain.getObject();
		DepartmentDao.getInstance().updateItem(updateDept);	
	}
}
