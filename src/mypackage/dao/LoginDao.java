package mypackage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mypackage.model.Login;

public class LoginDao extends BaseDao{
	
	/**
	 * verifier les informations de admin
	 * @param login
	 * @return resultat de verifiration
	 */
	public int login(Login login) {
		String name = login.getLogin();
		String password = login.getPassword();
		String sql = "select count(*) from login where login = '" + name + "' and password = '" + password + "'";
		ResultSet rs = query(sql);		
		int result = -1;
		try {
			if(rs.next()) {
				result = rs.getInt(1);
				//System.out.println(result);
			}
			if(result > 0) {
				return 1;
			}else {
				return 0;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}		
}

