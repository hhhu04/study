package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static String[] findID(String id, String pass)  {
		
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
			System.out.println(sql);
			String[] arr=new String[2];
			
			if(rs.next()) {
				String email = rs.getString("user_id");
				String pa = rs.getString("password");
				arr[0] = email;
				arr[1] = pa;
				System.out.println(email);
				System.out.println(pa);
				return arr;
			}
			else {
				System.out.println("아디없음");
				arr[0] = null;
				arr[1] = null;
				return arr;
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
