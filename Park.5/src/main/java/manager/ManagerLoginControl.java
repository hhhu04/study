package manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ManagerLoginControl
 */
@WebServlet("/ManagerLoginControl")
public class ManagerLoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginControl() {
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
		
		String email = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		System.out.println("로그인서블릿");
		
		String checkID = ManagerLoginConnect.findID(email, pass);
		
		if(checkID.equals(email)) {
		Cookie cookie = new Cookie("ID",email);
		response.addCookie(cookie);
		out.print(checkID);
		}
		
		else {
			out.println(" your email or password check");
		}
		
		out.flush();
		out.close();
		
		doGet(request, response);
	}

}
