/**
 * 
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once in a word.
 *
 * Input: 
 * 	board = [
 * 	  ['o','a','a','n'],
 * 	  ['e','t','a','e'],
 * 	  ['i','h','k','r'],
 * 	  ['i','f','l','v']
 * 	]
 * 	words = ["oath","pea","eat","rain"]
 * 
 * 	Output: ["eat","oath"]
 *
 *
 * Note:
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearchII_212 {

	//time m * n * wl
	public List<String> findWordsByTries(char[][] board, String[] words){
		
		List<String> result = new ArrayList<>();
		if(board.length==0 || board[0].length==0 || words.length==0) {
			return result;
		}
		
		TrieNode root = buildTrieNode(words);
		
		Set<String> set = new HashSet<>();
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				searchWordTrie(board, i, j, root, set);
			}
		}
		return new ArrayList<>(set);
	}
	
	public void searchWordTrie(char[][] board, int i, int j, TrieNode root, Set<String> set) {
		char c = board[i][j];
		int idx = c-'a';

		if(c=='#' || root.children[idx]==null) {
			return;
		}else {
			TrieNode curr = root.children[idx];
            
			if(curr.word!=null) {
				set.add(curr.word);
            }
			
            board[i][j] = '#';
            
            if(i>0) {
                searchWordTrie(board, i-1, j, curr, set);
            }
            if(i<board.length-1) {
                searchWordTrie(board, i+1, j, curr, set);
            }
            if(j>0) {
                searchWordTrie(board, i, j-1, curr, set);
            }
            if(j<board[0].length-1) {
                searchWordTrie(board, i, j+1, curr, set);
            }
            board[i][j] = c;
			
		}
	}
	
	public TrieNode buildTrieNode(String[] words) {
		TrieNode root = new TrieNode();
		
		for(String s: words) {
			
			int pos=0;
			TrieNode curr = root;
			
			while(pos<s.length()) {
				
				char c = s.charAt(pos);
                
				if(curr.children[c-'a']==null) {
					curr.children[c-'a'] = new TrieNode();
				}
                
				curr = curr.children[c-'a'];
				
                if(pos==s.length()-1) {	
					curr.word = s;
                    //System.out.println(curr.word);
				}
				
				pos++;
			}
			
			
		}
		
		return root;
	}
	
	class TrieNode {
		
		TrieNode[] children = new TrieNode[26];
		String word;
		//boolean isEndWord;
		
	}
	
	//time: O(m*n*wordCount*4^wordLen)
    public List<String> findWordsByStringMatch(char[][] board, String[] words) {
        
        List<String> result = new ArrayList<>();
        
        if(words.length==0 || board.length==0 || board[0].length==0){
            return result;
        }
        
        Map<Character, List<String>> dicts = new HashMap<>();
        
        for(String w: words){
            char c = w.charAt(0);
            List<String> list = dicts.getOrDefault(c, new ArrayList<>());
            list.add(w);
            dicts.put(c, list);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> resultSet = new HashSet<>();
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                char c = board[i][j];
                if(dicts.containsKey(c)){
                    List<String> list = dicts.get(c);
                    for(String s: list){
                        visited[i][j]=true;
                        if(checkWord(board, i, j, s, 0, visited)){
                            resultSet.add(s);
                        }
                        visited[i][j] = false;
                    }
                }
            }
        }
        
        return new ArrayList<>(resultSet);
    }
    
    public boolean checkWord(char[][] board, int i, int j, String word, int pos, boolean[][] visited){
        if(pos==word.length()-1){
            return true;
        }
        char c = word.charAt(++pos);
        if(i>0 && board[i-1][j]==c && visited[i-1][j]==false){
            visited[i-1][j]=true;
            if (checkWord(board, i-1, j, word, pos, visited)){
                visited[i-1][j]=false;
                return true;
            }
            visited[i-1][j]=false;
        }
        if(i<board.length-1 && board[i+1][j]==c && visited[i+1][j]==false){
            visited[i+1][j]=true;
            if(checkWord(board, i+1, j, word, pos, visited)){
                visited[i+1][j]=false;
                return true;
            } 
            visited[i+1][j]=false;
        }
        if(j>0 && board[i][j-1]==c && visited[i][j-1]==false){
            visited[i][j-1]=true;
            if(checkWord(board, i, j-1, word, pos, visited)){
                visited[i][j-1]=false;
                return true;
            }
            visited[i][j-1]=false;
        }
        if(j<board[0].length-1 && board[i][j+1]==c && visited[i][j+1]==false){
            visited[i][j+1]=true;
            if(checkWord(board, i, j+1, word, pos, visited)){
                visited[i][j+1]=false;
                return true;
            }
            visited[i][j+1]=false;
        }
        
        return false;
    }	
	
}
