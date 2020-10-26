package leetcode;

public class ValidateUTF8_393 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
    public boolean validUtf8(int[] data) {
        
        if(data.length==0 ){
            return false;
        }
        
        int i=0;
        
        while(i<data.length){

            if((data[i]& 128)==0){
                i++;
            }else if((data[i] & 128)==128 && (data[i] & 64 )==64 && (data[i] & 32)==0 && i+1 <data.length){
                if((data[i+1] & 128)==128 && (data[i+1] & 64)==0){
                    i+=2;
                }else{
                    //System.out.println("false");
                    return false;
                }
            }else if ((data[i] & 128)==128 && (data[i] & 64 )==64 && (data[i] & 32)==32 && (data[i] & 16)==0 && i+2 < data.length){

                if((data[i+1] & 128)==128 && (data[i+1] & 64)==0 
                   && (data[i+2] & 128)==128 && (data[i+2] & 64)==0){
                    i+=3;
                }else{
                    return false;
                }

            }else if ((data[i] & 128)==128 && (data[i] & 64 )==64 
                      && (data[i] & 32)==32 && (data[i]&16)==16 && (data[i] & 8)==0
                      && i+3 < data.length){

                if((data[i+1] & 128)==128 && (data[i+1] & 64)==0 
                   && (data[i+2] & 128)==128 && (data[i+2] & 64)==0
                   && (data[i+3] & 128)==128 && (data[i+3] & 64)==0){
                    i+=4;
                }else{
                    return false;
                }            

            }else{
                return false;
            }
        
        }
        
        return true;
        
    }

}
