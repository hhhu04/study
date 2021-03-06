package storelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import managerlist.ManagerListControl;

public class StoreListConnect {

	
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
			
			String sql ="select * from store_list ;";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql+"000");
			
			if(rs.next()) {
				rs.previous();
				while(rs.next()) {
					String name = rs.getString("store_name");
					String join = rs.getString("join_store_date");
					String close = rs.getString("expire_store_date");
					String discount = rs.getString("discount_price");
					String use = rs.getString("use_discount");
					StoreListControl.arr.add(name+","+join+","+close+","+discount+","+use);
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
