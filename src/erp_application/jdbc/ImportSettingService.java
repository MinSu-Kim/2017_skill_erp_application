package erp_application.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import erp_application.Config;

public class ImportSettingService {
	
	public void initSetting() {
		setForeignKeyCheck(0);
		for(String tableName : Config.EXPORT_TABLE_NAME){
			executeImportData(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s character set 'UTF8' fields TERMINATED by ','", Config.getFilePath(tableName), tableName), tableName);
		}
		setForeignKeyCheck(1);	
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

