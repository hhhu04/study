package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerJoinConnect {
	


public static String checkID(String id, String pass,String name, String phone) {
		System.out.println("관리자 가입확인");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select manager_id "
					+ "from manager where manager.manager_id ='"+id+"';" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("manager_id");
				System.out.println(email+"1111");
				return email;
			}
			else {
				System.out.println("가입진행");
				createID(id,pass,name,phone);
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
	
	public static String createID(String id,String pass,String name, String phone) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="insert into manager (manager_id,password,manager_name,manager_grade,join_manager_date)"
					+ "value (?,?,?,?,?);" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, setting.Ser.date());
			
			int rs = pstmt.executeUpdate();
			if( rs == 1 ){
                System.out.println("가입 성공");
                return pass;
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
