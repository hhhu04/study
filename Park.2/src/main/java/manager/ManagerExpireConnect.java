package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerExpireConnect {

	public static String expireManager(String id)  {
		System.out.println("해고db");
		
		if(id.equals("123")) return "9";
		
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
			String sql = " delete from manager where manager.manager_id=? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			
			int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패 : 결제 로그 생성 실패");
                return "-1";
            }
            else{
                System.out.println("데이터 입력 성공 : 결제 로그 생성 성공");
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
		return "-1";
	}
	
	
}
