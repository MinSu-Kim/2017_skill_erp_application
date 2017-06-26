package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_application.dao.DepartmentDao;
import erp_application.dto.Department;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbstractMainPanel<Department>{
	private JTextField tfNo;
	private JTextField tfTitle;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JPanel pMain;

	public DepartmentPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		pMain = new JPanel();
		add(pMain);
		pMain.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblNo = new JLabel("부서번호");
		pMain.add(lblNo);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfNo = new JTextField();
		pMain.add(tfNo);
		tfNo.setColumns(10);
		tfNo.setText(nextNo());
		tfNo.setEditable(false);
		
		JLabel lblTitle = new JLabel("부서명");
		pMain.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfTitle = new JTextField();
		pMain.add(tfTitle);
		tfTitle.setColumns(10);
		
		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		pMain.add(tfFloor);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);		
	}
	
	@Override
	public void clearObject() {
		tfNo.setText(nextNo());
		tfTitle.setText("");
		tfFloor.setText("");		
	}

	@Override
	public Department getObject() throws Exception {
		isEmptyCheck(pMain);
		int deptNo = Integer.parseInt(tfNo.getText().substring(1));

		String deptName = tfTitle.getText().trim();
		if (deptName.matches("[0-9]*")){
			throw new Exception("숫자는 불가능 합니다.");
		}
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public String nextNo() {
		return String.format("D%03d",DepartmentDao.getInstance().selectNextNo()+1);
	}

	@Override
	public void setSelectedTitle() {
		tfTitle.requestFocus();
		tfTitle.selectAll();		
	}

	@Override
	public void setObject(Department item) {
		tfNo.setText(String.format("D%03d", item.getDeptNo()));
		tfTitle.setText(item.getDeptName());
		tfFloor.setText(item.getFloor()+"");			
	}

}
