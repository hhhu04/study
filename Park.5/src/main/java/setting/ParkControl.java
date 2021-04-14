package setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class ParkControl {
	
	static Park park = new Park();
	static TreeMap<String,String> map = new TreeMap<>();
	
	public static void main(String[] args) throws IOException {
		readSetting();
	}
	
	public static int readSetting() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("./setting.txt"));
	
		String[] sp = new String[2];
		
		while(true)
		{
			String set = read.readLine();
			if(set == null) break;
			sp = set.split(" ");
			map.put(sp[0], sp[1]);
		}
		
		for(String i:map.keySet()) 
		{
			if(i.equals("remaining")) park.setRemaining(Integer.parseInt(map.get(i)));
			if(i.equals("defult")) park.setDefult(Integer.parseInt(map.get(i)));
			if(i.equals("day")) park.setDay(Integer.parseInt(map.get(i)));
			if(i.equals("week")) park.setWeek(Integer.parseInt(map.get(i)));
			if(i.equals("month")) park.setMonth(Integer.parseInt(map.get(i)));
			if(i.equals("month3")) park.setMonth3(Integer.parseInt(map.get(i)));
		}
		System.out.println("read");
		System.out.println(map.get("remaining"));
		read.close();
		
		return park.getRemaining();
		
	}
	
	public static void writeSetting(int num) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter("./setting.txt"));
		int result = park.getRemaining() + num;
		for(String i:map.keySet())
		{
			if(i.equals("remaining")) map.put(i,Integer.toString(result));
			if(i.equals("defult")) map.put(i,Integer.toString(park.getDefult()));
			if(i.equals("day")) map.put(i,Integer.toString(park.getDay()));
			if(i.equals("week")) map.put(i,Integer.toString(park.getWeek()));
			if(i.equals("month")) map.put(i,Integer.toString(park.getMonth()));
			if(i.equals("month3")) map.put(i,Integer.toString(park.getMonth3()));
		}
		
		for(String i : map.keySet())
		{
			System.out.println(i+" "+map.get(i)+"\n");
			write.write(i+" "+map.get(i)+"\n");
		}
		System.out.println("write");
		write.flush();
		write.close();
		
	}
	
	public static int readMoney() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("./money.txt"));
	
		String[] sp = new String[2];
		
		while(true)
		{
			String set = read.readLine();
			sp = set.split(" ");
			if(sp[0].equals("money")) {
				park.setMoney(Integer.parseInt(sp[1]));
				break;
			}
		}
		return park.getMoney();
	}
	
	
	public static void writeMoney(int num) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter("./money.txt"));
		
		int money = park.getMoney() - num;
		
		write.write("money "+Integer.toString(money));
		write.flush();
		write.close();
		
	}

		
		
		
}
