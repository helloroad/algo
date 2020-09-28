package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 *  Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of the array element between two given indices, inclusive. 
 *  Once all operations have been performed, return the maximum value in the array.
 *  
 *  Example 
	
	n = 10
	queries = [[1,5,3],[4,8,7],[6,9,1]]
	
	Queries are interpreted as follows:
	
	    a b k
	    1 5 3
	    4 8 7
	    6 9 1
	Add the values of  between the indices  and  inclusive:
	
	index->	 1 2 3  4  5 6 7 8 9 10
			[0,0,0, 0, 0,0,0,0,0, 0]
			[3,3,3, 3, 3,0,0,0,0, 0]
			[3,3,3,10,10,7,7,7,0, 0]
			[3,3,3,10,10,8,8,8,1, 0]
			
	The largest value is 10 after all operations are performed.
 * 
 * 
 */
public class Array_Manipulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public class Point {
		int pos;
		boolean isStart;
		int val;
		public Point() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Point(int pos, boolean isStart, int val) {
			super();
			this.pos = pos;
			this.isStart = isStart;
			this.val = val;
		}
		
	}
	
	public Array_Manipulation () {
		
	}
	
	//use sweep line  
	public long arrayManipulation1(int n, int[][] queries) {
		
		long max = 0;
	
		if(queries.length ==0) {
			return max;
		}
		
		List<Point> pList = new ArrayList<>();
		
		for(int[] q: queries) {
			
			int start = q[0];
			int end = q[1];
			int val = q[2];
			
			Point pStart = new Point(start, true, val);
			pList.add(pStart);
			Point pEnd = new Point(end, false, val);
			pList.add(pEnd);
		}
		
		Collections.sort(pList, (p1, p2)->{
			if(p1.pos!=p2.pos) {
				return p1.pos- p2.pos;
			}else {
				
				if(p1.isStart==p2.isStart) {
					return p1.pos-p2.pos;
				}else {
					return p1.isStart==true ? -1:1;
				}
			}
		});
		
		
		long currMax = 0;
		
		for(Point p: pList) {
			if(p.isStart) {
				currMax += p.val;
				max = Math.max(max, currMax);
			}else {
				currMax -= p.val;
			}
			
		}
		
		
		return max;
	}
	
	
	public long arrayManipulation2(int n, int[][] queries) {
		long max = 0;
		if(queries.length ==0) {
			return max;
		}
		
		long[] nums = new long[n+1];
		
		for(int[] q: queries) {
			int start = q[0];
			int end = q[1] +1;
			int val = q[2];
			nums[start] += (long)val;
			
			if(end<=n) {
				nums[end] -= (long)val;
			}
		}
		
		long currSum = 0;
		
		for(int i=1; i< nums.length; i++) {
			currSum += nums[i];
			max = Math.max(max, currSum);
		}
		
		return max;
	}
}
