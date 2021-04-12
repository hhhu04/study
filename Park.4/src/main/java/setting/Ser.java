package setting;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class Ser {
	
	static Map<String,String> map = new HashMap<>();
	

	public static void main(String[] args) throws ParseException {
		
		String a = date();
		System.out.println(addTime(a, 1, 1, 1));
		
	}
	
	public static String date() {
		Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(dt);
	}
	
	public static String date2() {
		Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd.mm.ss");
        return date.format(dt);
	}
	
	public static String date3() {
		Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("mm");
        return date.format(dt);
	}
	
	public static String date4() {
		Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd");
        return date.format(dt);
	}
	
	
	
	public static String addTime(String date2,int year, int month, int day) throws ParseException {
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date dt = date.parse(date2);
		
		cal.setTime(dt);
		cal.add(Calendar.YEAR, year); 
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE, day);

		
		return date.format(cal.getTime());
	}
	
public static String addDay(String date2,int day) throws ParseException {
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date dt = date.parse(date2);
		
		cal.setTime(dt);
		
		cal.add(Calendar.DATE, day);

		
		return date.format(cal.getTime());
	}
	
	
	
public static String addTime2(String date2,int hou,int min) throws ParseException {
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date dt = date.parse(date2);
		
		cal.setTime(dt);
		cal.add(Calendar.HOUR, hou); 
		cal.add(Calendar.MINUTE, min);

		
		return date.format(cal.getTime());
	}

	public static void prin() {
		for(String n : map.keySet())
		{
			System.out.println(n+"map "+map.get(n));
		}
	}
}
