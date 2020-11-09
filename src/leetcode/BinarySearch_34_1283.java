package leetcode;

import java.util.Arrays;

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


public class BinarySearch_34_1283 {
	
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
    
    
    /**
     * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor (from 1 to n) and divide all the array by it and sum the result of the division. 
     * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
     * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
     * It is guaranteed that there will be an answer.
     * 
     * 
     * Example 1:

		Input: nums = [1,2,5,9], threshold = 6
		Output: 5
		Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
		If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
	   
	   Example 2:
		
		Input: nums = [2,3,5,7,11], threshold = 11
		Output: 3
		
	   Example 3:
		
		Input: nums = [19], threshold = 5
		Output: 4
		 
		
		Constraints:
		
		1 <= nums.length <= 5 * 10^4
		1 <= nums[i] <= 10^6
		nums.length <= threshold <= 10^6
     * 
     * @param nums
     * @param threshold
     * @return
     */
    
    public int smallestDivisor(int[] nums, int threshold) {
        Arrays.sort(nums);
        
        int left = 1;
        int right = nums[nums.length-1];
        
        
      //search left boundary
        while(left<right){
        
            int mid = left + (right-left)/2;

            int sum = 0;

            for(int n: nums){
                sum += n/mid;
                if(n%mid>0){
                   sum++;
                }
            }
            
            if(sum > threshold){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        
        return left;
    }
    
    
}
