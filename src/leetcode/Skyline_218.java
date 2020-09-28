package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Skyline_218 {

    private class Point {
        int pos;
        boolean isStart;
        int height;
        public Point (){

        }

        public Point (int pos, boolean isStart, int height) {
            this.pos = pos;
            this.isStart = isStart;
            this.height = height;
        }
    }    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public Skyline_218() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public List<List<Integer>> getSkyline(int[][] buildings){
		
		List<List<Integer>> skyline = new ArrayList<>();
		
		if(buildings.length==0) {
			return skyline;
		}
		
		List<Point> pList = new ArrayList<>();
		
		for(int[] b: buildings) {
			
			int start = b[0];
			int end = b[1];
			int height = b[2];
			
			Point startP = new Point(start, true, height);
			pList.add(startP);
			
			Point endP = new Point(end, false, height);
			pList.add(endP);
		}
		
		Collections.sort(pList, (p1, p2)->{
			if(p1.pos != p2.pos) {
				return p1.pos - p2.pos;
			}else {
				//if p1 and p2 both are start p or end p;
				// 1. both starts: higher one is ahead of lower one, as higher building will override lower building
				// 2. both ends: lower one is ahead, lower building is still under higher building
				if(p1.isStart == p2.isStart) {
					if(p1.isStart) {
						return p2.height - p1.height;
					}else {
						return p1.height - p2.height;
					}
				}else {
					//if p1 and p2 are start and end points;
					//end point will be ahead of start point
					if(p1.isStart) {
						return -1;
					}else {
						return 1;
					}
				}
			}
		});
		
		//use tree map to track the building start and end pairs and also sort the height;
		TreeMap<Integer, Integer> hMap = new TreeMap<>();
		int preMaxH = 0;
		int currMaxH = 0;
		for(Point p: pList) {
			if(p.isStart) {
				hMap.put(p.height, hMap.getOrDefault(p.height, 0)+1);
				currMaxH = hMap.lastKey();
				if(currMaxH > preMaxH) {
					skyline.add(Arrays.asList(p.pos, currMaxH));
					preMaxH = currMaxH;
				}
			}else {
				hMap.put(p.height, hMap.get(p.height)-1);
				if(hMap.get(p.height)==0) {
					hMap.remove(p.height);
				}
				currMaxH = hMap.size()==0? 0: hMap.lastKey();
				if(currMaxH<preMaxH) {
					skyline.add(Arrays.asList(p.pos, currMaxH));
					preMaxH = currMaxH;
				}
			}
			
		}
		
		return skyline;
	}
	
	
}
