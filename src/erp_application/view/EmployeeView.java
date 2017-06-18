package erp_application.view;


import java.sql.SQLException;

import javax.swing.JOptionPane;

import erp_application.dao.EmployeeDao;
import erp_application.dto.Employee;
import erp_application.view.panel.EmployeePanel;

@SuppressWarnings("serial")
public class EmployeeView extends AbstractView<Employee> {
	
	public EmployeeView(boolean isAdd) {
		super(isAdd);
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
	protected void btnUpdateActionPerformed() {
		try {
			Employee updateEmp = pMain.getObject();
			EmployeeDao.getInstance().updateItem(updateEmp);
			JOptionPane.showMessageDialog(null, "수정 되었습니다.");
			btnAdd.setText("추가");
			pMain.clearObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			pMain.setSelectedTitle();
		} 
	}

	@Override
	protected void btnAddActionPerformed() {
		try {
			Employee addEmp = pMain.getObject();
			EmployeeDao.getInstance().insertItem(addEmp);
			JOptionPane.showMessageDialog(null, "추가 되었습니다.");
			pMain.clearObject();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가  실패하였습니다.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			pMain.setSelectedTitle();
		} 
	}

}
