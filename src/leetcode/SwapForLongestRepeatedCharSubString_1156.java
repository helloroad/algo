package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. 
 * Find the length of the longest substring with repeated characters.
 *
 * Example 1:

	Input: text = "ababa"
	Output: 3
	Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
	Example 2:
	
	Input: text = "aaabaaa"
	Output: 6
	Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
	Example 3:
	
	Input: text = "aaabbaaa"
	Output: 4
	Example 4:
	
	Input: text = "aaaaa"
	Output: 5
	Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
	Example 5:
	
	Input: text = "abcdef"
	Output: 1
	 
	
	Constraints:
	
	1 <= text.length <= 20000
	text consist of lowercase English characters only.
 *
 */
public class SwapForLongestRepeatedCharSubString_1156 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int useWindowFunction(String text) {
		//forward search
		int max = getMaxRep(text);
		//backward search
		max = Math.max(max, getMaxRep(new StringBuilder(text).reverse().toString()));
		
		return max;
	}
	
	public int getMaxRep(String s) {
		if(s.length()<=1) {
			return s.length();
		}
		
		int max = 1;
		
		int[] charCount = new int[26];
		for(char c: s.toCharArray()) {
			charCount[c-'a']++;
		}
		
		int left=0;
		int right = 0;
		
		while(right < s.length()) {
			
			char c = s.charAt(left);
			int count = 0;
			
			//find the repeating char from left pos
			while(right<s.length() && s.charAt(right)==c) {
				right++;
				count++;
			}
			
			if(right==s.length()) {
				max = Math.max(max, count);
			}else {
				int newStart = right;
				
				right++;
				int addon = 0;
				//find the repeating char after the first break
				while(right<s.length() && s.charAt(right)==c) {
					right++;
					addon ++;
				}
				//if total char count is more than first repeating substring and 2nd repeating substring
				//we can use swap to connect the two substring
				if(charCount[c-'a']>count+addon) {
					max = Math.max(max, count+addon+1);
				}else {
					max = Math.max(max, count+addon);
				}
				right = newStart;
				left = right;
			}
		}
		
		return max;
	}

	public int usePosMarker(String text) {
		
		if(text.length()<=1) {
			return text.length();
		}
		int max = 1;
		List<RepeatMarker> markerList = new ArrayList<>();
		int[] charCount = new int[26];
		
		int left=0;
		int right = 0;
		
		while(right<text.length()) {
			char c = text.charAt(left);
			while(right<text.length() && c == text.charAt(right)) {
				charCount[c-'a']++;
				right++;
			}
			
			RepeatMarker m = new RepeatMarker(c, left, right-1);
			markerList.add(m);
			
			left = right;
		}
		
		
		//search the longest repeating substring with plus 1 swap
		//aaabbbaa
		for(RepeatMarker m: markerList) {
			char c= m.c;
			int count = m.end - m.start +1;
			if(charCount[c-'a']>count) {
				count++;
			}
			max = Math.max(max, count);
		}
		
		//search the longest repeating substring with combining two
        //"aaaabaaaa"
        //swap another 'a' to connect two repeating 'a' substring
		for(int i=1; i<markerList.size()-1; i++) {
			RepeatMarker preM = markerList.get(i-1);
			RepeatMarker m = markerList.get(i-1);
			RepeatMarker postM = markerList.get(i+1);
			
			if(preM.c==postM.c && m.end-m.start+1 ==1) {
				int preMCount = preM.end-preM.start +1;
				int postMCount = postM.end - postM.start + 1;
				if(preMCount + postMCount < charCount[preM.c - 'a']) {
					max = Math.max(max, preMCount + postMCount + 1);
				}else {
					max = Math.max(max, preMCount + postMCount);
				}
			}
		}
		
		return max;
		
	}
	
	class RepeatMarker {
		char c;
		int start;
		int end;
		public RepeatMarker(char c, int start, int end) {
			this.c = c;
			this.start = start;
			this.end = end;
		}
	}
}
