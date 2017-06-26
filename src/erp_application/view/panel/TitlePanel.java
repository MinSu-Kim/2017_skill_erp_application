package erp_application.view.panel;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import erp_application.dao.TitleDao;
import erp_application.dto.Title;

@SuppressWarnings("serial")
public class TitlePanel extends AbstractMainPanel<Title>{	
	private JTextField tfNo;
	private JTextField tfTitle;
	
	
	public TitlePanel() {
		setLayout(new GridLayout(0, 2, 10, 0));
		JLabel lblNo = new JLabel("번호");
		add(lblNo);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		tfNo.setText(nextNo());
		tfNo.setEditable(false);
		
		JLabel lblTitle = new JLabel("직책");
		add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfTitle = new JTextField();
		add(tfTitle);
		tfTitle.setColumns(10);		
	}

	@Override
	public void clearObject() {
		tfNo.setText(nextNo());
		tfTitle.setText("");		
	}

	@Override
	public Title getObject() throws Exception {
		isEmptyCheck(this);
		int no = Integer.parseInt(tfNo.getText().substring(1));
		String title = tfTitle.getText().trim();
		if (Pattern.matches("[0-9]", String.valueOf(title.charAt(0)))){
			throw new Exception("첫글자는 문자만 가능 합니다.");
		}
		return new Title(no, title);
	}

	@Override
	public String nextNo() {
		return String.format("T%03d", TitleDao.getInstance().selectNextNo()+1);
	}

	@Override
	public void setSelectedTitle() {
		tfTitle.requestFocus();
		tfTitle.selectAll();		
	}

	@Override
	public void setObject(Title item) {
		tfNo.setText(String.format("T%03d", item.getNo()));
		tfTitle.setText(item.getTitle());			
	}

}
