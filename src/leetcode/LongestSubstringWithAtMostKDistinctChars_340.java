package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.

	Example 1:
	
	Input: s = "eceba", k = 2
	Output: 3
	Explanation: T is "ece" which its length is 3.
	Example 2:
	
	Input: s = "aa", k = 1
	Output: 2
	Explanation: T is "aa" which its length is 2.
 * 
 * 
 * 
 * 
 *
 */



class LongestSubstringWithAtMostKDistinctChars_340 {
	
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        
		int max = 0;
		Map<Character, Integer> charCount = new HashMap<>();
		
		int left = 0;
		int right = 0;
		
		while(right<s.length()){
			char c;
			if(charCount.size()<=k){
				max = Math.max(max, right-left);
				c = s.charAt(right);
				charCount.put(c, charCount.getOrDefault(c, 0)+1);
				right++;
			}else{
				c = s.charAt(left);
				if(charCount.get(c)==1){
					charCount.remove(c);
				}else{
					charCount.put(c, charCount.get(c)-1);
				}
				left++;
			}
		}
		if(charCount.size()<=k){
			max = Math.max(max, right-left);
		}
		return max;
    }
	
}