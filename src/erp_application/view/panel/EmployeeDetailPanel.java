package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_application.Config;
import erp_application.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends JPanel {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField cmbTitle;
	private JTextField spinnerSalary;
	private JTextField cmbDno;
	private JTextField tfAddr;
	private JTextField tfPost;
	private JPanel pMain;
	private JLabel lblPic;

	public EmployeeDetailPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));

		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		lblPic = new JLabel("");
		lblPic.setSize(120, 160);
		lblPic.setIcon(new ImageIcon(new ImageIcon(Config.IMPORT_DIR+ "\\img\\seohyunjin.jpg").getImage().getScaledInstance(120, 160, Image.SCALE_DEFAULT)));
		panel.add(lblPic);

		JPanel pEmp = new JPanel();
		add(pEmp, BorderLayout.CENTER);
		pEmp.setLayout(new BoxLayout(pEmp, BoxLayout.Y_AXIS));

		pMain = new JPanel();
		pEmp.add(pMain);
		pMain.setLayout(new GridLayout(0, 2, 10, 0));

		JLabel lblNo = new JLabel("사원번호");
		pMain.add(lblNo);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);

		tfNo = new JTextField();
		tfNo.setColumns(10);
		tfNo.setEditable(false);
		pMain.add(tfNo);

		
		JLabel lblName = new JLabel("사원명");
		pMain.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);

		tfName = new JTextField();
		tfName.setEditable(false);
		pMain.add(tfName);
		tfName.setColumns(10);

		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblTitle);

		cmbTitle = new JTextField();
		cmbTitle.setEditable(false);
		pMain.add(cmbTitle);

		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblSalary);

		spinnerSalary = new JTextField();
		spinnerSalary.setEditable(false);
		
		pMain.add(spinnerSalary);

		JLabel lblDno = new JLabel("부서");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblDno);

		cmbDno = new JTextField();
		cmbDno.setEditable(false);
		pMain.add(cmbDno);

		JLabel lblPost = new JLabel("우편번호");
		pMain.add(lblPost);
		lblPost.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfPost = new JTextField();
		tfPost.setEditable(false);
		pMain.add(tfPost);
		tfPost.setColumns(5);

		JLabel lblAddr = new JLabel("주소");
		pMain.add(lblAddr);
		lblAddr.setHorizontalAlignment(SwingConstants.RIGHT);

		tfAddr = new JTextField();
		tfAddr.setEditable(false);
		pMain.add(tfAddr);
		tfAddr.setColumns(10);

	}

/*	public void disableObject() {
		tfNo.setEnabled(false);
		tfName.setEnabled(false);
		cmbTitle.setEnabled(false);
		spinnerSalary.setEnabled(false);
		cmbDno.setEnabled(false);
		tfAddr.setEnabled(false);
		tfPost.setEnabled(false);
	}*/
	
	public void setObject(Employee obj) {
		tfNo.setText(String.format("E%04d", obj.getEmpNo()));
		tfName.setText(obj.getEmpName());
		cmbTitle.setText(obj.getTitle().getTitle());
		spinnerSalary.setText(obj.getSalary()+"");
		cmbDno.setText(obj.getDept().getDeptName());
		tfPost.setText(obj.getPost());
		tfAddr.setText(obj.getAddr());
		if (obj.getPic()!=null){
			lblPic.setIcon(new ImageIcon(new ImageIcon(obj.getPic()).getImage().getScaledInstance(120, 160, Image.SCALE_DEFAULT)));
		}else{
			lblPic.setIcon(null);
		}
	}

}
