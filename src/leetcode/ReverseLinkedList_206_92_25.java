package leetcode;

/**
 *  206: reverse linked list in recursive and iterative
 *  
 *  92: reverse linked list from m to n
 *  	1<= m<=n<= list length
 * 
 *  25: Reverse Nodes in k-Group
 *  
 *  	Given this linked list: 1->2->3->4->5
 *  
 *  	For k = 2, you should return: 2->1->4->3->5
 *  
 *  	For k = 3, you should return: 3->2->1->4->5
 *  
 *  
*/
public class ReverseLinkedList_206_92_25 {

	public static void main(String[] args) {
		
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if(k==1) {
			return head;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode curr = dummy;
		ListNode preK = null;
		ListNode startK = null;
		
		int count = 0;
		
		while(curr!=null) {
			
			if(count==0) {
				preK = curr;
			}else if(count==1) {
				startK = curr;
			}else if(count==k) {
				ListNode next = curr.next;
				reverseList(startK, curr);
				preK.next = curr;
				startK.next = next;
				
				count=0;
				preK = startK;
				curr = next;
				count++;
				continue;
			}
			curr = curr.next;
			count++;
		}
		
		
		return dummy.next;
	}
	
	
	public void reverseList(ListNode head, ListNode tail) {
		
		ListNode curr = head;
		ListNode newHead = null;
		
		while(curr!=tail) {
			ListNode next = curr.next;
			curr.next = newHead;
			newHead = curr;
			curr = next;
		}
		tail.next = newHead;
	}
	
	
	// leetcode 92
	//1. one pass to get node m and node n
	//2. reverse m-n
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode();
		dummy.next = head;
		
		int pos = 0;
		ListNode curr = dummy;
		ListNode preStart = null;
		ListNode end = null;
		while(curr!=null) {
			if(pos == m-1) {
				preStart = curr;
			}
			if(pos == n) {
				end = curr;
				break;
			}
			curr = curr.next;
			pos++;
		}
		
		ListNode postEnd = end.next;
		end.next = null;
		ListNode newStart = postEnd;
		curr = preStart.next;
		
		while(curr!=null) {
			ListNode next = curr.next;
			curr.next = newStart;
			newStart = curr;
			curr = next;
		}
		preStart.next = newStart;
		
		return head;
	}
	
	//1. find m node
	//2. start reverse until n node
	public ListNode reverseBetween2(ListNode head, int m, int n) {
		
        if(m==n){
            return head;
        }
        
		ListNode dummy = new ListNode();
		dummy.next = head;

		int pos = 0;
		ListNode curr = dummy;
		ListNode preM = null;
        ListNode mNode = null;
        ListNode newHead = null;
        
		while(curr!=null) {
            if(pos<m-1){
                curr = curr.next;
                pos++;
            }else if(pos == m-1) {
                preM = curr;
                curr = curr.next;
                pos++;
            }else if (pos==n){
                ListNode next = curr.next;
                curr.next = newHead;
                preM.next = curr;
                mNode.next = next;
                break;
            }else {
                if(pos==m){
                    mNode = curr; 
                }                

                ListNode next = curr.next;
                curr.next = newHead;
                newHead = curr;     
                //System.out.println(newHead==null?"null":newHead.val);
                
                curr = next;
                pos++;
                
            }

		}
		
		return dummy.next;
	}
	
    public ListNode recursive(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        
        ListNode newHead = recursive(head.next);
        head.next.next = head;
        head.next=null;
        return newHead;
    }
    
    public ListNode iterative(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        
        ListNode newHead = null;
        ListNode curr = head;
        
        while(curr!=null){
            ListNode next = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = next;
        }
        return newHead;
    }
    
    
    class ListNode {
    	int val;
    	ListNode next;
    	ListNode(){
    		
    	}
    	
    	ListNode (int val){
    		this.val = val;
    	}
    	
    	ListNode (int val, ListNode next){
    		this.val = val;
    		this.next = next;
    	}
    }
}
