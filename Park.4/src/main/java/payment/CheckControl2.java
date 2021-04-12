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
@WebServlet("/CheckControl2")
public class CheckControl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckControl2() {
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
		String ch = PaymentConnect2.getMember(number);
		String[] sp = new String[2];
		sp = ch.split(" ");
		System.out.println(sp[0]);
		
		for(String i:sp) System.out.println(i);
		
		
		if(sp[0].equals(number)) {
			if(sp.length < 2) writer.print("999");
			else if(sp[2].equals("member")) writer.print("member");
			
			else if(setting.MemberControl.carNumber(number)) {
			Cookie cookie = new Cookie("car_number",number);
			Cookie cookie2 = new Cookie("pay",sp[1]);
			response.addCookie(cookie);
			response.addCookie(cookie2);
			System.out.println("차번확인완료");
			writer.print("ok");
			}
			else writer.print("carNum");
		}
		else {
			System.out.println("차번틀림");
			writer.print("fail");
		}
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
