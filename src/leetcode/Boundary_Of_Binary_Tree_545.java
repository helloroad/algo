package leetcode;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. 
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * 
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 * 
 */

public class Boundary_Of_Binary_Tree_545 {

	public Boundary_Of_Binary_Tree_545() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Definition for a binary tree node.
	 * */
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

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {

		List<Integer> result = new ArrayList<>();

		if(root==null){
			return result;
		}

		result.add(root.val);

		searchLeftBoundary(root.left, result);
		searchBottom(root.left, result);
		searchBottom(root.right, result);
		searchRightBoundary(root.right, result);

		return result;
	}

	public void searchLeftBoundary(TreeNode node, List<Integer> result){
		if(node==null){
			return;
		}

		if(node.left==null && node.right==null){
			return;
		}

		result.add(node.val);

		if(node.left==null){
			searchLeftBoundary(node.right, result);
		}else{
			searchLeftBoundary(node.left, result);
		}
	}

	public void searchBottom(TreeNode node, List<Integer> result){

		if(node==null){
			return;
		}

		if(node.left==null && node.right==null){
			result.add(node.val);
			return;
		}

		searchBottom(node.left, result);
		searchBottom(node.right, result);
	}

	public void searchRightBoundary(TreeNode node, List<Integer> result){

		if(node==null){
			return;
		}

		if(node.left==null && node.right==null){
			return;
		}

		if(node.right==null){
			searchRightBoundary(node.left, result);
		}else{
			searchRightBoundary(node.right, result);
		}
		result.add(node.val);
	}


}
