package price;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import setting.MemberControl;

public class Connect {
	
	public static setting.MemberControl mem = new MemberControl();
	
	public static void getMember() {
		System.out.println("정산 차번체크");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select car_number,expected_payment,in_date from member where member.car_number='nomal'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String car = rs.getString("car_number");
					String member = rs.getString("in_date");
					String expected = rs.getString("expected_payment");
					mem.arr.put(car,member+"/"+expected+"/"+setting.Ser.date());
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
