package erp_application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import erp_application.Erp_Application;
import erp_application.view.panel.AbstractMainPanel;

@SuppressWarnings("serial")
public abstract class AbstractView<T> extends JPanel implements ActionListener {

	protected JButton btnAdd;
	private JButton btnCancel;
	protected AbstractMainPanel<T> pMain;
	protected Erp_Application main;
	
	public AbstractView(String title, boolean isAdd, Erp_Application main) {
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.main = main;
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
			try {
				if (e.getActionCommand().equals("추가")){
					btnAddActionPerformed();
				}else{
					btnUpdateActionPerformed();
				}
				JOptionPane.showMessageDialog(null, e.getActionCommand() + " 되었습니다.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				pMain.setSelectedTitle();
			}
			pMain.clearObject();
			main.reloadList();
		}
	}
	
	protected abstract void btnUpdateActionPerformed() throws Exception;
	protected abstract void btnAddActionPerformed() throws Exception;
	
	protected void btnCancelActionPerformed(){
		setVisible(false);
		reloadList();
	}
	
	protected void reloadList(){
		main.reloadList();
	}
}
