package leetcode;

/*
 * 
 * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

	Train tickets are sold in 3 different ways:
	
	a 1-day pass is sold for costs[0] dollars;
	a 7-day pass is sold for costs[1] dollars;
	a 30-day pass is sold for costs[2] dollars.
	The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
	
	Return the minimum number of dollars you need to travel every day in the given list of days.
 * 
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
	Output: 11
	Explanation: 
	For example, here is one way to buy passes that lets you travel your travel plan:
	On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
	On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
	On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
	In total you spent $11 and covered all the days of your travel.
 * 
 * Note:

	1 <= days.length <= 365
	1 <= days[i] <= 365
	days is in strictly increasing order.
	costs.length == 3
	1 <= costs[i] <= 1000
 * 
 */


public class DP_MinCostOfTicket_983 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public DP_MinCostOfTicket_983() {
		// TODO Auto-generated constructor stub
	}

	
	//dp BottomUp
    public int dpBottomUp(int[] days, int[] costs){
        if(days.length==0){
            return 0;
        }

        int[] ticketRange = new int[]{1, 7, 30};
        
        int[] dp = new int[days.length];
        
        dp[0] = costs[0];
        
        for(int i=0; i<dp.length; i++){
            dp[i] = costs[0] * days.length;
            for(int j=0; j<costs.length; j++){
                int range = ticketRange[j];
                int fromDay = days[i]-range;

                if(fromDay<days[0] || i==0){
                    dp[i] = Math.min(dp[i], costs[j]);
                }else{
                    
                    int idx = i;
                    while(idx>0 && days[idx]>fromDay){
                        idx--;
                    }                   

                    dp[i] = Math.min(dp[i], dp[idx] + costs[j]);
                }
            }           
        }
        
        return dp[dp.length-1];
    }    
    
    
    //dp top down
    public int dpTopDown(int[] days, int[] costs){
        if(days.length==0){
            return 0;
        }
        
        return getMinCost(days, 0, new int[]{1,7,30}, costs, new int[days.length+1]);
        
    }
    
    public int getMinCost(int[] days, int idx, int[] ticketRange, int[] costs, int[] cache){
        if(idx>=days.length){
            return 0;
        }
        
        if(cache[idx]!=0){
            return cache[idx];
        }
        int minCost = Integer.MAX_VALUE;
        for(int i=0; i<costs.length; i++){
        	
            int lastCoveredDay = days[idx]+ticketRange[i] -1;
            
            int newIdx = idx;
            while(newIdx < days.length && lastCoveredDay>=days[newIdx]){
                newIdx++;
            }
            minCost = Math.min(minCost, costs[i] + getMinCost(days, newIdx, ticketRange, costs, cache));
            
        }
        cache[idx] = minCost;
         
        return cache[idx];
    }
    
    //dfs TLE
    int minCost = Integer.MAX_VALUE;
    public int dfs(int[] days, int[] costs){
        
        if(days.length==0){
            return 0;
        }
        
        minCost(days, 0, 0, new int[]{1, 7, 30}, costs);
        
        return minCost;    
    }
    
    public void minCost(int[] days, int idx, int cost, int[] dayRange, int[] costs){
        if(idx==days.length){
            minCost = Math.min(cost, minCost);
            return;
        }
        
        for(int i=0; i<costs.length; i++){
            int newIdx = idx;
            while(newIdx<days.length-1 && days[idx] + dayRange[i]-1>=days[newIdx+1]){
                newIdx++;
            }
            minCost(days, newIdx+1, cost+costs[i], dayRange, costs);
        }
    }
	
	
}
