package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}	
	public TreeTraversal() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Integer> preorder(TreeNode root){
		
		List<Integer> result = new ArrayList<>();
        if(root==null){
            return result; 
        }		
		TreeNode curr = root;
		
		Stack<TreeNode> stk = new Stack<>();
		
		while(!stk.isEmpty() || curr!=null) {
			
			while(curr!=null) {
				result.add(curr.val);
				stk.push(curr);
				curr = curr.left;
			}
			
			curr = stk.pop();
			
			curr = curr.right;
		}
		
		return result;
	}
	
	
	public List<Integer> inorder(TreeNode root){
		List<Integer> result = new ArrayList<>();
        if(root==null){
            return result; 
        }		
		TreeNode curr = root;
		
		Stack<TreeNode> stk = new Stack<>();
		
		while(!stk.isEmpty() || curr!=null) {
			while(curr!=null) {
				stk.push(curr);
				curr = curr.left;
			}
			
			curr = stk.pop();
			
			result.add(curr.val);
			
			curr = curr.right;
			
		}
		return result;
	}
	
	public List<Integer> postorder(TreeNode root){
		List<Integer> result = new ArrayList<>();
        if(root==null){
            return result; 
        }		
		TreeNode curr = root;
		TreeNode prev = null;
		Stack<TreeNode> stk = new Stack<>();
		
		while(!stk.isEmpty() || curr!=null) {
			
			while(curr!=null) {
				stk.push(curr);
				curr = curr.left;
			}
			
			TreeNode top = stk.peek();
			
			if(top.right!=null && top.right!=prev) {
				curr = top.right;
			}else {
				result.add(top.val);
				prev = top;
				stk.pop();
			}
			
		}
		return result;
	}
	
	public List<Integer> postorder2(TreeNode root){
		List<Integer> result = new LinkedList<>();
        if(root==null){
            return result; 
        }		
		TreeNode curr = root;
		Stack<TreeNode> stk = new Stack<>();
		stk.push(curr);
		while(!stk.isEmpty()) {
			curr = stk.pop();
			result.add(0, curr.val);
			
			if(curr.left!=null) {
				stk.add(curr.left);
			}
			
			if(curr.right !=null) {
				stk.add(curr.right);
			}
			
		}
		
		return result;
	}

}
