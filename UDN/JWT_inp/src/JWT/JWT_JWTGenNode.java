package JWT;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibm.broker.plugin.*;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jwt.*;


public class JWT_JWTGenNode extends MbNode implements MbNodeInterface {

	String Algorithym;
	String JwkFilePath;
	String Subject;
	String Issuere;
	Long ExpirationTime;

	public String getAlgorithym() {
		return Algorithym;
	}

	public void setAlgorithym(String algorithym) {
		Algorithym = algorithym;
	}

	public String getJwkFilePath() {
		return JwkFilePath;
	}

	public void setJwkFilePath(String jwkFilePath) {
		JwkFilePath = jwkFilePath;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getIssuere() {
		return Issuere;
	}

	public void setIssuere(String issuere) {
		Issuere = issuere;
	}

	public Long getExpirationTime() {
		return ExpirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		ExpirationTime = expirationTime;
	}

	public JWT_JWTGenNode() throws MbException {
		createInputTerminal("in");
		createOutputTerminal("out");
		createOutputTerminal("failure");
	}

	public static String getNodeName() {
		return "JWT_JWTGenNode";
	}

	@Override
	public void evaluate(MbMessageAssembly assembly, MbInputTerminal in) throws MbException {
		// Long ExpirationTime = Long.parseLong(this.ExpirationTime);
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", "admin");
		claims.put("department", "engineering");

		// JWSAlgorithm Algorithm = JWSAlgorithm.parse(Algorithym);

		MbMessage newMsg = new MbMessage();
		MbElement outputRoot = newMsg.getRootElement();
		String jwtoken = null;

		try {
			jwtoken = generateJwtFromJwk(getSubject(), getIssuere(), getExpirationTime(), claims, JWSAlgorithm.RS256, getJwkFilePath());

			outputRoot.createElementAsLastChild(MbBLOB.PARSER_NAME).createElementAsFirstChild(MbElement.TYPE_NAME_VALUE,
					MbBLOB.ROOT_ELEMENT_NAME, jwtoken.getBytes());
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);

			e.printStackTrace(pw);
			jwtoken = e.getMessage();
		}

		if (jwtoken == null || jwtoken == "") {
			jwtoken = "error";
			outputRoot.createElementAsLastChild(MbBLOB.PARSER_NAME).createElementAsFirstChild(MbElement.TYPE_NAME_VALUE,
					MbBLOB.ROOT_ELEMENT_NAME, jwtoken.getBytes());
		}

		MbMessageAssembly newAssembly = new MbMessageAssembly(assembly, newMsg);

		MbOutputTerminal out = getOutputTerminal("out");
		out.propagate(newAssembly);

	}

	////// method

	public static String generateJwtFromJwk(String subject, String issuer, long expirationTime,
			Map<String, Object> claims, JWSAlgorithm algorithm, String jwkFilePath) throws Exception {

// Read the JWK JSON from file
		String jwkJson = new String(Files.readAllBytes(new File(jwkFilePath).toPath()), "UTF-8");
		JWK jwk = JWK.parse(jwkJson);

// Validate key type and presence of private key
		JWSSigner signer;
		if (jwk instanceof RSAKey) {
			RSAKey rsaKey = (RSAKey) jwk;
			if (!rsaKey.isPrivate()) {
				return "RSA JWK must contain a private key";
//  throw new IllegalArgumentException("RSA JWK must contain a private key");
			}
			signer = new RSASSASigner(rsaKey);
		} else if (jwk instanceof ECKey) {
			ECKey ecKey = (ECKey) jwk;
			if (!ecKey.isPrivate()) {
				return "EC JWK must contain a private key";
//throw new IllegalArgumentException("EC JWK must contain a private key");
			}
			signer = new ECDSASigner(ecKey);
		} else {
			return "Unsupported key type or missing private key";
//throw new IllegalArgumentException("Unsupported key type or missing private key");
		}

// Build JWT claims
		JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder().subject(subject).issuer(issuer)
				.expirationTime(new Date(System.currentTimeMillis() + expirationTime * 1000));

// Add custom claims
		if (claims != null) {
			for (Map.Entry<String, Object> entry : claims.entrySet()) {
				claimsBuilder.claim(entry.getKey(), entry.getValue());
			}
		}

		JWTClaimsSet claimsSet = claimsBuilder.build();

// Prepare JWT with header and claims
		SignedJWT signedJWT = new SignedJWT(
				new JWSHeader.Builder(algorithm).keyID(jwk.getKeyID()).type(JOSEObjectType.JWT).build(), claimsSet);

// Sign the JWT
		signedJWT.sign(signer);

// Return the compact JWT
		return signedJWT.serialize();
	}

}
