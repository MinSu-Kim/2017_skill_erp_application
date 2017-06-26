package erp_application.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import erp_application.dao.EmployeeDao;
import erp_application.dto.Employee;
import erp_application.view.panel.EmployeeDetailPanel;

@SuppressWarnings("serial")
public class EmployeeDetailView extends JPanel implements ActionListener{

	private EmployeeDetailPanel pMain;
	private JButton prevBtn;
	private JButton nextBtn;
	private List<Employee> lists;
	private int curPos;
	private JTable table;
	
	public EmployeeDetailView(String title, JTable table, int selectedIdx) {
		this.curPos = selectedIdx;
		this.table = table;
		lists = EmployeeDao.getInstance().selectByAllItems();
		
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setSize(600, 400);
		pMain = new EmployeeDetailPanel();
		add(pMain);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		prevBtn = new JButton("<");
		prevBtn.addActionListener(this);
		panel.add(prevBtn);
		
		nextBtn = new JButton(">");
		nextBtn.addActionListener(this);
		panel.add(nextBtn);
	}

	public void setObject(Employee emp) {
		pMain.setObject(emp);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextBtn) {
			actionPerformedNextBtn(e);
		}
		if (e.getSource() == prevBtn) {
			actionPerformedPrevBtn(e);
		}
	}
	
	protected void actionPerformedPrevBtn(ActionEvent e) {
		if (curPos>0){
			if (!nextBtn.isEnabled()){
				nextBtn.setEnabled(true);
			}
			curPos = curPos-1;
			if (curPos==0){
				prevBtn.setEnabled(false);
			}
		}
		
		pMain.setObject(lists.get(curPos));
		table.setRowSelectionInterval(curPos, curPos);
		revalidate();
	}
	
	protected void actionPerformedNextBtn(ActionEvent e) {
		if (curPos==lists.size()-1){
			nextBtn.setEnabled(false);
		}
		if (curPos<lists.size()-1){
			if (!prevBtn.isEnabled()){
				prevBtn.setEnabled(true);
			}
			curPos = curPos+1;
			if(curPos==lists.size()-1){
				nextBtn.setEnabled(false);
			}
		}
		pMain.setObject(lists.get(curPos));
		table.setRowSelectionInterval(curPos, curPos);
		revalidate();
	}
}
