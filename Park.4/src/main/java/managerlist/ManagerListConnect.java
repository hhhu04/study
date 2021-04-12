package managerlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerListConnect {

	
public static void findID()  {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select manager_grade,manager_id,manager_name,manager_phone from manager ;";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String grade = rs.getString("manager_grade");
					String ID = rs.getString("manager_id");
					String name = rs.getString("manager_name");
					String phone = rs.getString("manager_phone");
					ManagerListControl.arr.add(grade+","+ID+","+name+","+phone );
				}
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
		
	}
	
	
}
