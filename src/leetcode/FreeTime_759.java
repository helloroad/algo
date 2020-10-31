package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * 
 * 
 * Example 1:

	Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
	Output: [[3,4]]
	Explanation: There are a total of three employees, and all common
	free time intervals would be [-inf, 1], [3, 4], [10, inf].
	We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:

	Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
	Output: [[5,6],[7,9]]
 */

public class FreeTime_759 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	class Interval {
	    public int start;
	    public int end;

	    public Interval() {}

	    public Interval(int _start, int _end) {
	        start = _start;
	        end = _end;
	    }
	}
	
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		
		//return mergeFreeTime(schedule);
		//return sweepline(schedule);
		return usePQ(schedule);
		
	}
	
	public List<Interval> usePQ(List<List<Interval>> schedule) {
	
        List<Interval> result = new ArrayList<>();
        
        PriorityQueue<Interval> pq = new PriorityQueue<>((p, q)->p.start-q.start);
        
        for(List<Interval> list: schedule){
            for(Interval itv: list){
                pq.offer(itv);
            }
        }
        
        Interval prev = pq.poll();
        
        while(!pq.isEmpty()){
            Interval curr = pq.peek();
            if(prev.end<curr.start){
                result.add(new Interval(prev.end, curr.start));
                prev = pq.poll();
            }else{
                if(prev.end<=curr.end){
                    prev = curr;
                }
                pq.poll();
            }
        }
        
        return result;		
		
	}
	
	public List<Interval> sweepline(List<List<Interval>> schedule){
        List<Interval> result = new ArrayList<>();
        
        TreeMap<Integer, Integer> timeMap = new TreeMap<>();
        
        for(List<Interval> list: schedule){
            for(Interval itv: list){
                timeMap.put(itv.start, timeMap.getOrDefault(itv.start, 0)+1);
                timeMap.put(itv.end, timeMap.getOrDefault(itv.end, 0) -1);
            }
        }
        
        int start = 0;
        int count = 0;

        for(int t: timeMap.keySet()){
            int cnt = timeMap.get(t);
            if(count==0 && t!=timeMap.firstKey()){
                result.add(new Interval(start, t));
            }
            count+=cnt;
            start = t;
        }
        
        return result;		
		
	}
	
	
	
	//get free time from the working interval
	//merge free time interval to get the overlap intervals
    public List<Interval> mergeFreeTime(List<List<Interval>> schedule) {
        
        List<List<Interval>> freeTimeList = new ArrayList<>();
        
        for(List<Interval> ls: schedule){
            
            List<Interval> list = new ArrayList<>();
            
            int start = Integer.MIN_VALUE;
            
            for(int i=0; i<ls.size(); i++){
                Interval ft = new Interval(start, ls.get(i).start);
                list.add(ft);
                start = ls.get(i).end;
            }
            
            list.add(new Interval(start, Integer.MAX_VALUE));
                
            freeTimeList.add(list);
        }
        
        while(freeTimeList.size()>1){
            List<List<Interval>> mergeList = new ArrayList<>();
            
            for(int i=0; i<freeTimeList.size(); i=i+2){
                if(i+1<freeTimeList.size()){
                    mergeList.add(merge(freeTimeList.get(i), freeTimeList.get(i+1)));
                }else{
                    mergeList.add(freeTimeList.get(i));
                }
            }
            
            freeTimeList = mergeList;
        }
        
        List<Interval> result = new ArrayList<>();
        
        for(Interval i: freeTimeList.get(0)){
            if(i.start==Integer.MIN_VALUE || i.end==Integer.MAX_VALUE){
                continue;
            }else{
                result.add(i);
            }
        }
        
        return result;
    }
    
    public List<Interval> merge(List<Interval> l1, List<Interval> l2){
        
        List<Interval> result = new ArrayList<>();
        
        int i=0;
        int j=0;
        while(i<l1.size() && j<l2.size()){
            
            Interval int1 = l1.get(i);
            Interval int2 = l2.get(j);
            //System.out.println(int1.start + ":"+ int1.end);
            //System.out.println(int2.start + ":"+ int2.end);
            
            int start = Math.max(int1.start, int2.start);
            int end = Math.min(int1.end, int2.end);
            
            if(start<end){
                result.add(new Interval(start, end));
                if(int1.end==int2.end){
                    i++;
                    j++;
                }else if(int1.end==end){
                    i++;
                }else{
                    j++;
                }
            }else{
                if(int1.end>int2.end){
                    j++;
                }else{
                    i++;
                }
            }
            //if(result.size()>0)
                //System.out.println("added:" +result.get(result.size()-1).start + ":" + result.get(result.size()-1).end);
        }
        
        return result;
        
    }

}
