/**
 * 
 */
package leetcode;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 * 	1 represents the starting square.  There is exactly one starting square.
 * 	2 represents the ending square.  There is exactly one ending square.
 * 	0 represents empty squares we can walk over.
 * 	-1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *	Exp1:
 *	Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
	Output: 2
	Explanation: We have the following two paths: 
	1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
	2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 */
public class UniquePath_III_980 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	    
    public UniquePath_III_980() {
		super();
		// TODO Auto-generated constructor stub
	}




	int pathCount = 0;
    int visitCount = 0;
    int sqCount = 0;

    public int uniquePathsIII(int[][] grid) {
    
        int startRow = 0;
        int startCol = 0;
        
        int endRow = 0;
        int endCol = 0;
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==1){
                    startRow = i;
                    startCol = j;
                }else if (grid[i][j]==2){
                    endRow = i;
                    endCol = j;
                }else if (grid[i][j]==0){
                    sqCount++;
                }
                
            }
        }
        
        //boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        //totalSq = grid.length * grid[0].length;
        //System.out.println(sqCount);
        dfs(grid, startRow, startCol, endRow, endCol);
        
        return pathCount;
    }
    
    public void dfs(int[][] grid, int startRow, int startCol, int endRow, int endCol){
        
        visitCount++;
        //System.out.println(startRow + ":" + startCol + ":"+ endRow + ":"+ endCol + "::" + visitCount);
        if(startRow == endRow && startCol == endCol){   
            if(visitCount == sqCount+2){
                pathCount++;
            }
            visitCount--;
            return;
        }
        
        int row = grid.length;
        int col = grid[0].length;
        
        int tmp;
        if(startRow>0 && (grid[startRow-1][startCol]==0 || grid[startRow-1][startCol]==2)){
            tmp = grid[startRow-1][startCol];
            grid[startRow-1][startCol]=3;
            dfs(grid, startRow-1, startCol, endRow, endCol);
            grid[startRow-1][startCol]=tmp;            
        }
        
        if(startRow<row-1 && (grid[startRow+1][startCol]==0 || grid[startRow+1][startCol]==2)){
            tmp = grid[startRow+1][startCol];
            grid[startRow+1][startCol]=3;
            dfs(grid, startRow+1, startCol, endRow, endCol);
            grid[startRow+1][startCol]=tmp;
        }
        
        if(startCol>0 && (grid[startRow][startCol-1]==0 || grid[startRow][startCol-1]==2)){
            tmp = grid[startRow][startCol-1];
            grid[startRow][startCol-1]=3;
            dfs(grid, startRow, startCol-1, endRow, endCol);
            grid[startRow][startCol-1]=tmp;
        }
        
        if(startCol<col-1 && (grid[startRow][startCol+1]==0 || grid[startRow][startCol+1]==2)){
            tmp = grid[startRow][startCol+1];
            grid[startRow][startCol+1]=3;
            dfs(grid, startRow, startCol+1, endRow, endCol);
            grid[startRow][startCol+1]=tmp;
        }        
        
        visitCount--;
    }



}
