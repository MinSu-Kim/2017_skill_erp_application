package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_application.dao.DepartmentDao;
import erp_application.dao.PostDao;
import erp_application.dao.TitleDao;
import erp_application.dto.Address;
import erp_application.dto.Department;
import erp_application.dto.Employee;
import erp_application.dto.Title;

@SuppressWarnings("serial")
public class EmployeePanel extends AbstractMainPanel<Employee> implements ActionListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle;
	private JTextField tfSalary;
	private JComboBox<Department> cmbDno;
	private JTextField tfAddr;
	private JTextField tfPost;
	private JButton btnPost;

	public EmployeePanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));

		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);

		JPanel pEmp = new JPanel();
		add(pEmp, BorderLayout.CENTER);
		pEmp.setLayout(new BoxLayout(pEmp, BoxLayout.Y_AXIS));

		JPanel pMain = new JPanel();
		pEmp.add(pMain);
		pMain.setLayout(new GridLayout(0, 2, 10, 0));

		JLabel lblNo = new JLabel("사원번호");
		pMain.add(lblNo);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);

		tfNo = new JTextField();
		pMain.add(tfNo);
		tfNo.setColumns(10);
		JLabel lblName = new JLabel("사원명");
		pMain.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);

		tfName = new JTextField();
		pMain.add(tfName);
		tfName.setColumns(10);

		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblTitle);

		cmbTitle = new JComboBox<>();
		pMain.add(cmbTitle);

		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblSalary);

		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		pMain.add(tfSalary);

		JLabel lblDno = new JLabel("부서");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblDno);

		cmbDno = new JComboBox<>();
		pMain.add(cmbDno);

		JLabel lblPost = new JLabel("우편번호");
		pMain.add(lblPost);
		lblPost.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel pPost = new JPanel();
		pMain.add(pPost);
		pPost.setLayout(new BorderLayout(0, 0));

		tfPost = new JTextField();
		pPost.add(tfPost, BorderLayout.CENTER);
		tfPost.setColumns(5);

		btnPost = new JButton("우편번호 검색");
		btnPost.addActionListener(this);
		pPost.add(btnPost, BorderLayout.EAST);

		JLabel lblAddr = new JLabel("주소");
		pMain.add(lblAddr);
		lblAddr.setHorizontalAlignment(SwingConstants.RIGHT);

		tfAddr = new JTextField();
		pMain.add(tfAddr);
		tfAddr.setColumns(10);

		List<Title> titleList = TitleDao.getInstance().selectByAllItems();
		for (Title title : titleList) {
			cmbTitle.addItem(title);
		}

		List<Department> lists = DepartmentDao.getInstance().selectByAllItems();
		for (Department dept : lists) {
			cmbDno.addItem(dept);
		}
	}

	@Override
	public void clearObject() {
		tfNo.setText(nextNo());
		tfName.setText("");
		cmbTitle.setSelectedIndex(0);
		tfSalary.setText("");
		cmbDno.setSelectedIndex(0);
		tfAddr.setText("");
		tfPost.setText("");
	}

	@Override
	public Employee getObject() throws Exception {
		int empNo = Integer.parseInt(tfNo.getText());
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		int salary = Integer.parseInt(tfSalary.getText().trim());
		Department dept = (Department) cmbDno.getSelectedItem();
		String addr = tfAddr.getText().trim();
		String post = tfPost.getText().trim();

		//"사원번호", "사원명", "직책", "급여", "부서", "우편번호", "주소", "세부 주소"
		return new Employee(empNo, empName, title, salary, dept, post, addr);
	}

	@Override
	public void setObject(Employee obj) {
		tfNo.setText(obj.getEmpNo() + "");
		tfName.setText(obj.getEmpName());
		cmbTitle.setSelectedItem(obj.getTitle());
		tfSalary.setText(obj.getSalary() + "");
		cmbDno.setSelectedItem(obj.getDept());
		tfPost.setText(obj.getPost());
		tfAddr.setText(obj.getAddr());
	}

	@Override
	public String nextNo() {
		return null;
	}

	@Override
	public void setSelectedTitle() {
		tfNo.requestFocus();
		tfNo.selectAll();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPost) {
			btnPostActionPerformed(e);
		}
	}

	protected void btnPostActionPerformed(ActionEvent e) {
		String doro = JOptionPane.showInputDialog(null, "도로명을 입력하세요");
		List<Address> resList = PostDao.getInstance().selectZipCodeByDoro(doro);
		Address searchAddr = (Address) JOptionPane.showInputDialog(null, "해당주소 선택", "우편번호 검색",
				JOptionPane.INFORMATION_MESSAGE, null, resList.toArray(), resList.get(0));
		// System.out.println(searchAddr);
		tfPost.setText(searchAddr.getZipCode());
		tfAddr.setText(searchAddr.toString());
		tfAddr.requestFocus();
	}
}
