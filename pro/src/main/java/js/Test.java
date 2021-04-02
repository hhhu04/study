package js;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import org.json.simple.JSONObject;



/**
 * Servlet implementation class Test
 */
/**
 * @author cat
 *
 */
@WebServlet("/Test" )
public class Test extends HttpServlet {
	
	static HashMap<String, Object> map = new HashMap<String, Object>();
	static Out in = new Out();
	

	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter out = response.getWriter();
		String serch = request.getParameter("email2");
		if(serch != null)
		{
			out.print(Out.turn(serch, null));
		}
		
	
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/x-json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String val = request.getParameter("email");
		String num = request.getParameter("pass");
		
		  
		  if(val != null) {
			  Out.pt(val,num);
		  System.out.println("ID : "+val);
		  System.out.println("PASSWORD : "+num);
		  Out.output(val, num);
		  
		  }
		 
		  
		 
		  
		doGet(request, response);
	}

}
