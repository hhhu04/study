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
		String card_number = request.getParameter("number");
		String card = request.getParameter("card");
		String month = request.getParameter("month");
		
		String cc="";
		
		if(!dis.equals("")) {
		cc = payment.PaymentConnect.getStore(dis);
		}
		boolean ch = setting.MemberControl.cardNumber(card_number);
		System.out.println(ch);
		if(dis.equals("")) dis = "not";
		if(!dis.equals("") && !cc.equals("xxx") ) payment.PaymentConnect.storeName(dis);
		if(card_number.equals("0000")) out.print("0000");
		else if(card.equals("==선택==")) out.print("7");
		else if(month.equals("")) out.print("7");
		else if(card.equals("")) out.print("7");
		else if(!setting.MemberControl.carNumber(car_number)) out.print("carNum");
		else if(ch == false) {
			out.print("cardNum");
		}
		else if(cc.equals("xxx")) out.print("mmm");
		else {
			System.out.println(setting.MemberControl.cardNumber(card_number));
			String num = payment.PaymentConnect.logInsert(car_number, dis, card_number);
			if(!dis.equals("not")) out.print("111");
			else if(num.equals("1")) out.print("x");
			else out.print("0");
		}
		
		
		
		doGet(request, response);
	}

}
