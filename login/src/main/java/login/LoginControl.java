package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		System.out.println(email+" " +pass+"   123123");
		
		String[] checkID = Connect.findID(email, pass);
		
		String id = checkID[0];
		
		if(checkID[0] != null && checkID[1] != null) {
		Cookie cookie = new Cookie("email",email);
		response.addCookie(cookie);
		writer.print(id);
		}
		
		else {
			writer.println(" your email or password check");
		}
		
		doGet(request, response);
	}

}
