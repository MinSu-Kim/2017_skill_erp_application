package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import erp_application.Erp_Application;
import erp_application.dao.TitleDao;
import erp_application.dto.Title;
import erp_application.view.TitleView;

@SuppressWarnings("serial")
public class TitleList extends AbstractList {

	public TitleList(String title, Erp_Application main) {
		super(title, main);
	}

	protected String[] getColumns() {
		return new String[]{"번호", "직책"};
	}

	protected String[][] getRows() {
		List<Title> lists = TitleDao.getInstance().selectByAllItems();
		arDatas = new String[lists.size()][];
		
		for(int i=0; i<arDatas.length; i++){
			arDatas[i] = lists.get(i).toArray();
		}
		return arDatas;
	}

	@Override
	protected void reAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1);
		tableSetWidth(100, 200);		
	}

	@Override
	protected void reSize() {
		scrollPane.setPreferredSize(new Dimension(450, 300));		
	}

	@Override
	public Object getSelectedData() {
		String tno = arDatas[selectedIdx][0].toString();
		return TitleDao.getInstance().selectByItem(Integer.parseInt(tno));
	}

	@Override
	protected void updateItem() {
		Title selectItem  = (Title)getSelectedData();
		TitleView titleView = new TitleView("직책 수정", false, main);
		titleView.setObject(selectItem);
		titleView.setVisible(true);				
	}

	@Override
	protected void deleteItem() {
		Title selectItem  = (Title)getSelectedData();
		TitleDao.getInstance().deleteItem(selectItem.getNo());
		JOptionPane.showMessageDialog(null, selectItem + " 삭제 되었습니다.");
		main.reloadList();			
	}

	@Override
	protected void insertItem() {
		TitleView titleView = new TitleView("직책 추가", true, main);
		main.replacePanel(titleView);				
	}

}
