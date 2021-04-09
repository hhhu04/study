package payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PaymentControl
 */
@WebServlet("/PaymentControl")
public class PaymentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String check;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentControl() {
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
		String dis = request.getParameter("dis");
		String number = request.getParameter("number");
		String card = request.getParameter("card");
		String month = request.getParameter("month");
		
		if(dis.equals("")) dis = "not";
		if(!dis.equals("")) payment.PaymentConnect.storeName(dis);
		
		if(number.equals("0000")) out.print("0000");
		else if(card.equals("==선택==")) out.print("7");
		else if(month.equals("")) out.print("7");
		else if(card.equals("")) out.print("7");
		else if(!setting.MemberControl.carNumber(car_number)) out.print("carNum");
		else if(!setting.MemberControl.cardNumber(number)) out.print("cardNum");
		else {
			String num = payment.PaymentConnect.logInsert(car_number, dis, number);
			if(!dis.equals("")) out.print("111");
			else if(num.equals("1")) out.print("x");
			else out.print("0");
		}
		
		
		
		doGet(request, response);
	}

}
