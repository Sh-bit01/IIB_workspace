package encdec;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class Esecurity {

    private static final String KEYSTORE_PATH = "C:/Users/Sreenivas Bandaru/Documents/Encryption_logic/client-keystore.jks";
    private static final String KEYSTORE_PASSWORD = "password";
    private static final String KEYSTORE_Alias = "client";
    private static final String Server_KEYSTORE_Alias = "server";

    public static String encryptPassword( String password) {
        try {
            KeyStore keyStore = loadKeyStore();
            PublicKey publicKey = keyStore.getCertificate(Server_KEYSTORE_Alias).getPublicKey();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptPassword( String encryptedPassword) {
        try {
            KeyStore keyStore = loadKeyStore();
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(KEYSTORE_Alias, KEYSTORE_PASSWORD.toCharArray());

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));

            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static KeyStore loadKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream fis = new FileInputStream(KEYSTORE_PATH)) {
                keyStore.load(fis, KEYSTORE_PASSWORD.toCharArray());
            }
            return keyStore;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
