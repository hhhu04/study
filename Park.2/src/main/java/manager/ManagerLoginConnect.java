package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerLoginConnect {

public static String findID(String id, String pass)  {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select manager_id,password "
					  + "from manager where manager.manager_id ='"+id+"'"
					  + "and manager.password = '"+pass+"';" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				String email = rs.getString("manager_id");
				String pa = rs.getString("password");
				System.out.println(id+"123");
				System.out.println(pa);
				return id;
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
