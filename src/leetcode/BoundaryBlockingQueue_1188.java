package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BoundaryBlockingQueue_1188 {

	public void main (String[] args) {
		
	}
	
	
	class BoundedBlockingQueue {
	    private int capacity;
	    private Queue<Integer> q;
	    public BoundedBlockingQueue(int capacity) {
	        this.capacity = capacity;
	        this.q = new LinkedList<>();
	    }
	    
	    public synchronized void enqueue(int element) throws InterruptedException {
	        while(q.size()==capacity){
	            wait();
	            continue;            
	        }
	        q.offer(element);
	        notifyAll();
	    }
	    
	    public synchronized int dequeue() throws InterruptedException {
	        while(q.isEmpty()){
	            wait();
	            continue;            
	        }
	        int ret = q.poll();
	        notifyAll();
	        return ret;
	    }
	    
	    public int size() {
	        return q.size();
	    }
	}
	
	class BoundedBlockingQueue2 {
	    final int capacity;
	    Deque<Integer> queue;

	    public BoundedBlockingQueue2(int capacity) {
	        this.capacity = capacity;
	        this.queue = new ArrayDeque<>();
	    }
	    
	    public void enqueue(int element) throws InterruptedException {
	        synchronized(queue) {
	            while(queue.size() == capacity) {
	                queue.wait();
	            }

	            queue.addLast(element);
	            queue.notify();
	        }
	    }
	    
	    public int dequeue() throws InterruptedException {
	        synchronized(queue) {
	            while(queue.size() == 0) {
	                queue.wait();
	            }

	            Integer element = queue.removeFirst();
	            queue.notify();
	            return (int)element;
	        }
	        
	    }
	    
	    public int size() {
	        return queue.size();
	    }
	}
	
}
