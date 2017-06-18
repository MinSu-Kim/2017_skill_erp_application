package erp_application.view.panel;

import java.awt.GridLayout;

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
		int no = Integer.parseInt(tfNo.getText());
		String title = tfTitle.getText().trim();
		if (title.matches("[0-9]*")){
			throw new Exception("숫자는 불가능 합니다.");
		}
		return new Title(no, title);
	}

	@Override
	public String nextNo() {
		return String.valueOf(TitleDao.getInstance().selectNextNo()+1);
	}

	@Override
	public void setSelectedTitle() {
		tfTitle.requestFocus();
		tfTitle.selectAll();		
	}

	@Override
	public void setObject(Title item) {
		tfNo.setText(item.getNo()+"");
		tfTitle.setText(item.getTitle());			
	}

}
