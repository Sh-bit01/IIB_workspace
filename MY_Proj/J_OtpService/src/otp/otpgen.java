package otp;
import java.security.SecureRandom;

public class otpgen {
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
