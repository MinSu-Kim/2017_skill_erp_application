package erp_application.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import erp_application.Config;

public class DBCon {
	private static DBCon instance = new DBCon();
	private static Connection con;
	
	private DBCon() {
		try {
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(Config.URL, Config.USER, Config.PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (instance == null) {
			new DBCon();
		}
		return DBCon.con;
	}
	
	public static void closeConnection(){
		if (con != null){
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
