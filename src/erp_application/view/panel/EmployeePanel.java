package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import erp_application.dao.DepartmentDao;
import erp_application.dao.EmployeeDao;
import erp_application.dao.PostDao;
import erp_application.dao.TitleDao;
import erp_application.dto.Address;
import erp_application.dto.Department;
import erp_application.dto.Employee;
import erp_application.dto.EmployeeDetail;
import erp_application.dto.Title;

@SuppressWarnings("serial")
public class EmployeePanel extends AbstractMainPanel<Employee> implements ActionListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Employee> cmbManager;
	private JTextField tfSalary;
	private JComboBox<Department> cmbDno;
	private JTextField tfAddr;
	private JTextField tfAddrEtc;
	private JTextField tfPost;
	private final ButtonGroup btnGrpMarried = new ButtonGroup();
	private final ButtonGroup btnGrpDependent = new ButtonGroup();
	private JRadioButton rdbtnMarried;
	private JRadioButton rdbtnSingle;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNO;
	private JButton btnPost;
	private JButton btnPic;
	private JLabel lblPicture;
	private String picPath;

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

		JLabel lblManager = new JLabel("매니저");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblManager);

		cmbManager = new JComboBox<>();
		pMain.add(cmbManager);

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

		List<Title> titleList = TitleDao.getInstance().selectByAllItems();
		for (Title title : titleList) {
			cmbTitle.addItem(title);
		}
		List<Employee> mgnList = EmployeeDao.getInstance().selectEmployeeByManager();
		for (Employee mgn : mgnList) {
			cmbManager.addItem(mgn);
		}
		List<Department> lists = DepartmentDao.getInstance().selectByAllItems();
		for (Department dept : lists) {
			cmbDno.addItem(dept);
		}

		JPanel pDetail = new JPanel();
		pEmp.add(pDetail);
		pDetail.setLayout(new GridLayout(0, 2, 10, 0));

		JLabel lblPost = new JLabel("우편번호");
		lblPost.setHorizontalAlignment(SwingConstants.RIGHT);
		pDetail.add(lblPost);

		JPanel pPost = new JPanel();
		pDetail.add(pPost);
		pPost.setLayout(new BorderLayout(0, 0));

		tfPost = new JTextField();
		pPost.add(tfPost, BorderLayout.CENTER);
		tfPost.setColumns(5);

		btnPost = new JButton("우편번호 검색");
		btnPost.addActionListener(this);
		pPost.add(btnPost, BorderLayout.EAST);

		JLabel lblAddr = new JLabel("주소");
		lblAddr.setHorizontalAlignment(SwingConstants.RIGHT);
		pDetail.add(lblAddr);

		tfAddr = new JTextField();
		pDetail.add(tfAddr);
		tfAddr.setColumns(10);

		JLabel lblAddrEtc = new JLabel("세부 주소");
		lblAddrEtc.setHorizontalAlignment(SwingConstants.RIGHT);
		pDetail.add(lblAddrEtc);

		tfAddrEtc = new JTextField();
		tfAddrEtc.setColumns(10);
		pDetail.add(tfAddrEtc);

		JLabel lblMarried = new JLabel("결혼여부");
		lblMarried.setHorizontalAlignment(SwingConstants.RIGHT);
		pDetail.add(lblMarried);

		JPanel pMarried = new JPanel();
		pDetail.add(pMarried);

		rdbtnMarried = new JRadioButton("기혼");
		btnGrpMarried.add(rdbtnMarried);
		pMarried.add(rdbtnMarried);

		rdbtnSingle = new JRadioButton("미혼");
		rdbtnSingle.setSelected(true);
		btnGrpMarried.add(rdbtnSingle);
		pMarried.add(rdbtnSingle);

		JLabel lblDependent = new JLabel("부양가족");
		lblDependent.setHorizontalAlignment(SwingConstants.RIGHT);
		pDetail.add(lblDependent);

		JPanel pDependent = new JPanel();
		pDetail.add(pDependent);

		rdbtnYes = new JRadioButton("유");
		btnGrpDependent.add(rdbtnYes);
		pDependent.add(rdbtnYes);

		rdbtnNO = new JRadioButton("무");
		rdbtnNO.setSelected(true);
		btnGrpDependent.add(rdbtnNO);
		pDependent.add(rdbtnNO);

		JPanel pPicture = new JPanel();
		add(pPicture, BorderLayout.NORTH);
		pPicture.setLayout(new BorderLayout(0, 0));

		JPanel pPicBtn = new JPanel();
		pPicture.add(pPicBtn, BorderLayout.SOUTH);

		btnPic = new JButton("증명사진");
		pPicBtn.add(btnPic);

		JPanel pPicLabel = new JPanel();
		pPicture.add(pPicLabel, BorderLayout.NORTH);

		lblPicture = new JLabel("");
		lblPicture.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pPicLabel.add(lblPicture);
		lblPicture.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblPicture.setSize(new Dimension(100, 150));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setPreferredSize(new Dimension(100, 150));
		btnPic.addActionListener(this);
	}

	@Override
	public void clearObject() {
		tfNo.setText(nextNo());
		tfName.setText("");
		tfSalary.setText("");
		tfAddr.setText("");
		tfAddrEtc.setText("");
		tfPost.setText("");
		lblPicture.setIcon(null);
	}

	@Override
	public Employee getObject() throws Exception {
		int empNo = Integer.parseInt(tfNo.getText());
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = Integer.parseInt(tfSalary.getText().trim());
		Department dept = (Department) cmbDno.getSelectedItem();

		String addr = tfAddr.getText().trim();
		String addr_etc = tfAddrEtc.getText().trim();
		boolean isMarried = false;
		if (rdbtnMarried.isSelected()) {
			isMarried = true;
		}
		boolean isDependent = false;
		if (rdbtnYes.isSelected()) {
			isDependent = true;
		}
		String post = tfPost.getText().trim();

		byte[] pic = getImage();
		EmployeeDetail empDetail = new EmployeeDetail(empNo, addr, addr_etc, isMarried, isDependent, post  , pic );
		return new Employee(empNo, empName, title, manager, salary, dept, empDetail);
	}

	private byte[] getImage() {
		byte[] pic = null;
		File file = new File(picPath);
		try {
			InputStream is = new FileInputStream(file);
			pic = new byte[is.available()];
			is.read(pic);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Override
	public void setObject(Employee obj) {
		tfNo.setText(obj.getEmpNo() + "");
		tfName.setText(obj.getEmpName());
		cmbTitle.setSelectedItem(obj.getTitle());
		cmbManager.setSelectedItem(obj.getManager());
		tfSalary.setText(obj.getSalary() + "");
		cmbDno.setSelectedItem(obj.getDept());

		if (obj.getDetail() != null) {
			EmployeeDetail empDetail = obj.getDetail();
			tfPost.setText(empDetail.getPost());
			tfAddr.setText(empDetail.getAddr());
			tfAddrEtc.setText(empDetail.getAddr_etc());
			rdbtnMarried.setSelected(empDetail.isMarried() ? true : false);
			rdbtnYes.setSelected(empDetail.isDependent() ? true : false);
			lblPicture.setIcon(new ImageIcon(empDetail.getPic()));
		}
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
		if (e.getSource() == btnPic) {
			btnPicActionPerformed(e);
		}
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
		tfAddrEtc.requestFocus();
	}

	protected void btnPicActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		picPath = chooser.getSelectedFile().getPath();
		lblPicture.setIcon(new ImageIcon(picPath));
	}
}
