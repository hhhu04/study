package in;

import java.sql.*;

import setting.MemberControl;

public class InConnect {
	
	static setting.MemberControl mem = new MemberControl();
	static setting.Ser ser = new setting.Ser();	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getMember(String carNumber, String time) {
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
			
			String sql ="select car_number,member_status from member where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) return memberUpdate(carNumber, time);
			else return memberInsert(carNumber,time);
			
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
	
	public static String checkMember(String carNumber) {
		System.out.println("checkMember");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select member_status,out_date from member where member.car_number='"+carNumber+"'";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String status = null;;
			if(rs.next()) {
				status = rs.getString("member_status");
			}
			return status;
			
			
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
	
	private static String memberUpdate(String carNumber, String time) {
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
			
//			Statement stmt = con.createStatement();

			if(checkMember(carNumber).equals("nomal")) {
			String sql = " update member set in_date=? ,expected_payment=?, payment_status=?,out_date=? where member.car_number ='"+carNumber+"'";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, "50000");
			pstmt.setString(3, "not");
			pstmt.setString(4, null);
			
			}
			
			else if(checkMember(carNumber).equals("9")) return "9";
			
			else
			{
				String sql = " update member set in_date=?,out_date=? where member.car_number ='"+carNumber+"'";

				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, time);
				pstmt.setString(2, null);
				
				
			}
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 갱신 실패");
                return "-1";
            }
            else{
                System.out.println("데이터 입력 성공 : 갱신 성공");
                return "0";
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
		return null;
	
	}


	public static String memberInsert(String carNumber,String time)  {
		System.out.println("memberInsert");
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();

			String sql = "insert into member(car_number,member_status,"
					+ "join_date,expected_payment,in_date,payment_status) "
					+ "value(?,?,?,?,?,?);";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, carNumber);
			pstmt.setString(2, "nomal");
			pstmt.setString(3, time);
			pstmt.setString(4, "50000");
			pstmt.setString(5, time);
			pstmt.setString(6, "not");
			
			
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 고객 생성 실패");
                return "-1";
            }
            else{
                System.out.println("데이터 입력 성공 : 신규고객 생성");
                mem.arr.put(carNumber,time+"/"+"50000"+"/"+setting.Ser.date());
                return "0";
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
		return null;
	}
	
	public static void logInsert(String carNumber,String time)  {
		System.out.println("입차로그");
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
			pstmt.setString(2, time);
			pstmt.setString(3, "not");
			pstmt.setString(4, "in");
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 입차 로그 생성 실패");
            }
            else{
                System.out.println("데이터 입력 성공 : 입차 로그 생성 성공");
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
	
	public static String checkin(String carNumber) {
		System.out.println("checkin");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select in_out_value from park_logs where park_logs.car_number='"+carNumber+"' order by num desc limit 1";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String status = "no";
			if(rs.next()) {
				status = rs.getString("in_out_value");
			}
			return status;
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "no";
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
