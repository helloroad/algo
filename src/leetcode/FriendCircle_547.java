package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendCircle_547 {

	public FriendCircle_547() {
		// TODO Auto-generated constructor stub
	}


	public int findCircleNum(int[][] M) {
		return useDFS(M);
		//return unionSet(M);
	}


	public int useDFS(int[][] M){

		int len = M.length;

		if(len<=1){
			return len;
		}

		int count = 0;
		boolean[] visited = new boolean[len];

		for(int i=0; i<len; i++){
			if(visited[i]==false){
				count++;
				dfs(M, visited, i);
			}
		}
		return count;
	}


	public void dfs(int[][] M, boolean[] visited, int from){

		int len = M.length;
		visited[from]=true;
		for(int to=0; to<len; to++){
			if(M[from][to]==1 && visited[to]==false){
				dfs(M, visited, to);
			}
		}

	}


	class UnionFind {
		
		class Node {
			int val;
			int rank;
			Node parent;
			public Node (int val) {
				this.val = val;
				parent = this;
				rank = 0;
			}
		}
		
		private Map<Integer, Node> nodeMap;
		
		private int count;
		
		public UnionFind(int n) {
			count = n;
			nodeMap = new HashMap<>();
			for(int i=0; i<n; i++) {
				Node node = new Node(i);
				nodeMap.put(i, node);
			}
		}
		
		public int getCount() {
			return count;
		}
		
		public int findRootNodeVal(int val) {
			return findRootNode(nodeMap.get(val)).val;
		}
		
		public Node findRootNode(Node node) {
			if(node.parent==node) {
				return node;
			}else {
				//compress path
				node.parent = findRootNode(node.parent);
				return node.parent;
			}
		}
		
		public void union(int p, int q) {
			
			Node nP = findRootNode(nodeMap.get(p));
			Node nQ = findRootNode(nodeMap.get(q));
			
			if(nP==nQ) {
				return;
			}
			
			if(nP.rank < nQ.rank) {
				nP.parent = nQ;
			}else if (nP.rank>nQ.rank){
				nQ.parent = nP;
				
			}else {
				nP.rank++;
				nQ.parent = nP;
			}
			count--;
		}
		
	}
	
	public int useUnionFind (int[][] M) {
		int len = M.length;
		
		if(len<2) {
			return len;
		}
		
		UnionFind uf = new UnionFind(len);
		for(int i=0; i<len; i++) {
			
			for(int j=i+1; j<len; j++) {
				if(M[i][j]==1) {
					uf.union(i, j);
				}
			}
			
		}
		
		return uf.getCount();
	}
	

 	public int unionSet(int[][] M){
		List<Set<Integer>> circles = new ArrayList<>();
		int N = M.length;

		if(N<=1){
			return N;
		}

		for(int i=0; i<N; i++){

			for(int j=i+1; j<N; j++){
				List<Set<Integer>> unmergedCircles = new ArrayList<>();
				List<Set<Integer>> circleToMergeA = new ArrayList<>();
				boolean hasA = false;
				boolean hasB = false;
				if(M[i][j]==1){
					for(Set<Integer> s: circles){

						if(s.contains(i) || s.contains(j)){
							circleToMergeA.add(s);
						}else{
							unmergedCircles.add(s);
						}
					}                    

					Set<Integer> newSet = new HashSet<>();
					newSet.add(i);
					newSet.add(j);
					for(Set<Integer> s: circleToMergeA){
						newSet.addAll(s);
					}
					unmergedCircles.add(newSet);
				}else{

					for(Set<Integer> s: circles){
						if(s.contains(i)){
							hasA = true;
						}

						if(s.contains(j)){
							hasB = true;
						}
						unmergedCircles.add(s);
					}      
					if(hasA==false){
						Set<Integer> newSetA = new HashSet<>();
						newSetA.add(i);          
						unmergedCircles.add(newSetA);
					}
					if(hasB==false){
						Set<Integer> newSetB = new HashSet<>();
						newSetB.add(j);          
						unmergedCircles.add(newSetB);                   
					}                     
				}

				circles = unmergedCircles;
			}

		}

		return circles.size();
	}

}
