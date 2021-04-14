package manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.PaymentConnect;

/**
 * Servlet implementation class ManagerControl
 */
@WebServlet("/ManagerJoinControl")
public class ManagerJoinControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerJoinControl() {
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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String mail = request.getParameter("email");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		System.out.println(mail+" "+pass);
		
		if(mail.length() < 5) {out.print("x");}
		else {
		String ID = ManagerJoinConnect.checkID(mail, pass, name, phone);
		
		if(ID.equals(mail)) out.print(mail);
		if(ID.equals(pass)) out.print(pass);
		}
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
