package renting.com.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	
	 private final static String PASSWORD_VALIDATION_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,50})";
	 private final static String EMAIL_VALIDATION_REGEX = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	
	 public static boolean isValidRegexPassword(String password) {

	    if (password == null) {
	      return false;
	    }
	    return password.matches(PASSWORD_VALIDATION_REGEX);
	  }
	 
	 public static boolean isValidRegexEmail(String email) {

		    if (email == null) {
		      return false;
		    }
		    return email.matches(EMAIL_VALIDATION_REGEX);
	 }
	 
	 public static boolean isValidPasswordLength(String password,int passwordLength) {

		    if (password == null) {
		      return false;
		    }
		    return password.length() >= passwordLength;
	}
	 
	 //Chiffrement SHA
	 private static String convertToHex(byte[] data) {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				int halfbyte = (data[i] >>> 4) & 0x0F;
				int two_halfs = 0;
				do {
					if ((0 <= halfbyte) && (halfbyte <= 9))
						buf.append((char) ('0' + halfbyte));
					else
						buf.append((char) ('a' + (halfbyte - 10)));
					halfbyte = data[i] & 0x0F;
				} while (two_halfs++ < 1);
			}
			return buf.toString();
		}

		public static String SHA1(String text) throws NoSuchAlgorithmException,
				UnsupportedEncodingException {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1");
			byte[] sha1hash = new byte[40];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			sha1hash = md.digest();
			return convertToHex(sha1hash);
		}
		
	

		public final static boolean isInSubnet(String ipaddr, String subnet, String mask) {
		    
		    //  Convert the addresses to integer values
		    
		  /*  int ipaddrInt = parseNumericAddress(ipaddr);
		    if ( ipaddrInt == 0)
		      return false;
		      
		    int subnetInt = parseNumericAddress(subnet);
		    if ( subnetInt == 0)
		      return false;
		      
		    int maskInt = parseNumericAddress(mask);
		    if ( maskInt == 0)
		      return false;
		      
		    //  Check if the address is part of the subnet
		    
		    if (( ipaddrInt & maskInt) == subnetInt)*/
		      return true;
		   // return false;  
		  }
		  /**
		   * Check if the specified address is a valid numeric TCP/IP address and return as an integer value
		   * 
		   * @param ipaddr String
		   * @return int
		   */
		  public final static int parseNumericAddress(String ipaddr) {
		  
		    //  Check if the string is valid
		    
		    if ( ipaddr == null || ipaddr.length() < 7 || ipaddr.length() > 15)
		      return 0;
		    //  Check the address string, should be n.n.n.n format
		    String[] token = ipaddr.split("\\.");
		    int tokenSize = token.length;
		    
		    if (tokenSize != 4)
		      return 0;

		    int ipInt = 0;
		    
		    for(String key : token) {
		      //  Get the current token and convert to an integer value
		      String ipNum = key;
		      
		      try {
		        
		        //  Validate the current address part
		        
		        int ipVal = Integer.valueOf(ipNum).intValue();
		        if ( ipVal < 0 || ipVal > 255)
		          return 0;
		          
		        //  Add to the integer address
		        
		        ipInt = (ipInt << 8) + ipVal;
		      }
		      catch (NumberFormatException ex) {
		        return 0;
		      }
		    }
		   
		    return ipInt;
		  }
		  
		 /* public static String getIpAdress(){
			  try {
					InetAddress addr = InetAddress.getLocalHost();
					return addr.getHostAddress();					
				} catch (UnknownHostException e) {
					Window.alert(e.getMessage());
					
				}
				return "";
		  }
		  */
		  
		  
		  public static String encodeToMD5(String key) {
			  
			  byte[] uniqueKey = key.getBytes();
			  byte[] hash = null;	 
			  try {
				  // on récupère un objet qui permettra de crypter la chaine
				  hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
			  }	 
			  catch (NoSuchAlgorithmException e) {
				  throw new Error("no MD5 support in this VM");
			  }	 
			  StringBuffer hashString = new StringBuffer();	 
			  for (int i = 0; i < hash.length; ++i) {	 
				  String hex = Integer.toHexString(hash[i]);	 
				  if (hex.length() == 1) {
					  hashString.append(0);
					  hashString.append(hex.charAt(hex.length() - 1));
				  }else {
					  hashString.append(hex.substring(hex.length() - 2));
				  }
			  }
			  return hashString.toString();
		  }

		  
		  
}
