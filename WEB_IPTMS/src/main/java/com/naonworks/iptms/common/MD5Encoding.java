package com.naonworks.iptms.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoding{
	
    public static String md5(String value) throws NoSuchAlgorithmException {
    	StringBuffer sb = new StringBuffer(); 
    	
		byte[] digest = MessageDigest.getInstance("MD5").digest( value.getBytes() );
  	
		for( int i = 0; i < digest.length; i++ )
		{ 
			sb.append( Integer.toString( ( digest[i] & 0xf0) >> 4, 16 ) ); 
			sb.append( Integer.toString( digest[i] & 0x0f, 16 ) );
		} 
    	
		return sb.toString();
    }
    
    
    public static String sha256(String value) throws Exception {
    	StringBuffer sb = new StringBuffer();
    	
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(value.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        return sb.toString();

    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(MD5Encoding.md5("jenious80"));
    }
    
}
