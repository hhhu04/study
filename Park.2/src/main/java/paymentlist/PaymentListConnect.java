package paymentlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import managerlist.ManagerListControl;

public class PaymentListConnect {

	
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
			
			String sql ="select * from payment_logs ;";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String ID = rs.getString("payment_number");
					String car = rs.getString("car_number");
					String price = rs.getString("price");
					String pstatus = rs.getString("payment_status");
					String comment = rs.getString("status_comments");
					String date = rs.getString("payment_date");
					String discount = rs.getString("discount_status");
					String storeid = rs.getString("store_id");
					PaymentListControl.arr.add("거래번호 : "+ID+" 차번 : "+car+" 거래가격 : "+price+
							" 거래상태 : "+pstatus+" 실패사유 : "+comment+" 거래일시 : "+date+" 할인유무 : "+discount+" 사용된할인점 : "+storeid );
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
