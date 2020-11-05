package leetcode;

/**
 * Find First and Last Position of Element in Sorted Array (search boundary)
 * 
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * 
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 * 
 * 
 *
 */


public class BinarySearch_34 {
	
    public int[] searchRange(int[] nums, int target) {
        
        int start = -1;
        int end = -1;       
        
        if(nums.length==0){
            return new int[]{start, end};
        }
        
        int left = 0;
        int right = nums.length-1;

        //search left boundary
        while(left<right){
            int mid = left + (right-left)/2;
            
            if(nums[mid]<target){
                left = mid +1;
            }else{
                right = mid;
            }
        }
        
        if(nums[left]!=target){
            return new int[]{start, end};
        }
        
        start = left;
        
        left = 0;
        right = nums.length-1;
        //search right boundary
        while(left<right){
            int mid = left + (right-left)/2+1;
            
            if(nums[mid]>target){
                right = mid -1;
            }else{
                left = mid;
            }
            
        }
            
        end = right;
        
        return new int[]{start, end};
        
        
    }
}
