package leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class MultiThread_PrintOddEven_FizzBuzz_1116_1195 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * 
	 * The same instance of ZeroEvenOdd will be passed to three different threads:
	 * Thread A will call zero() which should only output 0's.
	 * Thread B will call even() which should only ouput even numbers.
	 * Thread C will call odd() which should only output odd numbers.
	 * 
	 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.
	 * 
	 * Example 1:
	 * 
	 * Input: n = 2
	 * Output: "0102"
	 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
	 * 
	 * Example 2:
	 * Input: n = 5
	 * Output: "0102030405"
	 */
	//use synchronized and wait/notifyAll
	class ZeroEvenOdd {
	    private int n;
	    
	    int count;
	    boolean printZero = false;
	    boolean printEven = false;
	    boolean printOdd = false;
	    
	    Object lock;
	    public ZeroEvenOdd(int n) {
	        this.n = n;
	        count = 0;
	        printZero = true;
	        
	        //
	        //instead of sync (this), we can also use sync (lock);
	        //
	        lock = new Object();
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void zero(IntConsumer printNumber) throws InterruptedException {
	        
	        for(int i=0; i<n; i++){
	            synchronized(this){
	            //synchronized(lock){	
	                while(printZero == false){
	                    this.wait();
	                    //lock.wait();
	                }
	                printNumber.accept(0);
	                printZero = false;
	                if(i%2==0){
	                    printOdd = true;
	                }else{
	                    printEven = true;
	                }
	                this.notifyAll();
	                //lock.notifyAll();
	            }
	            
	        }
	        
	    }

	    public void even(IntConsumer printNumber) throws InterruptedException {
	        
	        for(int i=2; i<=n; i+=2){
	            synchronized(this){
	                
	                while(printZero || printOdd){
	                    this.wait();
	                }
	                printNumber.accept(i);

	                printZero = true;
	                printEven = false;
	                this.notifyAll();
	            }
	            
	        }
	    }

	    public void odd(IntConsumer printNumber) throws InterruptedException {

	        for(int i=1; i<n; i+=2){
	            synchronized(this){
	                while(printZero || printEven){
	                    this.wait();
	                }
	                printNumber.accept(i);
	                
	                printZero = true;
	                printOdd = false;
	                this.notifyAll();
	            }
	        }
	        
	    }
	}

	//use Semaphore
	class ZeroEvenOdd2 {
	    private int n;
	    
	    Semaphore semaphoreZero, semaphoreEven, semaphoreOdd;
	    
	    public ZeroEvenOdd2(int n) {
	        this.n = n;
	        semaphoreZero = new Semaphore(1);
	        semaphoreOdd = new Semaphore(0);
	        semaphoreEven = new Semaphore(0);
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void zero(IntConsumer printNumber) throws InterruptedException {
	        
	        for(int i=0; i<n; i++){
	            semaphoreZero.acquire();
	            printNumber.accept(0);
	            if(i%2==0){
	                semaphoreOdd.release();
	            }else{
	                semaphoreEven.release();
	            }
	        }
	    }

	    public void even(IntConsumer printNumber) throws InterruptedException {
	        for(int i=2; i<=n; i+=2){
	            semaphoreEven.acquire();
	            printNumber.accept(i);
	            semaphoreZero.release();
	        }
	    }

	    public void odd(IntConsumer printNumber) throws InterruptedException {
	        for(int i=1; i<=n; i+=2){
	            semaphoreOdd.acquire();
	            printNumber.accept(i);
	            semaphoreZero.release();
	        }        
	    }
	}	
	
	
	//use Semaphore
	class FizzBuzz {
	    private int n;
	    
	    Semaphore sOther, s3, s5, s15;
	    
	    public FizzBuzz(int n) {
	        this.n = n;
	        sOther = new Semaphore(0);
	        s3 = new Semaphore(0);
	        s5 = new Semaphore(0);
	        s15 = new Semaphore(0);
	    }

	    // printFizz.run() outputs "fizz".
	    public void fizz(Runnable printFizz) throws InterruptedException {
	        for(int i=3; i<=n; i+=3){
	            if(i%15!=0){
	                s3.acquire();
	                //System.out.println(i+"s3 aq");
	                printFizz.run();
	                sOther.release();
	            }
	        }
	    }

	    // printBuzz.run() outputs "buzz".
	    public void buzz(Runnable printBuzz) throws InterruptedException {
	        for(int i=5; i<=n; i+=5){
	            if(i%15!=0){
	            s5.acquire();
	            //System.out.println(i+"s5 aq");
	            printBuzz.run();
	            sOther.release();
	            }
	        }        
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	        for(int i=15; i<=n; i+=15){
	            s15.acquire();
	            //System.out.println(i+"s15 aq");
	            printFizzBuzz.run();
	            sOther.release();
	        }        
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	        for(int i=1; i<=n; i++){
	            if(i%15==0){                
	                s15.release();
	                //System.out.println(i+"s15 release");
	                sOther.acquire();
	            }else if (i%3==0){
	                s3.release();
	                //System.out.println(i+"s3 release");
	                sOther.acquire();
	                
	            }else if (i%5==0){
	                s5.release();
	                //System.out.println(i+"s5 release");
	                sOther.acquire();
	            }else{
	                //sOther.acquire();
	                printNumber.accept(i);
	                //sOther.release();
	            }
	        }
	    }
	}	


	//use synchronized, wait, notifyAll
	class FizzBuzz2 {
	    private int n;

	    private Object lock;
	    volatile int num;
	    public FizzBuzz2(int n) {
	        this.n = n;
	        num = 1;
	        lock = new Object();
	    }

	    // printFizz.run() outputs "fizz".
	    public void fizz(Runnable printFizz) throws InterruptedException {
	        while(num<=n){ 
	            synchronized(lock){
	                
	                if(num%3==0 && num%15!=0){
	                    printFizz.run();
	                    num++;
	                    lock.notifyAll();
	                }else{
	                    lock.wait();
	                }
	            }
	        }        
	    }

	    // printBuzz.run() outputs "buzz".
	    public void buzz(Runnable printBuzz) throws InterruptedException {
	        while(num<=n){ 
	            synchronized(lock){
	                
	                if(num%5==0 && num%15!=0){
	                    printBuzz.run();
	                    num++;
	                    lock.notifyAll();
	                }else{
	                    lock.wait();
	                }
	            }          
	        }
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	        while(num<=n){ 
	            synchronized(lock){	                
	                if(num%15==0){
	                    printFizzBuzz.run();
	                    num++;
	                    lock.notifyAll();
	                }else{
	                    lock.wait();
	                }
	            }
	        }             
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	        while(num<=n){    
	            synchronized(lock){	                
	                if(num%3==0 ||num%5==0){
	                    lock.wait();
	                }else{
	                    printNumber.accept(num);
	                    num++;
	                    lock.notifyAll();
	                }
	            }
	        }

	    }
	}
}
