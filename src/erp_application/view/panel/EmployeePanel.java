package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import erp_application.Config;
import erp_application.dao.DepartmentDao;
import erp_application.dao.EmployeeDao;
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
	private JSpinner spinnerSalary;
	private JComboBox<Department> cmbDno;
	private JTextField tfAddr;
	private JTextField tfPost;
	private JButton btnPost;
	private JPanel pMain;
	private JLabel lblPic;
	private JButton btnPic;
	private String picPath;
	
	public EmployeePanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));

		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);

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
		tfNo.setText(nextNo());
		pMain.add(tfNo);

		
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

		spinnerSalary = new JSpinner();
		spinnerSalary.setModel(new SpinnerNumberModel(new Integer(2000000), new Integer(1500000), new Integer(5000000), new Integer(100000)));
		
		pMain.add(spinnerSalary);

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
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel pPic = new JPanel();
		panel.add(pPic);
		pPic.setLayout(new BoxLayout(pPic, BoxLayout.Y_AXIS));
		
		lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon(new ImageIcon(Config.IMPORT_DIR+ "\\img\\seohyunjin.jpg").getImage().getScaledInstance(120, 160, Image.SCALE_DEFAULT)));
		pPic.add(lblPic);
		
		btnPic = new JButton("사진 추가");
		btnPic.addActionListener(this);
		btnPic.setMaximumSize(new Dimension(120, 27));
		pPic.add(btnPic);

		List<Title> titleList = TitleDao.getInstance().selectByAllItems();
		for (Title title : titleList) {
			cmbTitle.addItem(title);
		}

		List<Department> lists = DepartmentDao.getInstance().selectByAllItems();
		for (Department dept : lists) {
			cmbDno.addItem(dept);
		}
	}

	public void disableObject() {
		tfNo.setEnabled(false);
		tfName.setEnabled(false);
		cmbTitle.setEnabled(false);
		spinnerSalary.setEnabled(false);
		cmbDno.setEnabled(false);
		tfAddr.setEnabled(false);
		tfPost.setEnabled(false);
		btnPost.setEnabled(false);
	}
	
	@Override
	public void clearObject() {
		tfNo.setText(nextNo());
		tfName.setText("");
		cmbTitle.setSelectedIndex(0);
		spinnerSalary.setModel(new SpinnerNumberModel(new Integer(2000000), new Integer(1500000), new Integer(5000000), new Integer(100000)));
		cmbDno.setSelectedIndex(0);
		tfAddr.setText("");
		tfPost.setText("");
		lblPic.setIcon(null);
	}

	@Override
	public Employee getObject() throws Exception {
		isEmptyCheck(pMain);
		String strEmpNo = tfNo.getText();
		int empNo = Integer.parseInt(strEmpNo.substring(1));
		String empName = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		int salary = (int) spinnerSalary.getValue();
		Department dept = (Department) cmbDno.getSelectedItem();
		String addr = tfAddr.getText().trim();
		String post = tfPost.getText().trim();
		byte[] pic = getImage();
		//"사원번호", "사원명", "직책", "급여", "부서", "우편번호", "주소", "세부 주소"
		return new Employee(empNo, empName, title, salary, dept, post, addr, pic);
	}

	@Override
	public void setObject(Employee obj) {
		tfNo.setText(String.format("E%04d", obj.getEmpNo()));
		tfName.setText(obj.getEmpName());
		cmbTitle.setSelectedItem(obj.getTitle());
		spinnerSalary.setModel(new SpinnerNumberModel(Integer.valueOf(obj.getSalary()), new Integer(1500000), new Integer(5000000), new Integer(100000)));
		cmbDno.setSelectedItem(obj.getDept());
		tfPost.setText(obj.getPost());
		tfAddr.setText(obj.getAddr());
		if (obj.getPic()!=null){
			lblPic.setIcon(new ImageIcon(new ImageIcon(obj.getPic()).getImage().getScaledInstance(120, 160, Image.SCALE_DEFAULT)));
		}else{
			lblPic.setIcon(null);
		}
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
	public String nextNo() {
		return String.format("E%04d",EmployeeDao.getInstance().selectNextNo()+1);
	}

	@Override
	public void setSelectedTitle() {
		tfName.requestFocus();
		tfName.selectAll();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPic) {
			actionPerformedBtnPic(e);
		}
		if (e.getSource() == btnPost) {
			btnPostActionPerformed(e);
		}
	}

	protected void btnPostActionPerformed(ActionEvent e) {
		String doro = JOptionPane.showInputDialog("도로명을 입력하세요", "엉또로");
		List<Address> resList = PostDao.getInstance().selectZipCodeByDoro(doro);
		Address searchAddr = (Address) JOptionPane.showInputDialog(null, "해당주소 선택", "우편번호 검색", JOptionPane.INFORMATION_MESSAGE, null, resList.toArray(), resList.get(0));
		tfPost.setText(searchAddr.getZipCode());
		tfAddr.setText(searchAddr.toString());
		tfAddr.requestFocus();
	}
	
	protected void actionPerformedBtnPic(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(Config.IMPORT_DIR));
		int ret = chooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		picPath = chooser.getSelectedFile().getPath();
		lblPic.setIcon(new ImageIcon(new ImageIcon(picPath).getImage().getScaledInstance(120, 160, Image.SCALE_DEFAULT)));
	}
}
