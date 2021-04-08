package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberControl
 */
@WebServlet("/MemberControl")
public class MemberControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberControl() {
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
		String car_number = request.getParameter("car_number");
		String check = request.getParameter("check");
		String card = request.getParameter("card");
		String month = request.getParameter("month");
		String card_number = request.getParameter("number");
		System.out.println(car_number+" "+check+" "+card+" "+month+" "+card_number);
		System.out.println(card_number);
		
		if(card_number.equals("0000")) out.print("0000");
		else if(car_number == null) out.print("7");
		else if(check == null) out.print("7");
		else if(card.equals("==선택==")) out.print("7");
		else if(month.equals("")) out.print("7");
		else if(card_number.equals("")) out.print("7");
		else {
			String n = MemberConnect.getMember(car_number,check,card_number);
			out.print(n);
		}
		
		
		doGet(request, response);
	}

}
