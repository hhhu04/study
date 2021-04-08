package out;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.InConnect;
import setting.Park;
import setting.ParkControl;

/**
 * Servlet implementation class OutControl
 */
@WebServlet("/OutControl")
public class OutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String car_number = request.getParameter("car_number");
		String in_date = setting.Ser.date();
		System.out.println(car_number+" 출차 서블릿 "+in_date);
		String num = OutConnect.checkPayment(car_number,in_date);
		
		if(num.equals(car_number)) {
			Park park = new Park();
			ParkControl pc = new ParkControl();
			int seat = pc.readSetting();
			pc.writeSetting(1);
			out.print(car_number);
		}
		else if(num.equals("9")) out.print("9");
		else out.print("x");
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
