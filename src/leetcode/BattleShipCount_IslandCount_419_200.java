package leetcode;

import java.util.ArrayList;


/*
 * BattleShip Count
 * Given an 2D board, count how many battleships are in it. 
 * The battleships are represented with 'X's, empty slots are represented with '.'s. 
 * You may assume the following rules:
	You receive a valid board, made of only battleships or empty slots.
	Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
	At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * 
 * 
 * Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 *
 *
 * Num of Island
 *Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 *An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 *You may assume all four edges of the grid are all surrounded by water.
 */

public class BattleShipCount_IslandCount_419_200 {
	
	public int BattleShipCount(char[][] board) {
		
		//return dfs(board);
		return onePass(board);
	}
	
    //check left and up for each X
    public int onePass(char[][] board){
        
        if(board.length==0 || board[0].length==0){
            return 0;
        }
        int count = 0;
        for(int i=0; i<board.length; i++){
            
            for(int j=0; j<board[0].length; j++){

                if(board[i][j]=='X'){
                    
                    if(i==0 && j==0){
                        count++;
                    }else if (i==0){
                        if(board[i][j-1]!='X'){
                            count++;
                        }
                    }else if(j==0){
                        if(board[i-1][j]!='X'){
                            count++;    
                        }
                        
                    }else if(board[i-1][j]!='X' && board[i][j-1]!='X'){
                        count++;
                    }
                    
                }
                
            }
            
        }
        
        return count;        

    }
   
    
    public int dfs(char[][] board){
        
        if(board.length==0 || board[0].length==0){
            return 0;
        }
    
        int count = 0;
            
        for(int i=0; i<board.length; i++){
            
            for(int j=0; j<board[0].length; j++){
                
                if(board[i][j]=='X'){
                    count++;
                    countBattleship(board, i, j);
                }
                
            }
            
        }
        
        return count;
    }
    
    public void countBattleship(char[][] board, int i, int j){
        
        board[i][j] = '.';
        
        if(i>0 && board[i-1][j]=='X'){
            countBattleship(board, i-1, j);
        }
        
        if(i<board.length-1 && board[i+1][j]=='X'){
            countBattleship(board, i+1, j);
        }
        
        if(j>0 && board[i][j-1]=='X'){
            countBattleship(board, i, j-1);
        }
        
        if(j<board[0].length-1 && board[i][j+1]=='X'){
            countBattleship(board, i, j+1);
        }        
        
    }	
	
	
    public int countIsland(char[][] grid) {
        int count = 0;
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]=='1'){
                    count++;
                    exploreIsland(grid, i, j);
                }
            }
        }
        
        return count;
    }
    //update the explored position
    public void exploreIsland(char[][] grid, int x, int y){
        
        if(grid[x][y]=='0' || grid[x][y]=='2'){
            return;
             
        }else{
            grid[x][y] = '2';   
        
        
            if(x-1>=0){
                exploreIsland(grid, x-1, y);
            }
            
            if(x+1<grid.length){
                exploreIsland(grid, x+1, y);
            }
            
            if(y-1>=0){
                exploreIsland(grid, x, y-1);
            }
            
            if(y+1<grid[0].length){
                exploreIsland(grid, x, y+1);
            }
        
        }
    }
}