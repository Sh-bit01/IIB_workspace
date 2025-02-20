package otp;

import java.security.SecureRandom;

public class optMapping {

	/**
	 * Sample method that can be called from a Mapping Custom Java transform.
	* The content of this method provides the implementation for the Custom Java transform.
	 */
	public static java.lang.Object sampleTransform() {
		return null;
	}
	
	 private static final String DIGITS = "0123456789";
	    private static final int OTP_LENGTH = 5;
	    
	    public static String generateOTP() {
	        SecureRandom random = new SecureRandom();
	        StringBuilder otp = new StringBuilder();
	        
	        for (int i = 0; i < OTP_LENGTH; i++) {
	            int index = random.nextInt(DIGITS.length());
	            otp.append(DIGITS.charAt(index));
	        }
	        
	        return otp.toString();
	    }

}
