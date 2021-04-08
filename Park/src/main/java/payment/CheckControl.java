package payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckControl
 */
@WebServlet("/CheckControl")
public class CheckControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckControl() {
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
		PrintWriter writer = response.getWriter();
		
		String number = request.getParameter("number");
		String ch = PaymentConnect.getMember(number);
		String[] sp = new String[2];
		sp = ch.split(" ");
		System.out.println(sp[0]);
		
		if(sp[0].equals(number)) {
			Cookie cookie = new Cookie("car_number",number);
			Cookie cookie2 = new Cookie("pay",sp[1]);
			response.addCookie(cookie);
			response.addCookie(cookie2);
			System.out.println("차번확인완료");
			writer.print("ok");
		}
		else {
			System.out.println("차번틀림");
			writer.print("fail");
		}
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
