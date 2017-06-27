package erp_application.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import erp_application.Config;

public class ImportSettingService {
	
	public void initSetting() {
		setForeignKeyCheck(0);
		for(String tableName : Config.EXPORT_TABLE_NAME){
			executeImportData(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s character set 'UTF8' fields TERMINATED by ','", Config.getFilePath(tableName), tableName), tableName);
		}
		setForeignKeyCheck(1);	
		Map<Integer,String> entrys= loadImage();
		for(Entry<Integer, String> entry : entrys.entrySet()){
			updateImage(getImage(entry.getValue()), entry.getKey());
		}
	}

	private Map<Integer,String> loadImage() {
		String sql = "select empno, left(pic,8) from employee";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Integer,String> maps = new HashMap<>();
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				maps.put(rs.getInt(1), rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maps;
	}

	private byte[] getImage(String fileName) {
		byte[] pic = null;
		File file = new File(Config.IMPORT_DIR+"\\img\\"+fileName);
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
	
	private void updateImage(byte[] imges, int empNo){
		String sql = "update employee set pic=? where empno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setBytes(1, imges);
			pstmt.setInt(2, empNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	protected void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
			System.out.println(sql);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("중복데이터 존재");
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}
	}
	
	public void setForeignKeyCheck(int isCheck){
		PreparedStatement pstmt = null;
		try{
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
}

