package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipListImpl_1206 {

	public SkipListImpl_1206() {
		// TODO Auto-generated constructor stub
	}
	
	
	public class Skiplist {
	
	    List<Node> sentinels;
	    Random rand;
	    
	    public  Skiplist() {
	        sentinels = new ArrayList<>();
	        Node sentinel = new Node(Integer.MIN_VALUE);
	        sentinel.right = new Node(Integer.MAX_VALUE);
	        sentinels.add(sentinel);
	        rand = new Random();
	    }
	    
	    public boolean search(int target) {
	        Node curr = sentinels.get(sentinels.size()-1);
	        
	        while(curr!=null){
	            if(curr.val<target){
	                if(curr.right!=null && curr.right.val<=target){
	                    curr = curr.right;
	                }else {
	                    //System.out.println("move down: " +curr.val);
	                    curr = curr.down;
	                }
	            }else if(curr.val==target){
	                return true;
	            }else{
	                //System.out.println(curr.val);
	                return false;
	            }
	
	        }
	        
	        return false;
	    }
	    
	    public void add(int num) {
	        Node curr = sentinels.get(0);
	        
	        while(curr.right!=null && curr.right.val<num){
	            curr = curr.right;
	        }
	        
	        Node n = new Node(num);
	        n.right = curr.right;
	        n.left = curr;
	        curr.right.left = n;
	        curr.right = n;
	        
	        Node prevLvlNode = n;
	        int lvl = 0;
	        while(rand.nextBoolean()){
	            lvl++;
	            
	            if(sentinels.size()>lvl){
	                curr = sentinels.get(lvl);
	            }else{
	                curr = new Node(Integer.MIN_VALUE);
	                curr.right = new Node(Integer.MAX_VALUE);
	                curr.down = sentinels.get(lvl-1);
	                sentinels.add(curr);
	            }
	            
	            while(curr.right!=null && curr.right.val<num){
	                curr = curr.right;
	            }
	            
	            n = new Node(num);
	            n.right = curr.right;
	            n.left = curr;
	            n.down = prevLvlNode;
	            prevLvlNode = n;
	            curr.right.left = n;
	            curr.right = n;            
	        }
	        //print("add "+ num);
	    }
	    
	    public boolean erase(int num) {
	        Node curr = sentinels.get(sentinels.size()-1);
	        
	        while(curr!=null){
	            if(curr.val<num){
	                if(curr.right!=null && curr.right.val <= num){
	                    curr = curr.right;
	                }else{
	                    curr = curr.down;
	                }
	            }else if(curr.val==num){
	                break;
	            }else{
	                return false;
	            }
	        }    
	        if(curr==null){
	            return false;
	        }
	        while(curr!=null){
	            curr.left.right= curr.right;
	            curr.right.left = curr.left;
	            curr = curr.down;
	        }
	        if(num==17){
	            //print("delete 17");
	        }
	        return true;
	    }
	    
	    
	    public class Node {
	        int val;
	        Node left, right, down;
	        public Node (int val){
	            this.val = val;
	        }
	    }
	    
	    public void print(String str){
	        System.out.println(str);
	        StringBuilder sb;
	        for(int i=sentinels.size()-1; i>=0; i--){
	            Node curr = sentinels.get(i);
	            sb = new StringBuilder();
	            while(curr!=null){
	                sb.append(curr.val + "->"); 
	                curr = curr.right;
	            }
	            System.out.println(sb.toString());
	        }
	    }
	    
	}

}
