package erp_application.view;


import javax.swing.JOptionPane;

import erp_application.dao.TitleDao;
import erp_application.dto.Title;
import erp_application.view.panel.TitlePanel;

@SuppressWarnings("serial")
public class TitleView extends AbstractView<Title>{

	public TitleView(boolean isAdd) {
		super(isAdd);
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
			Title addDept = (Title) pMain.getObject();
			TitleDao.getInstance().insertItem(addDept);
			JOptionPane.showMessageDialog(null, "추가 되었습니다.");
			pMain.clearObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			pMain.setSelectedTitle();
		}		
	}

}
