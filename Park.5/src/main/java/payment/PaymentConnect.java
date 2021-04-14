package payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import setting.Ser;

public class PaymentConnect {
	
	static setting.Ser ser = new Ser();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getMember(String carNumber) {
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
			
			String sql ="select car_number,member_status,expected_payment from member where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String car = rs.getString("car_number");
					String member = rs.getString("member_status");
					String expected = rs.getString("expected_payment");
					return car+" "+expected+" "+member;
				}
			}
			
			return "";
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
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
	
	public static String getStore(String name) {
		System.out.println("정산 가맹점체크");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select * from store_list where store_list.store_name='"+name+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			System.out.println("123");
				String sname = rs.getString("store_name");
				String discount = rs.getString("discount_price");
				System.out.println(sname);
				System.out.println(discount);
				return discount;
			}
			else return "xxx";
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "777";
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
	
	public static String logInsert(String number, String dis,String card)  {
		System.out.println("정산로그");
		String list = getMember(number);
		String[] sp = new String[3];
		sp = list.split(" ");
		if(sp[1].equals("0")) return "1";
		
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
			
			pstmt.setString(1, ser.date2().replace(".", ""));
			pstmt.setString(2, sp[0]);
			pstmt.setString(3, sp[1]);
			pstmt.setString(4, "success");
			pstmt.setString(5, ser.date());
			pstmt.setString(6, dis);
			temp = "0";
			
			memberUpdate(number);
			}
			
			else {
				String sql = "insert into payment_logs(payment_number,"
						+ "car_number,price,payment_status,status_comments,payment_date,discount_status) "
						+ "value(?,?,?,?,?,?,?);";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, ser.date2());
				pstmt.setString(2, number);
				pstmt.setString(3, sp[1]);
				pstmt.setString(4, "fail");
				pstmt.setString(5, "limit Excess");
				pstmt.setString(6, ser.date());
				pstmt.setString(7, dis);
				temp = "0000";
			}
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 결제 로그 생성 실패");
                
            }
            else{
                System.out.println("데이터 입력 성공 : 결제 로그 생성 성공");
            }
            
            return temp;
            
        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
            return "fail";
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
            return "fail";
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
		System.out.println("memberUpdate");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			
			String sql = " update member set payment_status=? ,expected_payment=?where member.car_number ='"+carNumber+"'";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "okay");
			pstmt.setString(2, "0");
			
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 갱신 실패");
            }
            else{
                System.out.println("데이터 입력 성공 : 갱신 성공");
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
	
	
	public static void storeName(String name) {
		System.out.println("storecheck");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select use_discount from store_list where store_list.store_name ='"+name+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					String car = rs.getString("use_discount");
					int num = Integer.parseInt(car);
					storeUpdate(name, num);
					
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
	
	
	public static void storeUpdate(String name,int num) {
		System.out.println("storeUpdate");
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			
			String sql = " update store_list set use_discount=? where store_list.store_name ='"+name+"'";

			pstmt = con.prepareStatement(sql);
			int n = num+1;
			
			pstmt.setString(1, Integer.toString(n));
			
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 갱신 실패");
            }
            else{
                System.out.println("데이터 입력 성공 : 갱신 성공");
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
