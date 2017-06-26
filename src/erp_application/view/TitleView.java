package erp_application.view;


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
	protected void btnUpdateActionPerformed() throws Exception {
		Title updateDept = (Title) pMain.getObject();
		TitleDao.getInstance().updateItem(updateDept);		
	}

	@Override
	protected void btnAddActionPerformed() throws Exception {
		Title addDept = (Title) pMain.getObject();
		TitleDao.getInstance().insertItem(addDept);		
	}

}
