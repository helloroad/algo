package leetcode;

import java.util.ArrayList;

/**
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). 
 * Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * 
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * 
 * It's guaranteed that each city can reach the city 0 after reorder.
 * 
 */



import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinReroute_1466 {

	public static void main(String[] args) {
		
	}
	
	//BFS
    public int minReorder(int n, int[][] connections) {
        Set<String> routes = new HashSet<>();
        Map<Integer, List<Integer>> conn = new HashMap<>();
        for(int[] c: connections){
            routes.add(c[0]+":"+c[1]);
            List<Integer> ls0 = conn.getOrDefault(c[0], new ArrayList<>());
            ls0.add(c[1]);
            conn.put(c[0], ls0);
            
            List<Integer> ls1 = conn.getOrDefault(c[1], new ArrayList<>());
            ls1.add(c[0]);
            conn.put(c[1], ls1);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int result=0;
        while(!q.isEmpty()){
            int curr = q.poll();
            List<Integer> cities = conn.get(curr);
            //System.out.println(cities);
            for(int i: cities){
                if(visited[i]==false){
                    visited[i]=true;
                    if(!routes.contains(i+":"+curr)){
                        result++;
                    }
                    q.offer(i);
                }
            }
        }
        
        return result;
    }
}
