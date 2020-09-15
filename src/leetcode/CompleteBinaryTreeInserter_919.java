/**
 * 
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 * Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
 * 	CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
 * 	CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
 * 	CBTInserter.get_root() will return the head node of the tree
 *
 */
public class CompleteBinaryTreeInserter_919 {


	    TreeNode root;
	    Queue<TreeNode> lastLevel;
	    public CompleteBinaryTreeInserter_919(TreeNode root) {
	        this.root = root;
	        
	        lastLevel = new LinkedList<>();
	        
	        TreeNode curr = root;
	        lastLevel.offer(curr);
	        
	        while(!lastLevel.isEmpty()){            

	            curr = lastLevel.peek();
	            if(curr.left!=null){
	                lastLevel.offer(curr.left);
	            }else{
	                break;
	            }
	            if(curr.right!=null){
	                lastLevel.offer(curr.right);
	            }else{
	                break;
	            }
	            lastLevel.poll();

	        }
	        
	    }
	    
	    public int insert(int v) {
	        
	        TreeNode n = new TreeNode(v);
	        TreeNode curr = new TreeNode();

	        curr = lastLevel.peek();
	        if(curr.left==null){
	            curr.left = n;
	            lastLevel.offer(n);
	        }else {
	            curr.right = n;
	            lastLevel.offer(n);
	            lastLevel.poll();
	        }

	        return curr.val;
	    }
	    
	    public TreeNode get_root() {
	        return root;
	    }
	

  class TreeNode {
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
	
	
}
