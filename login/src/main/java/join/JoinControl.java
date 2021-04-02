package join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Control
 */
@WebServlet("/Control")
public class JoinControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connect connect = new Connect();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinControl() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		System.out.println(email+" " +pass);
		String id = connect.findID(email, pass);
		
		if(id.equals(email)) {
		writer.print(id);
		}
		
		if(id.equals("ok")) {
			writer.println("ok");
		}
		
		writer.flush();
		writer.close();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		doGet(request, response);
	}

}
