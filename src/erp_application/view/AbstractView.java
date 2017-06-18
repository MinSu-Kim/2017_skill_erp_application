package erp_application.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_application.view.panel.AbstractMainPanel;

@SuppressWarnings("serial")
public abstract class AbstractView<T> extends JPanel implements ActionListener {

	protected JButton btnAdd;
	private JButton btnCancel;
	protected AbstractMainPanel<T> pMain;
	
	public AbstractView(boolean isAdd) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		createMainPanel();
		add(pMain, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);

		btnAdd = new JButton();
		if (isAdd){
			btnAdd.setText("추가");
		}else{
			btnAdd.setText("수정");
		}
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
	}

	protected abstract void createMainPanel();
	
	public abstract void setObject(T item);
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed();
		}
		if (e.getSource() == btnAdd) {
			if (e.getActionCommand().equals("추가")){
				btnAddActionPerformed();
			}else{
				btnUpdateActionPerformed();
			}
		}
	}
	
	protected abstract void btnUpdateActionPerformed();
	
	protected void btnCancelActionPerformed(){
		pMain.clearObject();
	}
	
	protected abstract void btnAddActionPerformed();
	
}
