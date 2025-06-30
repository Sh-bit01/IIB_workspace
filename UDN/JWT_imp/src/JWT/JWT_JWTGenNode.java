package JWT;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.ibm.broker.plugin.MbBLOB;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
//import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.nimbusds.jose.JWSAlgorithm;

public class JWT_JWTGenNode extends MbNode implements MbNodeInterface {

	String algo;
	String sub;
	String iss;
	String KeyFile;
	int ExpTime;
	
///-----getter and setter
	public String getAlgo() {
		return algo;
	}
	public void setAlgo(String algo) {
		this.algo = algo;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getKeyFile() {
		return KeyFile;
	}
	public void setKeyFile(String keyFile) {
		KeyFile = keyFile;
	}
	public String getExpTime() {
		return String.valueOf(ExpTime);
	}
	public void setExpTime(String expTime) {
		ExpTime = Integer.parseInt(expTime);
	}
	
	
///------ terminals
	public JWT_JWTGenNode() throws MbException {
		createInputTerminal("in");
		createOutputTerminal("out");
		createOutputTerminal("failure");
	}
/////----nodename 
	public static String getNodeName() {
		return "JWT_JWTGenNode";
	}


	@Override
	public void evaluate(MbMessageAssembly assembly, MbInputTerminal in) throws MbException {

//		MbMessage newMsg = new MbMessage();
//
//		MbElement outRoot = newMsg.getRootElement();
//		MbElement outJsonRoot = outRoot.createElementAsLastChild(MbJSON.PARSER_NAME);
//		MbElement outJsonData =outJsonRoot.createElementAsLastChild(MbElement.TYPE_NAME, MbJSON.DATA_ELEMENT_NAME, null);
//		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "algo", getAlgo());
//		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "sub", getSub());
//		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "iss", getIss());
//		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "keyfile", getKeyFile());
//		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "exptime", getExpTime());
//
//		MbMessageAssembly newAssembly = new MbMessageAssembly(assembly, newMsg);
//      MbOutputTerminal out = getOutputTerminal("out");
//      out.propagate(newAssembly);	
		
	
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", "admin");
		claims.put("department", "engineering");

		// JWSAlgorithm Algorithm = JWSAlgorithm.parse(Algorithym);

		MbMessage newMsg = new MbMessage();
		MbElement outputRoot = newMsg.getRootElement();
		String jwtoken = null;

		try {
			jwtoken = JwtGenerator.generateJwtFromJwk(getSub(), getIss(), ExpTime , claims, JWSAlgorithm.RS256, getKeyFile());

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

}
