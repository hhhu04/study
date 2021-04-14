package payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setting.Park;
import setting.ParkControl;


/**
 * Servlet implementation class PaymentControl
 */
@WebServlet("/PaymentControl2")
public class PaymentControl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String check;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentControl2() {
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
		String money = request.getParameter("money");
		String price = request.getParameter("price");
		
		
		setting.ParkControl pk = new ParkControl();
		setting.Park park = new Park();
		int mo = ParkControl.readMoney();
		System.out.println(mo);
		int mon=0;
		
			
			
		if(dis.equals("") && !money.equals("")) {
			mon = Integer.parseInt(money)-Integer.parseInt(price);
			ParkControl.writeMoney(mon);
			}
		else if(!payment.PaymentConnect2.getStore(dis).equals("xxx"))
		{
			mon = Integer.parseInt(money)-Integer.parseInt(payment.PaymentConnect2.getStore(dis))-Integer.parseInt(price);
			ParkControl.writeMoney(mon);
			}
		 
		
		
		
		String cc="";
		
		if(!dis.equals("")) {
		cc = payment.PaymentConnect2.getStore(dis);
		}
		
		
		if(dis.equals("")) dis = "not";
		if(!dis.equals("")) {
			payment.PaymentConnect2.storeName(dis);
		}
		
		if(!money.equals("")) {
		if(dis.equals("not") || price.equals("")){
			if(Integer.parseInt(price) > Integer.parseInt(money))out.print("!");
		}}
		
		if(mo <= 0) out.print("mo");
		else if(mo-mon < 0) out.print("mo");
		else if(money.equals("0000")) out.print("0000");
		else if(!setting.MemberControl.carNumber(car_number)) out.print("carNum");
		else if(cc.equals("xxx")) out.print("mmm");
		else {
			String num = payment.PaymentConnect2.logInsert(car_number, dis, money);
			if(!dis.equals("not")) out.print("111");
			else if(num.equals("1")) out.print("x");
			else out.print("0");
		}
		
		
		
		doGet(request, response);
	}

}
