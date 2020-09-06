/**
 * 
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LU
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

   Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.


as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.



For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.

Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * 
 *
 */


//Definition for a Node.
class Node {
 public int val;
 public List<Node> children;

 public Node() {}

 public Node(int _val) {
     val = _val;
 }

 public Node(int _val, List<Node> _children) {
     val = _val;
     children = _children;
 }
};


public class SerializeDeserializeNaryTree_428 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//recursive DFS
	public String serializeDFS(Node root){
		if(root==null){
			return "";
		}
		List<String> list = new ArrayList<>();
		
		serializeHelper(list, root);
		
		return String.join(",", list);
	}	
	public void serializeHelper(List<String> list, Node n){
		if(n==null) {
			return;
		}
		List<Node> children = n.children;
		list.add(n.val + "");
		if(children==null){
			list.add("0");
		}else{
			list.add(children.size()+"");
		}
		for(Node child: children){
			serializeHelper(list, child);
		}
	}	
	//recursive DFS
	public Node deserializeDFS(String data){
		if(data==null || data.length()==0){
			return null;
		}
		
		String[] valArr = data.split(",");
		
		Queue<String> q = new LinkedList<>(Arrays.asList(valArr));
		
		return deserializeHelper(q);
	}	
	
	public Node deserializeHelper(Queue<String> q){
		Node n = new Node(Integer.parseInt(q.poll()));
		int count = Integer.parseInt(q.poll());
		List<Node> children = new ArrayList<>();
		for(int i=0; i< count; i++){
			children.add(deserializeHelper(q));
		}
		n.children = children;
		return n;
	}	
	
	//BFS
	public String serializeBFS(Node root) {
		
		if(root==null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
		Queue<Node> q = new LinkedList<>();
		
		q.offer(root);
		Node curr;
		
		while(!q.isEmpty()) {
			
			curr = q.poll();
			sb.append(curr.val).append(",").append(curr.children.size()).append(",");
			for(Node n: curr.children) {
				q.offer(n);
			}
		}
		
		return sb.toString();
	}
	
    public Node deserializeBFS(String data) {
		if(data==null||data.length()==0) {
			return null;
		}
		
		String[] valArr = data.split(",");
		Queue<String> q = new LinkedList<>(Arrays.asList(valArr));
		Queue<Integer> countQ = new LinkedList<>();
		Node root = new Node(Integer.parseInt(q.poll()));
        root.children = new ArrayList<>();
		Queue<Node> nodeQ = new LinkedList<>();
		nodeQ.offer(root);
        countQ.offer(Integer.parseInt(q.poll()));
		Node curr;
		while(!nodeQ.isEmpty()) {
			curr = nodeQ.poll();
            int count = countQ.poll();
			for(int i=0; i<count; i++) {
				Node child = new Node(Integer.parseInt(q.poll()));
				child.children = new ArrayList<>();
                curr.children.add(child);
				nodeQ.offer(child);               
                countQ.offer(Integer.parseInt(q.poll()));
			}
			
		}
		
		return root;        
    }
}
