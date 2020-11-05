package leetcode;

public class DayOfWeek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class Solution {
	    public String dayOfTheWeek(int day, int month, int year) {        
	        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}; 
	        int[] daysOfMonth = new int[] {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334}; 
	        
	        boolean isLeap = isLeapYear(year);
	        int leapYearCount = (year-1-1972)/4;
	        int currentLeapYearOffset = isLeap && month>2?1:0;
	        
	        int dayCount = 5 +
	            day+
	            daysOfMonth[month-1]+
	            ((year-1-1970)*365) +
	            leapYearCount+
	            currentLeapYearOffset;
	        //System.out.println(dayCount);
	        return days[dayCount%7];
	    }
	    public boolean isLeapYear(int year){
	        return (year%4==0 && year%100!=0) || year%400==0;
	    }
	 }

	
}
