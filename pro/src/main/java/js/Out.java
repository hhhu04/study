package js;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Out {
	
	static HashMap<String,String> map = new HashMap<String,String>();
	
	public static void main(String[] args) throws IOException{
		pt("hhhhh@123123","1234");
		output("asd","123");
//		for(String n : map.keySet())
//		{
//			System.out.println(n);
//		}
		
	}
	
	
	public static void output(String a, String b) throws IOException {
		
		BufferedWriter write = new BufferedWriter(new FileWriter("./email.txt"));
		pt(a,b);
		map.put(a, b);
		for(String n : map.keySet())
		{
			write.write(n+" "+map.get(n)+"\n");
		}
		
		write.flush();
		write.close();
		
	}
	
	public static String turn(String a, String b) throws IOException {
		pt(a,b);
		for(String n : map.keySet())
		{
			if(n.equals(a)) return "email :"+n+" pass : "+map.get(n);
		}
		
		
		return null;
	}
	
	public static void pt(String a, String b) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("./email.txt"));
		String rd;
		String[] sp = new String[2];
		String email,pass;
		
		
		while(true)
		{
			rd = read.readLine();
			if(rd == null) break;
			sp = rd.split(" ");
			email = sp[0];
			pass = sp[1];
			map.put(email, pass);
		}
		
		read.close();
	
	}
	
	
}
