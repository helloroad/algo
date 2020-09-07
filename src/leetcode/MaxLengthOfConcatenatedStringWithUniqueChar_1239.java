/**
 * 
 */
package leetcode;

import java.util.List;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * 
 * Return the maximum possible length of s.
 * 
 * Eg 1:
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 * Eg 2:
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 *
 * Eg 1:
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 */
public class MaxLengthOfConcatenatedStringWithUniqueChar_1239 {

    int max = 0;
    public int maxLength(List<String> arr) {
        
        if(arr.size()==0){
            return 0;
        }
        
        boolean[] charChecker = new boolean[26];
        
        generateString(charChecker, 0, arr);
        
        return max;
    }
    
    //DFS with visited check
    public void generateString(boolean[] charChecker, int start, List<String> arr){
        
        int len = 0;
        if(start==arr.size()){

            for(boolean b: charChecker){
                if(b){
                    len++;
                }
            }
            max = Math.max(max, len);
            return;
        }
        
        for(int i=start; i<arr.size(); i++){
            
            if(isUnique(charChecker, arr.get(i))){
                            
                generateString(charChecker, i+1, arr);              
                
                for(char c: arr.get(i).toCharArray()){
                    charChecker[c-'a'] = false;
                }                  
                
            }
            
        }

 
        for(boolean b: charChecker){
            if(b){
                len++;
            }
        }
        max = Math.max(max, len);          
        
    }
    
    public boolean isUnique(boolean[] charChecker, String s){
        
        for(int i=0; i< s.length(); i++){
            char c = s.charAt(i);
            if(charChecker[c-'a']){
                
                for(int j=i-1; j>=0; j--){
                    c = s.charAt(j);
                    charChecker[c-'a'] = false;
                }
                
                return false;
            }else{
                charChecker[c-'a'] = true;
            }
        }
        return true;
    }	
	
}
