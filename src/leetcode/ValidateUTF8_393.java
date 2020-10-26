package leetcode;


/**
 * 
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

	For 1-byte character, the first bit is a 0, followed by its unicode code.
	For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
	This is how the UTF-8 encoding would work:
	
	   Char. number range  |        UTF-8 octet sequence
	      (hexadecimal)    |              (binary)
	   --------------------+---------------------------------------------
	   0000 0000-0000 007F | 0xxxxxxx
	   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
	   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
	   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
	Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 *  The array of data could be multiple utf-8 encoding num
 * 
 * 
 *
 */

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
