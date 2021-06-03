package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static String findID(String id, String pass)  {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/login?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select user_id,password "
					  + "from user where user.user_id ='"+id+"'"
					  + "and user.password = '"+pass+"';" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				String email = rs.getString("user_id");
				String pa = rs.getString("password");
				System.out.println(email+"123");
				System.out.println(pa);
				return email;
			}
			else {
				System.out.println("아디없음");
				return "0";
			}
		
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
		
	}

}
