package managerlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManagerListControl
 */
@WebServlet("/ManagerListControl")
public class ManagerListControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<String> arr = new ArrayList<String>();
	public static ArrayList<String> list = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerListControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		arr.clear();
		
		ManagerListConnect.findID();
		
		
		for(int i=0;i<arr.size();i++) {
		
			out.print(arr.get(i)+"/");
		
		}
		System.out.println();
		
		out.flush();
		out.close();
		doGet(request, response);
	}
	
	public static void add() {
		
	}
	

}
