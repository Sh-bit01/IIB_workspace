package JWT;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.*;
//import com.nimbusds.jose.jwk.*;
//import com.nimbusds.jwt.*;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
public class JwtGenerator {
//
//    /**
//     * Generates a signed JWT from user-provided JWK and parameters.
//     *
//     * @param subject         JWT subject
//     * @param issuer          JWT issuer
//     * @param expirationTime  Expiration time in seconds from now
//     * @param claims          Custom claims (key-value map)
//     * @param algorithm       JWS algorithm (e.g., RS256, ES256)
//     * @param jwkFilePath     Path to JWK JSON file (must contain private key)
//     * @return Signed JWT as String
//     * @throws Exception if the JWK can't be parsed or signing fails
//     */
//    public static String generateJwtFromJwk(String subject,
//                                            String issuer,
//                                            long expirationTime,
//                                            Map<String, Object> claims,
//                                            JWSAlgorithm algorithm,
//                                            String jwkFilePath) throws Exception {
//
//        // Read the JWK JSON from file
//        String jwkJson = new String(Files.readAllBytes(new File(jwkFilePath).toPath()), "UTF-8");
//        JWK jwk = JWK.parse(jwkJson);
//
//        // Validate key type and presence of private key
//        JWSSigner signer;
//        if (jwk instanceof RSAKey) {
//            RSAKey rsaKey = (RSAKey) jwk;
//            if (!rsaKey.isPrivate()) {
//            	return "RSA JWK must contain a private key";
//              //  throw new IllegalArgumentException("RSA JWK must contain a private key");
//            }
//            signer = new RSASSASigner(rsaKey);
//        } else if (jwk instanceof ECKey) {
//            ECKey ecKey = (ECKey) jwk;
//            if (!ecKey.isPrivate()) {
//            	return "EC JWK must contain a private key";
//                //throw new IllegalArgumentException("EC JWK must contain a private key");
//            }
//            signer = new ECDSASigner(ecKey);
//        } else {
//        	return "Unsupported key type or missing private key";
//            //throw new IllegalArgumentException("Unsupported key type or missing private key");
//        }
//
//        // Build JWT claims
//        JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
//                .subject(subject)
//                .issuer(issuer)
//                .expirationTime(new Date(System.currentTimeMillis() + expirationTime * 1000));
//
//        // Add custom claims
//        if (claims != null) {
//            for (Map.Entry<String, Object> entry : claims.entrySet()) {
//                claimsBuilder.claim(entry.getKey(), entry.getValue());
//            }
//        }
//
//        JWTClaimsSet claimsSet = claimsBuilder.build();
//
//        // Prepare JWT with header and claims
//        SignedJWT signedJWT = new SignedJWT(
//                new JWSHeader.Builder(algorithm)
//                        .keyID(jwk.getKeyID())
//                        .type(JOSEObjectType.JWT)
//                        .build(),
//                claimsSet
//        );
//
//        // Sign the JWT
//        signedJWT.sign(signer);
//
//        // Return the compact JWT
//        return signedJWT.serialize();
//    }
//    
//    public static void main(String[] args) {
//        try {
//            Map<String, Object> customClaims = new HashMap<>();
//            customClaims.put("role", "admin");
//            customClaims.put("email", "user@example.com");
//
//            String jwt = JwtGenerator.generateJwtFromJwk(
//                "1234567890",                          // subject
//                "my-app",                              // issuer
//                3600,                                   // expirationTime in seconds
//                customClaims,
//                JWSAlgorithm.RS256,                     // algorithm
//                "C:\\temp\\keypair.json"            // path to JWK file
//            );
//
//            System.out.println("Generated JWT:\n" + jwt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
}
//
