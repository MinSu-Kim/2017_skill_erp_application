package erp_application;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_application.jdbc.DBCon;
import erp_application.jdbc.JdbcUtil;
import erp_setting.Erp_Setting;

@SuppressWarnings("serial")
public class SkillTestMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnApplication;
	private JButton btnSetting;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkillTestMain frame = new SkillTestMain();
					frame.setVisible(true);
					frame.enableBtn();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SkillTestMain() {
		setTitle("ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 0));
		
		btnSetting = new JButton("Erp Setting");
		btnSetting.addActionListener(this);
		contentPane.add(btnSetting);
		
		btnApplication = new JButton("Erp Applicaion");
		btnApplication.setEnabled(false);
		contentPane.add(btnApplication);
	}
	
	public void enableBtn(){
		if (isDBExist()){
			btnApplication.setEnabled(true);
		}
	}
	
	private boolean isDBExist() {
		String sql = "SELECT EXISTS (SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?) AS flag";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, Config.DB_NAME);
			rs = pstmt.executeQuery();
			rs.next();
			res = rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return res;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSetting) {
			actionPerformedBtnSetting(e);
		}
	}
	protected void actionPerformedBtnSetting(ActionEvent e) {
		Erp_Setting erpSetting = new Erp_Setting();
		erpSetting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		erpSetting.setVisible(true);
		
	}
}
