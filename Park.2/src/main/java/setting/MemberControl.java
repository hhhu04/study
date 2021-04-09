package setting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import price.Connect;

public class MemberControl {
	
	public static TreeMap<String,String> arr = new TreeMap<String,String>();
	static Member mem = new Member();
	static Ser ser = new Ser();
	static String[] sp;
	
	
	public static void setTime() {
		mem.setTime(ser.date());
		System.out.println();
	}
	//mem.arr.put(car,in_date+"/"+expected+"/"+setting.Ser.date());
	public static void sp() {
		for(String i:arr.keySet())
		{
			arr.put(i, arr.get(i)+"2000");
		}
	}

	public static Boolean carNumber(String number) {
	Pattern pattern = Pattern.compile("\\d{3}+[A-Z]+\\d{4}");
	Matcher matcher = pattern.matcher(number);

	return matcher.find();

	}
	
	public static Boolean cardNumber(String number) {
		Pattern pattern = Pattern.compile("\\d{12}");
		Matcher matcher = pattern.matcher(number);

		return matcher.find();
		}
	
	
	
	
	public static int cc() {
		Connect conn = new Connect();
		String mm = Ser.date3();
		System.out.println(mm+"분");
		if(mm.equals("00")) conn.getMember();
		if(mm.equals("10")) conn.getMember();
		if(mm.equals("20")) conn.getMember();
		if(mm.equals("30")) conn.getMember();
		if(mm.equals("40")) conn.getMember();
		if(mm.equals("50")) conn.getMember();
		if(mm.equals("07")) conn.getMember();
		return 1;
	}
	
}
