package leetcode;

public class FindCelebrity_277 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public int findCelebrity(int n) {
        
        int celebrity = 0;
        
        //use one loop to filter out possible celebrity
        for(int i=0; i<n; i++){
            if(celebrity!=i){
                if(knows(celebrity, i)){
                    celebrity = i;
                }
            }
        }
        //verify the celebrity by checking both directions
        for(int i=0; i<n; i++){
            if(celebrity!=i){
                if(knows(celebrity, i) || !knows(i, celebrity)){
                    return -1;
                }
            }
        }
        
        return celebrity;
        
    }

	private boolean knows(int celebrity, int i) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
