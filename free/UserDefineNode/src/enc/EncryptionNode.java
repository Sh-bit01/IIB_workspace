package enc;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;
import com.ibm.broker.plugin.MbOutputTerminal;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class EncryptionNode extends MbNode implements MbNodeInterface {
	
	
	static String SECRET_KEY = "KEY";
	static String PROPERTY_ALGORITHM = "ALGORITHM";


	public EncryptionNode() throws MbException
	{
	// create terminals here
	createInputTerminal ("in");
	createOutputTerminal ("out");
	createOutputTerminal ("failure");
	}
	
	public static String getNodeName()
	{
	return "Encryption";
	}
	
	@Override
	public void evaluate(MbMessageAssembly assembly, MbInputTerminal in) throws MbException {
        //MbElement rootElement = assembly.getMessage().getRootElement();
        //String Data = "";
		String terminalName = "out";
        MbOutputTerminal out = getOutputTerminal(terminalName);

        byte[] messageBytes = assembly.getMessage().getRootElement().toBitstream(null, null, null, 0, 1208, 0);

        // Convert byte array to String (assuming UTF-8 encoding)
        try {
			String messageString = new String(messageBytes, "UTF-8");
			String encrypt = encrypt(messageString);
			
			assembly.getMessage().getRootElement().getLastChild().createElementAsLastChild(encrypt);
			
			out.propagate(assembly);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public static String encrypt(String plainText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
