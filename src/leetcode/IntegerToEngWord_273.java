package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IntegerToEngWord_273 {

	public static void main(String[] args) {
		
	}
	
	class Solution {
	    public String numberToWords(int num) {

	        if(num==0){
	            return "Zero";
	        }
	        
	        String[] nums = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
	                                    "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	        

	        Map<Integer, String> tensMap = new HashMap<>();
	        tensMap.put(2, "Twenty");
	        tensMap.put(3, "Thirty");
	        tensMap.put(4, "Forty");
	        tensMap.put(5, "Fifty");
	        tensMap.put(6, "Sixty");
	        tensMap.put(7, "Seventy");
	        tensMap.put(8, "Eighty");
	        tensMap.put(9, "Ninety");
	        
	        String[] thousands = new String[]{"", "Thousand", "Million", "Billion"};
	        
	        List<String> resultList = new LinkedList<>();
	        
	        int curr = num;
	        
	        int thousandsIdx = 0;
	        
	        while(curr>0){
	            
	            int rem = curr%1000;
	            
	            if(rem==0){
	                
	            }else{
	                
	                resultList.add(0, thousands[thousandsIdx]);
	                
	                int hundreds = rem/100;
	                int tens = (rem%100)/10;
	                int ones = rem%10;
	                
	                if(tens==0&& ones==0){
	                    
	                }else if(tens<2){
	                    resultList.add(0, nums[tens*10 + ones]);
	                }else{
	                    if(ones==0){
	                        resultList.add(0, tensMap.get(tens));
	                    }else{
	                        resultList.add(0, tensMap.get(tens) +" " +nums[ones]);
	                    }
	                }

	                if(hundreds>0){
	                    resultList.add(0, nums[hundreds] + " Hundred");
	                }
	                
	                
	            }
	            
	            thousandsIdx++;
	            
	            curr = curr/1000;
	        }
	        
	        String result = String.join(" ", resultList).trim();
	        
	        return result;
	    }
	}	
	
}
