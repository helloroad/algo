package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

	Example:
	
	Input: 
	
	1 - 0 - 0 - 0 - 1
	|   |   |   |   |
	0 - 0 - 0 - 0 - 0
	|   |   |   |   |
	0 - 0 - 1 - 0 - 0
	
	Output: 6 
	
	Explanation: Given three people living at (0,0), (0,4), and (2,2):
	             The point (0,2) is an ideal meeting point, as the total travel distance 
	             of 2+2+2=6 is minimal. So return 6.
 * 
 * 
 * 
 *
 */

public class BestMeetPoint_296 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//The meet point is the median of all x and median of all y
	//So the distance is std dev of all x to median X and std dev of all y to median Y
	public int minTotalDistance(int[][] grid) {
	    int m = grid.length;
	    int n = grid[0].length;
	    
	    List<Integer> X = new ArrayList<>(m);
	    List<Integer> Y = new ArrayList<>(n);
	    
	    for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
	            if(grid[i][j] == 1){
	                X.add(i);
	                Y.add(j);
	            }
	        }
	    }
	    
	    return getMin(X) + getMin(Y);
	}

	//So the distance is std dev of all x to median X and std dev of all y to median Y
	private int getMin(List<Integer> list){
	    int ret = 0;
	    
	    Collections.sort(list);
	    
	    int i = 0;
	    int j = list.size() - 1;
	    while(i < j){
	    	//use the diff between large and small instead of std dev on each point
	        ret += list.get(j--) - list.get(i++);
	    }
	    
	    return ret;
	}
	
}
