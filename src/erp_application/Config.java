package erp_application;

public class Config {
	public static final String DB_NAME = "skill_erp";
	public static final String USER   = "user_erp";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/"+DB_NAME;
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String CONFIT_RESOURCE_PATH ="resources/config-jdbc.properties";
	
	public static final String[] TABLE_NAME = {"title", "department","employee","post"};
	public static final String[] EXPORT_TABLE_NAME = {"title", "department","employee"};
	
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	
	
	public static String getFilePath(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append(Config.IMPORT_DIR).append(tableName).append(".txt");
		return sb.toString().replace("\\", "/");
	}
}
