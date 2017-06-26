package erp_application.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import erp_application.Config;



public class ExportSettingService {
	
	public void initSetting() {
		checkBackupDir();
		String sql = "select * from %s";
		for(String tableName : Config.EXPORT_TABLE_NAME){
			executeExportData(sql, tableName);
		}
	}
	
	private void checkBackupDir() {
		File backupDir = new File(Config.EXPORT_DIR);
		if (backupDir.exists()){
			backupDir.delete();
			System.out.printf("%s Delete Success! %n",backupDir.getName());
		}else{
			backupDir.mkdir();
			System.out.printf("%s %smake dir Success! %n",Config.EXPORT_DIR, backupDir.getAbsolutePath());
		}
	}

	public void executeExportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(sql, tableName));
			exportData(rs, tableName);
		} catch (SQLException e) {
			System.out.printf("error %d : %s %n", e.getErrorCode(), e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}
	}
	
	private void saveImage(byte[] byteImg, int empId){
			File file = new File(Config.EXPORT_DIR+"//"+empId+".jpg");
			try {
				OutputStream is = new FileOutputStream(file);
				is.write(byteImg);
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void exportData(ResultSet rs, String tableName) throws UnsupportedEncodingException {
		try {
			StringBuilder sb = new StringBuilder();
			int colCnt = rs.getMetaData().getColumnCount(); // 컬럼의 개수
			int lineCount = 0;
			while (rs.next()) {
				for (int i = 1; i <= colCnt; i++) {
					sb.append(rs.getObject(i) + ","); // 필드사이 구분 [,] 찍어줌
					if(rs.getMetaData().getColumnName(i).equals("pic")){
						saveImage(rs.getBytes(i), (int) rs.getObject(1));
						sb.append(rs.getObject(1)+".jpg");
					}
				}
				sb.replace(sb.length() - 1, sb.length(), ""); // 마지막라인 [,] 제거
				sb.append("\n");
				lineCount++;
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(Config.EXPORT_DIR + tableName + ".txt"))) {
				bw.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.printf("Export Table(%s) %d Rows Success! %n",tableName, lineCount);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}