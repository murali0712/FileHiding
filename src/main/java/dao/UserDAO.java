package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDAO {
	public static boolean isExist(String email) throws SQLException{
		Connection conn= MyConnection.getConnection();
		PreparedStatement ps= conn.prepareStatement("select email from users");
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			String em= rs.getString(1);
			if(em.equals(email))
				return true;
		}
		return false;
	}
	
	public static int saveUser(User user) throws SQLException{
		Connection conn= MyConnection.getConnection();
		PreparedStatement ps= conn.prepareStatement("insert into users values(default,?,?)");
		ps.setString(1,user.getName() );
		ps.setString(2, user.getEmail());
		return ps.executeUpdate();
		
	}
}
