package memberlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import managerlist.ManagerListControl;

public class MemberListConnect {

	
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
			
			String sql ="select * from member where member.member_status='member' ;";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String car = rs.getString("car_number");
					String join = rs.getString("join_date");
					String expire = rs.getString("expire_date");
					MemberListControl.arr.add("차번 : "+car+" 가입일 : "+join+" 종료일 : "+expire);
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
