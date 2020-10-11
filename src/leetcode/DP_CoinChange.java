package leetcode;

import java.util.Map;

/*
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * Example:
 * Input: coins = [1,2,5], amount = 11
	Output: 3
	Explanation: 11 = 5 + 5 + 1
 * 
 * Input: coins = [2], amount = 3
	Output: -1
 * 
 */


public class DP_CoinChange {

	public DP_CoinChange() {
		
	}
	
	public int dpBottomUp(int[] coins, int amount) {
		
		if(coins.length==0) {
			return 0;
		}
		
		int[] dp = new int[amount+1];
		
		for(int i=1; i<dp.length; i++) {
			dp[i] = amount+1;
			for(int j=0; j<coins.length; j++) {
				if(i>=coins[j]) {
					dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);	
				}
				
			}
			
		}
		
		return dp[amount]==amount+1?-1: dp[amount];
	}
	
	
	//map is slower than int[] as cache
	public int dpTopDown(int[] coins, int amount) {
		if(coins.length==0) {
			return 0;
		}
		
		
		return topDownArr(coins, new int[amount+1], amount);
		//return topDOwnMap(coins, new HashMap<>(), amount);
	}
	
	public int topDownArr(int[] coins, int[] cache, int rem) {
		
		if(rem<0) {
			return -1;
		}
		
		if(rem==0) {
			return 0;
		}
		
		if(cache[rem]!=0) {
			return cache[rem];
		}
		
		int min = rem +1;
		for(int c: coins) {
			
			int count = topDownArr(coins, cache, rem -c);
			if(count!=-1) {
				min = Math.min(min, count+1);
			}
			
		}
		
		cache[rem] = min==rem+1? -1: min;
		
		return cache[rem];
		
	}
	
	public int topDownMap(int[] coins, Map<Integer, Integer> cache, int rem) {
		
		if(rem<0) {
			return -1;
		}
		
		if(rem==0) {
			return 0;
		}
		
		if(cache.get(rem)!=null) {
			return cache.get(rem);
		}		
		int min = rem+1;
		for(int c: coins) {
			int count = topDownMap(coins, cache, rem-c);
            if(count!=-1){
			    min = Math.min(min, count+1);
            }
		}
		
		cache.put(rem, min==rem+1?-1: min);
		return cache.get(rem);
	}

}
