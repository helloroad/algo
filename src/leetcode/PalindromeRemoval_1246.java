/**
 * 
 */
package leetcode;

/**
 * Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. 
 * Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.
 * Return the minimum number of moves needed to remove all numbers from the array.
 *
 *
 *	Example 1:
 *
	Input: arr = [1,2]
	Output: 2
 *
 *	Example 2:
 *
	Input: arr = [1,3,4,1,5]
	Output: 3
	Explanation: Remove [4] then remove [1,3,1] then remove [5].
 *
 */
public class PalindromeRemoval_1246 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int miniMove(int[] arr) {
		
		if(arr.length <2) {
			return arr.length;
		}
		
		
		int len = arr.length;
		
		int[][] dp = new int[len][len];
		
		for(int i=0; i<len; i++) {
			for(int j=i; j>=0; j--) {
				if(i==j) {
					dp[i][j] = 1;
				}else if (i==j+1) {
					if(arr[i]==arr[j]) {
						dp[i][j] =1;
					}else {
						dp[i][j] = 2;
					}
				}else {
					int m1 =i-j +1;
					int m2 =i-j +1;
					int m3 =i-j +1;
					
					if(arr[i]==arr[j]) {
						m1 = dp[i-1][j+1];
					}else {
						m1 = dp[i-1][j+1] + 2;
					}
					
					m2 = Math.min(dp[i][j+1], dp[i-1][j]) +1;
					
					for(int k=j+1; k<i-1; k++) {
						m3 = Math.min(m3, dp[i][k+1]+ dp[k][j]);
					}
					dp[i][j]=Math.min(Math.min(m1, m2), m3);
					
				}
			}
		}
		
		return dp[len-1][0];
	}
	

}
