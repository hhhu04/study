package setting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeMap;

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
	public static void sp() throws ParseException {
		for(String i:arr.keySet())
		{
			sp = arr.get(i).split("/");
			setting.Ser.addTime2(sp[0], 0, 10);
		}
	}

	
	
}
