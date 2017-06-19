package erp_application;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_application.jdbc.ExportSettingService;
import erp_application.jdbc.ImportSettingService;
import erp_application.view.list.AbstractList;
import erp_application.view.list.DepartmentList;
import erp_application.view.list.EmployeeList;
import erp_application.view.list.TitleList;
import erp_application.view.panel.StateChartPanel;

@SuppressWarnings("serial")
public class Erp_Application extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmResore;
	private AbstractList empList;
	private AbstractList deptList;
	private AbstractList titleList;
	private JMenuItem mntmBackup;
	private JMenuItem mntmExit;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private StateChartPanel chartPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erp_Application frame = new Erp_Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Erp_Application() {
		setTitle("Erp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnManagement = new JMenu("관리");
		menuBar.add(mnManagement);
		
		mntmResore = new JMenuItem("복원");
		mntmResore.addActionListener(this);
		mnManagement.add(mntmResore);
		
		mntmBackup = new JMenuItem("백업");
		mntmBackup.addActionListener(this);
		mnManagement.add(mntmBackup);
		
		mntmExit = new JMenuItem("종료");
		mntmExit.addActionListener(this);
		mnManagement.add(mntmExit);
		
		mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 10, 10));
		

		chartPanel = new StateChartPanel("사원 현황");
		contentPane.add(chartPanel);
		
		empList = new EmployeeList("사원관리");
		contentPane.add(empList);
		
		deptList = new DepartmentList("부서관리");
		contentPane.add(deptList);
		
		titleList = new TitleList("직책관리");
		contentPane.add(titleList);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
		if (e.getSource() == mntmExit) {
			actionPerformedMntmExit(e);
		}
		if (e.getSource() == mntmBackup) {
			actionPerformedMntmBackup(e);
		}
		if (e.getSource() == mntmResore) {
			actionPerformedMntmResore(e);
		}
	}
	
	protected void actionPerformedMntmResore(ActionEvent e) {
		ImportSettingService importSettingService = new ImportSettingService();
		importSettingService.initSetting();
		empList.reload();
		deptList.reload();
		titleList.reload();
		JOptionPane.showMessageDialog(null, "복원 완료~!");
	}
	protected void actionPerformedMntmBackup(ActionEvent e) {
		ExportSettingService exportSettingService = new ExportSettingService();
		exportSettingService.initSetting();
		empList.reload();
		deptList.reload();
		titleList.reload();
		JOptionPane.showMessageDialog(null, "백업 완료~!");
	}
	
	protected void actionPerformedMntmExit(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
		System.exit(0);
	}
	
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
//		chartPanel.setChartData(new String[]{"마케팅","개발", "경영"}, new int[]{18,8,17,}, 43);
	}
}
