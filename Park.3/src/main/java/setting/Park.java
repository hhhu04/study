package setting;

public class Park {
	
	private int remaining;
	private int defult ; //10분요금
	private int day ; // 1일 최대요금
	private int week ; //주 요금제
	private int month ; //월 요금제
	private int month3 ; //3개월 결제
	
	
	
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public int getDefult() {
		return defult;
	}
	public void setDefult(int defult) {
		this.defult = defult;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth3() {
		return month3;
	}
	public void setMonth3(int month3) {
		this.month3 = month3;
	}
	@Override
	public String toString() {
		return "잔여공간" + remaining + "]";
	}
	
	
	
	

}
