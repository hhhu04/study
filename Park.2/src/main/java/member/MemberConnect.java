package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

import setting.MemberControl;
import setting.Ser;

public class MemberConnect {
	
	static setting.MemberControl mc = new MemberControl();
	static setting.Ser time = new Ser();
	
	public static String getMember(String carNumber,String num,String card) {
		System.out.println("getMember");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select car_number,member_status,expire_date from member where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				System.out.println("1");
				rs.previous();
				while(rs.next()) {
					System.out.println("2");
					String car = rs.getString("car_number");
					String mem = rs.getString("member_status");
					String expire = rs.getString("expire_date");
					return memberUpdate(car,mem,expire, num,card);
				}
			}
			else return "3";
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "-1";
		}		
		finally {

			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return "-1";
	}
	
	
	private static String memberUpdate(String carNumber, String mem,String ex,String num,String card) {
		System.out.println("memberUpdate");
		if(ex == null) ex = setting.Ser.date();
		System.out.println(ex);
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			if(num.equals("1")) {
				ex = setting.Ser.addTime(ex,0,0,7);
			}
			else if(num.equals("2")) {
				ex = setting.Ser.addTime(ex,0,1,0);
			}
			else if(num.equals("3")) {
				ex = setting.Ser.addTime(ex,0,3,0);
			}
			System.out.println(ex);
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("1");
			if(mem.equals("nomarl")) {
			String sql = " update member set member_status=?,join_date=?,expire_date=? ,expected_payment=?, payment_status=? where member.car_number ='"+carNumber+"'";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "member");
			pstmt.setString(2, time.date());
			pstmt.setString(3, ex);
			pstmt.setString(4, "0");
			pstmt.setString(5, "member");
	
			}
			
			else
			{
				String sql = " update member set join_date=?,expire_date=?,member_status=?,expected_payment=?, payment_status=? where member.car_number ='"+carNumber+"'";

				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, time.date());
				pstmt.setString(2,  ex);
				pstmt.setString(3,  "member");
				pstmt.setString(4, "0");
				pstmt.setString(5, "member");
			}
			
			int count = pstmt.executeUpdate();
			
			logInsert(carNumber,num,card);
			
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 갱신 실패");
                return "-1";
            }
            else{
                System.out.println("데이터 입력 성공 : 갱신 성공");
                mc.arr.remove(carNumber);
                return "0";
            }
            
            
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return "-1";
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
            return "-1";
        }catch(Exception e) {
        	System.out.println(e);
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
		return "-1";
	
	}
	
	
	public static void logInsert(String number,String num,String card)  {
		System.out.println("정산로그");
		
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();
			String temp;
			if(!card.equals("0000")) {
			String sql = "insert into payment_logs(payment_number,"
					+ "car_number,price,payment_status,payment_date,discount_status) "
					+ "value(?,?,?,?,?,?);";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, setting.Ser.date2());
			pstmt.setString(2, number);
			
			if(num.equals("1")) pstmt.setString(3, "40000");
			else if(num.equals("2")) pstmt.setString(3, "150000");
			else if(num.equals("3")) pstmt.setString(3, "400000");
			
			pstmt.setString(4, "success");
			pstmt.setString(5, setting.Ser.date());
			pstmt.setString(6, "not");
			
			}
			
			else {
				String sql = "insert into payment_logs(payment_number,"
						+ "car_number,price,payment_status,status_comments,payment_date,discount_status) "
						+ "value(?,?,?,?,?,?,?);";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, setting.Ser.date2());
				pstmt.setString(2, number);
				pstmt.setString(3, number);
				pstmt.setString(4, "fail");
				pstmt.setString(5, "limit Excess");
				pstmt.setString(6, setting.Ser.date());
				pstmt.setString(7, "not");
			}
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 결제 로그 생성 실패");
                
            }
            else{
                System.out.println("데이터 입력 성공 : 결제 로그 생성 성공");
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
	
	
}
