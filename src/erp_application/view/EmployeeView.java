package erp_application.view;


import erp_application.Erp_Application;
import erp_application.dao.EmployeeDao;
import erp_application.dto.Employee;
import erp_application.view.panel.EmployeePanel;

@SuppressWarnings("serial")
public class EmployeeView extends AbstractView<Employee> {
	
	public EmployeeView(String title, boolean isAdd, Erp_Application main) {
		super(title, isAdd, main);
		setSize(600, 400);
	}

	@Override
	protected void createMainPanel() {
		pMain = new EmployeePanel();		
	}

	@Override
	public void setObject(Employee emp) {
		pMain.setObject(emp);
	}

	@Override
	protected void btnUpdateActionPerformed() throws Exception{
		Employee updateEmp = pMain.getObject();
		EmployeeDao.getInstance().updateItem(updateEmp);
	}

	@Override
	protected void btnAddActionPerformed() throws Exception{
		Employee addEmp = pMain.getObject();
		EmployeeDao.getInstance().insertItem(addEmp);
	}

}
