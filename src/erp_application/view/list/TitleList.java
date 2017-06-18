package erp_application.view.list;

import java.awt.Dimension;
import java.util.List;

import javax.swing.SwingConstants;

import erp_application.dao.TitleDao;
import erp_application.dto.Title;

@SuppressWarnings("serial")
public class TitleList extends AbstractList {

	public TitleList(String title) {
		super(title);
	}

	protected String[] getColumns() {
		return new String[]{"번호", "직책"};
	}

	protected String[][] getRows() {
		List<Title> lists = TitleDao.getInstance().selectByAllItems();
		String[][] arTitles = new String[lists.size()][];
		
		for(int i=0; i<arTitles.length; i++){
			arTitles[i] = lists.get(i).toArray();
		}
		return arTitles;
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

}
