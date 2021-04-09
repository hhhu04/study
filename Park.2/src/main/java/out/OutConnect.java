package out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OutConnect {
	
	
	public static String checkPayment(String carNumber,String date) {
		System.out.println("checkPayment");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select payment_status,out_date from member where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String status = null;;
			
			if(rs.next()) {
				System.out.println("000");
				status = rs.getString("payment_status");
				String time = rs.getString("out_date");
				if(time != null) return "9";
				if(!status.equals("not")) {
					return checklogs(carNumber);
				}
				return "x";
			}
			else return "x";
			
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "x";
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
	
	public static String checklogs(String carNumber) {
		System.out.println("checklog");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="update member set out_date=? where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setting.Ser.date());
		
		
		int count = pstmt.executeUpdate();
        if( count == 0 ){
            System.out.println("데이터 입력 실패 : 갱신 실패");
            return "-1";
        }
        else{
            System.out.println("데이터 입력 성공 : 갱신 성공");
            logInsert(carNumber);
            return carNumber;
        }
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "x";
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
	
	public static void logInsert(String carNumber)  {
		System.out.println("출차로그");
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();

			String sql = "insert into park_logs(car_number,"
					+ "in_date,payment_status,in_out_value) "
					+ "value(?,?,?,?);";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, carNumber);
			pstmt.setString(2, setting.Ser.date());
			pstmt.setString(3, "okay");
			pstmt.setString(4, "out");
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 출차 로그 생성 실패");
            }
            else{
                System.out.println("데이터 입력 성공 : 출차 로그 생성 성공");
                memberUpdate(carNumber);
            }
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
	
	
	private static void memberUpdate(String carNumber) {
		System.out.println("출차 맴버갱신");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = " update member set out_date=? where member.car_number ='"+carNumber+"'";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, setting.Ser.date());
			
			
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
