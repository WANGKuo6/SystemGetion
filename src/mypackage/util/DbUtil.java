package mypackage.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;


/**
 * connecter et deconnecter avec BD
 * @author XR et WK
 *
 */
public class DbUtil {
	
	private String dbUrl = "jdbc:mysql://127.0.0.1:3306/systemgestion?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
	private String dbUser = "root";
	private String dbPassword = "Wangkuo521";
	private String jdbcName = "com.mysql.jdbc.Driver";
	private Connection connection = null;
	public Connection getConnection(){
		try {
			Class.forName(jdbcName);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			//System.out.println("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("failed");
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeCon(){
		if(connection != null)
			try {
				connection.close();
				System.out.println("close success!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbUtil dbUtil = new DbUtil();
		dbUtil.getConnection();
	}

}

