package leetcode;


/**
 *  Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 *  A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *  A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *  
 * 	1 <= xi.length <= 4
 * 	xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * 	Leading zeros are allowed in xi.
 * 	For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 * 
 */
public class ValidateIPAddr_468 {

	public static void main(String[] args) {
		
	}
	
    public String validIPAddress(String IP) {
        
        if(IP.indexOf('.')>=0){
            if(isValidIPv4(IP)){
                return "IPv4";
            }
        }
        if(IP.indexOf(":")>=0){
            if(isValidIPv6(IP)){
                return "IPv6";
            }
        }
        
        return "Neither";
        
    }
    
    public boolean isValidIPv4(String IP){
        
        String[] ipStr = IP.split("\\.", -1);
        
        if(ipStr.length!=4){
            return false;
        }
        
        for(String str: ipStr){
            if(!isValidIPv4Value(str)){
                return false;
            }        
        }
        
        return true;
        
    }

    public boolean isValidIPv4Value(String str){
        if(str.length()==0 || str.length()>3){
            return false;
        }
        for(char c: str.toCharArray()){
            if(c>='0' && c<='9'){
                continue;
            }else{
                return false;
            }
        }
        int val = Integer.parseInt(str);
        if(val>255 || (val<10 && str.length()>1) || (val<100 && str.length()>2)){
            return false;
        }
        return true;
    }
    
    public boolean isValidIPv6(String IP){
        
        
        String[] ipSecs = IP.split("\\:", -1);
        
        if(ipSecs.length!=8){
            return false;
        }
        
        for(int i=0; i<ipSecs.length; i++){
            if(!isValidIPv6Value(ipSecs[i])){
                return false;
            }

        }
        return true;
    }
    
    public boolean isValidIPv6Value(String str){
        if(str.length()>4 || str.length()==0){
            return false;
        }
        for(char c : str.toCharArray()){
            if((c>='0' && c<='9') || (c>='a' && c<='f') ||(c>='A' && c<='F')){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
	
}
