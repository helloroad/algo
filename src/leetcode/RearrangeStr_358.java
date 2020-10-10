/**
 * 
 */
package leetcode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

	All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
	
	Example 1:
	
	Input: s = "aabbcc", k = 3
	Output: "abcabc" 
	Explanation: The same letters are at least distance 3 from each other.
	Example 2:
	
	Input: s = "aaabc", k = 3
	Output: "" 
	Explanation: It is not possible to rearrange the string.
	Example 3:
	
	Input: s = "aaadbbcc", k = 2
	Output: "abacabcd"
	Explanation: The same letters are at least distance 2 from each other.
 *
 */
public class RearrangeStr_358 {

	/**
	 * 
	 */
	public RearrangeStr_358() {
		// TODO Auto-generated constructor stub
	}
	
	public String rearrangeString(String s, int k) {
        if(k==0) return s;
        int[] freq = new int[26];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) freq[c - 'a']++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0) {
                pq.add(new int[]{i, freq[i]});
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        
        while(!pq.isEmpty()){
            
            int[] charCount = pq.poll();
            char c = (char)(charCount[0]+'a');
            sb.append(c);
            charCount[1]--;
            
            q.offer(charCount);
            
            if(q.size()>=k){
                charCount = q.poll();
                if(charCount[1]>0){
                    pq.offer(charCount);
                }
            }
            
        }
        
        String outputStr = sb.toString();
        if(outputStr.length()==s.length()){
            return outputStr;
        }else{
            return "";
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
