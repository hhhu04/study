package in;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setting.Park;
import setting.ParkControl;

/**
 * Servlet implementation class Time
 */
@WebServlet("/InControl")
public class InControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String car_number = request.getParameter("car_number");
		String in_date = request.getParameter("in_date");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String car_number = request.getParameter("car_number");
		String in_date = setting.Ser.date();
		System.out.println(car_number+" 입차 서블릿 "+in_date);
		
		String ch = InConnect.checkin(car_number);
		System.out.println(ch);
		
		if(!setting.MemberControl.carNumber(car_number)) out.print("carNum");
		
		else if(ch.equals("out") || ch.equals("no")) {
			String num = InConnect.getMember(car_number,in_date);
			System.out.println(num);
			
			if(num.equals("0")) {
				InConnect.logInsert(car_number, in_date);
				Park park = new Park();
				ParkControl pc = new ParkControl();
				int seat = pc.readSetting();
				pc.writeSetting(-1);
			}
			out.print(num);
		}
		else out.print("-1");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
	}

}
