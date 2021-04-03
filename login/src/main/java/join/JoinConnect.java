package join;

import java.sql.*;

public class Connect {
	
	public static void main(String[] args) {
	}
	
	public static String findID(String id, String pass) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/login?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select user_id "
					+ "from user where user.user_id ='"+id+"';" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("user_id");
				System.out.println(email);
				return email;
			}
			else {
				System.out.println("가입진행");
				createID(id,pass);
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
		return pass;
		
	}
	
	public static String createID(String id,String pass) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/login?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="insert into user (user_id,password)"
					+ "value (?,?);" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			int rs = pstmt.executeUpdate();
			if( rs == 1 ){
                System.out.println("가입 성공");
                return "ok";
            }
            else{
                System.out.println("가입 실패 ");
                return "not";
            }
			
			
			
		}catch (Exception e) {
			System.out.println("a");
			System.out.println(e.getMessage());
			return "not";
		}		
		finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
	

}
