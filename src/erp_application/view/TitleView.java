package erp_application.view;


import javax.swing.JOptionPane;

import erp_application.Erp_Application;
import erp_application.dao.TitleDao;
import erp_application.dto.Title;
import erp_application.view.panel.TitlePanel;

@SuppressWarnings("serial")
public class TitleView extends AbstractView<Title>{

	public TitleView(String title, boolean isAdd, Erp_Application main) {
		super(title, isAdd, main);
		setSize(300, 150);
	}

	@Override
	protected void createMainPanel() {
		pMain = new TitlePanel();
	}

	@Override
	public void setObject(Title dept) {
		pMain.setObject(dept);
	}

	@Override
	protected void btnUpdateActionPerformed() {
		try {
			Title updateDept = (Title) pMain.getObject();
			TitleDao.getInstance().updateItem(updateDept);
			JOptionPane.showMessageDialog(null, "수정 되었습니다.");
			dispose();
			main.reloadList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 			
	}

	@Override
	protected void btnAddActionPerformed() {
		try {
			Title addDept = (Title) pMain.getObject();
			TitleDao.getInstance().insertItem(addDept);
			JOptionPane.showMessageDialog(null, "추가 되었습니다.");
			dispose();
			main.reloadList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}

}
