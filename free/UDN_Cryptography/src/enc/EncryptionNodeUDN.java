package enc;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>EncryptionNodeUDN</I> instance</p>
 * <p></p>
 */
public class EncryptionNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "enc/EncryptionNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/Cryptography/icons/full/obj16/enc/Encryption.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/Cryptography/icons/full/obj30/enc/Encryption.gif";

	protected final static String PROPERTY_ALGO = "Algo";
	protected final static String PROPERTY_KEY = "Key";


	/**
	 * <I>ENUM_ENCRYPTION_ALGO</I>
	 * <pre>
	 * ENUM_ENCRYPTION_ALGO.AES = AES
	 * ENUM_ENCRYPTION_ALGO.DES = DES
	 * </pre>
	 */
	public static class ENUM_ENCRYPTION_ALGO {
		private String value;

		public static final ENUM_ENCRYPTION_ALGO AES = new ENUM_ENCRYPTION_ALGO("AES");
		public static final ENUM_ENCRYPTION_ALGO DES = new ENUM_ENCRYPTION_ALGO("DES");

		protected ENUM_ENCRYPTION_ALGO(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_ENCRYPTION_ALGO getEnumFromString(String enumValue) {
			ENUM_ENCRYPTION_ALGO enumConst = ENUM_ENCRYPTION_ALGO.AES;
			if (ENUM_ENCRYPTION_ALGO.DES.value.equals(enumValue)) enumConst = ENUM_ENCRYPTION_ALGO.DES;
			return enumConst;
		}

		public static String[] values = new String[]{ "AES", "DES" };

	}
	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(EncryptionNodeUDN.PROPERTY_ALGO,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.ENUMERATION, "AES", ENUM_ENCRYPTION_ALGO.class,"","",	"enc/Encryption",	"Cryptography"),
			new NodeProperty(EncryptionNodeUDN.PROPERTY_KEY,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"enc/Encryption",	"Cryptography")
		};
	}

	public EncryptionNodeUDN() {
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_FAILURE = new OutputTerminal(this,"OutTerminal.Failure");
	public final OutputTerminal OUTPUT_TERMINAL_OUT = new OutputTerminal(this,"OutTerminal.out");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
			OUTPUT_TERMINAL_FAILURE,
			OUTPUT_TERMINAL_OUT
		};
	}

	@Override
	public String getTypeName() {
		return NODE_TYPE_NAME;
	}

	protected String getGraphic16() {
		return NODE_GRAPHIC_16;
	}

	protected String getGraphic32() {
		return NODE_GRAPHIC_32;
	}

	/**
	 * Set the <I>EncryptionNodeUDN</I> "<I>Algo</I>" property
	 * 
	 * @param value ENUM_ENCRYPTION_ALGO ; the value to set the property "<I>Algo</I>"
	 */
	public EncryptionNodeUDN setAlgo(ENUM_ENCRYPTION_ALGO value) {
		setProperty(EncryptionNodeUDN.PROPERTY_ALGO, value.toString());
		return this;
	}

	/**
	 * Get the <I>EncryptionNodeUDN</I> "<I>Algo</I>" property
	 * 
	 * @return ENUM_ENCRYPTION_ALGO; the value of the property "<I>Algo</I>"
	 */
	public ENUM_ENCRYPTION_ALGO getAlgo() {
		ENUM_ENCRYPTION_ALGO value = ENUM_ENCRYPTION_ALGO.getEnumFromString((String)getPropertyValue(EncryptionNodeUDN.PROPERTY_ALGO));
		return value;
	}

	/**
	 * Set the <I>EncryptionNodeUDN</I> "<I>Key</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Key</I>"
	 */
	public EncryptionNodeUDN setKey(String value) {
		setProperty(EncryptionNodeUDN.PROPERTY_KEY, value);
		return this;
	}

	/**
	 * Get the <I>EncryptionNodeUDN</I> "<I>Key</I>" property
	 * 
	 * @return String; the value of the property "<I>Key</I>"
	 */
	public String getKey() {
		return (String)getPropertyValue(EncryptionNodeUDN.PROPERTY_KEY);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "Encryption";
		return retVal;
	};
}
