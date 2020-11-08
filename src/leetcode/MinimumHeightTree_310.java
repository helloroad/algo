package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/**
 * 
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. 
 * In other words, any connected graph without simple cycles is a tree.
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. 
 * When you select a node x as the root, the result tree has height h. 
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * 
 * 
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * 
 * 
 * Key point:
 * 
 * start from leaves and move towards the center of tree.
 * When reaches the center, the center node will have the shortest tree height.
 * 
 * 1. Start from the leaves with only one connection
 * 2. trim the leaves
 * 3. repeat from 1
 * 
 * when there is only one or two nodes left (center nodes), that's the answer
 * 
 * 
 *
 */


public class MinimumHeightTree_310 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	class Solution {
	    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
	        List<Integer> minTrees = new ArrayList<>();
	        
	        if(n==1){
	            minTrees.add(n-1);
	            return minTrees;
	        }
	        
	        Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
	        
	        for(int[] edge: edges){
	            Set<Integer> children = edgeMap.getOrDefault(edge[0], new HashSet<>());
	            children.add(edge[1]);
	            edgeMap.put(edge[0], children);
	            
	            children = edgeMap.getOrDefault(edge[1], new HashSet<>());
	            children.add(edge[0]);
	            edgeMap.put(edge[1], children);            
	        }
	        
	        Queue<Integer> q = new LinkedList<>();
	        
	        for(int node: edgeMap.keySet()){
	            if(edgeMap.get(node).size()==0){
	                minTrees.add(node);
	                return minTrees;
	            }else if (edgeMap.get(node).size()==1){
	                q.offer(node);
	            }
	        }
	        
	        while(!q.isEmpty()){
	            minTrees = new ArrayList<>();
	            
	            int count = q.size();
	            
	            for(int i=0; i<count; i++){
	                int curr = q.poll();
	                minTrees.add(curr);
	                Set<Integer> nodes = edgeMap.get(curr);
	                for(int node: nodes){
	                    edgeMap.get(node).remove(curr);
	                    if(edgeMap.get(node).size()==1){
	                        q.offer(node);
	                    }
	                }
	            }
	        }

	        return minTrees;
	    }
	}
}
