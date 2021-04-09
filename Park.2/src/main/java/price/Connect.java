package price;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import setting.MemberControl;

public class Connect {
	
	public static setting.MemberControl mem = new MemberControl();
	
	public static void getMember() {
		System.out.println("nomal check");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select car_number,expected_payment,in_date from member where member.member_status='nomal' and member.expected_payment < '20000' and member.expected_payment > '0'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String car = rs.getString("car_number");
					String in_date = rs.getString("in_date");
					String expected = rs.getString("expected_payment");
					mem.arr.put(car,expected);
					memberUpdate(expected,car);
				}
				mem.sp();
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
	
	
	private static void memberUpdate(String num,String car) {
		System.out.println("memberUpdate");
		// TODO Auto-generated method stub
		int pay = Integer.parseInt(num)+2000;
		
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = " update member set expected_payment=? where member.car_number='"+car+"' ";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, Integer.toString(pay));

			
			int count = pstmt.executeUpdate();
          
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
        finally{
            try{
                if( con != null && !con.isClosed()){
                    con.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }catch( SQLException e){
                    e.printStackTrace();
                }
		
            	
        }
	
	}
	
	
}
