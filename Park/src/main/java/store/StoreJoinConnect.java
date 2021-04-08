package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

public class StoreJoinConnect {
	


public static String checkID(String name, String dis,String day) throws NumberFormatException, ParseException {
	
		String days = setting.Ser.date();
		String addDay = setting.Ser.addTime(days, Integer.parseInt(day), 0, 0);
	
		System.out.println("가맹점 가입확인");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="select store_name "
					+ "from store_list where store_list.store_name ='"+name+"';" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("store_name");
				System.out.println(email+"1111");
				return "-1";
			}
			else {
				System.out.println("가입진행");
				return createID(name,dis,days,addDay);
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
		return null;
		
	}
	
	public static String createID(String name,String dis,String day,String exday) {
		System.out.println("가맹점 생성");
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url ="jdbc:mysql://localhost:3306/park?useSSL=false";
			String user ="sample";
			String password ="12345678";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="insert into store_list (store_name,join_store_date,expire_store_date,discount_price,use_discount)"
					+ "value (?,?,?,?,?);" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, day);
			pstmt.setString(3, exday);
			pstmt.setString(4, dis);
			pstmt.setString(5, "0");
			
			int rs = pstmt.executeUpdate();
			if( rs == 1 ){
                System.out.println("가입 성공");
                return "0";
            }
            else{
                System.out.println("가입 실패 ");
                return "-1";
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
