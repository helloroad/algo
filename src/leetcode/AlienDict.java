package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlienDict {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public String alienDict(String[] words) {
		if(words.length==0) {
			return "";
		}
		
		Map<Character, List<Character>> children = new HashMap<>();
		Map<Character, Integer> parentCount = new HashMap<>();
	
		for(String word: words) {
			for(char c: word.toCharArray()) {
				if(!parentCount.containsKey(c)) {
					parentCount.put(c, 0);
				}
				if(!children.containsKey(c)) {
					children.compute(c,  new ArrayList<>());
				}
			}
		}
		
		//find all edges
		for(int i=0; i<words.length-1; i++) {
			String w1 = words[i];
			String w2 = words[i+1];
			//check invalid case
			if(w1.length()>w2.length() && w1.startsWith(w2)) {
				return "";
			}
			int minLen = Math.min(w1.length(), w2.length());
			for(int j=0; i<minLen; i++) {
				if(w1.charAt(j)!=w2.charAt(j)) {
					char c1 = w1.charAt(j);
					char c2 = w2.charAt(j);
					//add c2 to c1's children;
					children.get(c1).add(c2);
					parentCount.put(c2, parentCount.get(c2)+1);
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		for(Character c: parentCount.keySet()) {
			if(parentCount.get(c)==0) {
				q.offer(c);
			}
		}
		
		while(!q.isEmpty()) {
			char c = q.poll();
			sb.append(c);
			
			for(char child: children.get(c)) {
				parentCount.put(child, parentCount.get(child)-1);
				if(parentCount.get(child)==0) {
					q.offer(child);
				}
			}
		}
		
		if(sb.length()<parentCount.size()) {
			return "";
		}else {
			return sb.toString();
		}
	}

}
