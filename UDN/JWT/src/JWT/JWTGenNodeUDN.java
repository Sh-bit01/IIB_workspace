package JWT;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import java.util.Vector;
import com.ibm.broker.config.appdev.NodePropertyRow;
import com.ibm.broker.config.appdev.NodePropertyTable;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>JWTGenNodeUDN</I> instance</p>
 * <p></p>
 */
public class JWTGenNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "JWT/JWTGenNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/JWT/icons/full/obj16/JWT/JWTGen.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/JWT/icons/full/obj30/JWT/JWTGen.gif";

	protected final static String PROPERTY_ALGO = "algo";
	protected final static String PROPERTY_SUB = "sub";
	protected final static String PROPERTY_ISS = "iss";
	protected final static String PROPERTY_KEYFILE = "keyFile";
	protected final static String PROPERTY_EXPTIME = "expTime";


	/**
	 * <I>ENUM_JWTGEN_ALGO</I>
	 * <pre>
	 * ENUM_JWTGEN_ALGO.RS256 = RS256
	 * ENUM_JWTGEN_ALGO.HS256 = HS256
	 * ENUM_JWTGEN_ALGO.ES256 = ES256
	 * </pre>
	 */
	public static class ENUM_JWTGEN_ALGO {
		private String value;

		public static final ENUM_JWTGEN_ALGO RS256 = new ENUM_JWTGEN_ALGO("RS256");
		public static final ENUM_JWTGEN_ALGO HS256 = new ENUM_JWTGEN_ALGO("HS256");
		public static final ENUM_JWTGEN_ALGO ES256 = new ENUM_JWTGEN_ALGO("ES256");

		protected ENUM_JWTGEN_ALGO(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_JWTGEN_ALGO getEnumFromString(String enumValue) {
			ENUM_JWTGEN_ALGO enumConst = ENUM_JWTGEN_ALGO.RS256;
			if (ENUM_JWTGEN_ALGO.HS256.value.equals(enumValue)) enumConst = ENUM_JWTGEN_ALGO.HS256;
			if (ENUM_JWTGEN_ALGO.ES256.value.equals(enumValue)) enumConst = ENUM_JWTGEN_ALGO.ES256;
			return enumConst;
		}

		public static String[] values = new String[]{ "RS256", "HS256", "ES256" };

	}
	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(JWTGenNodeUDN.PROPERTY_ALGO,		NodeProperty.Usage.OPTIONAL,	true,	NodeProperty.Type.ENUMERATION, "RS256", ENUM_JWTGEN_ALGO.class,"","",	"JWT/JWTGen",	"JWT"),
			new NodeProperty(JWTGenNodeUDN.PROPERTY_SUB,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"JWT/JWTGen",	"JWT"),
			new NodeProperty(JWTGenNodeUDN.PROPERTY_ISS,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"JWT/JWTGen",	"JWT"),
			new NodeProperty(JWTGenNodeUDN.PROPERTY_KEYFILE,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"JWT/JWTGen",	"JWT"),
			new NodeProperty(JWTGenNodeUDN.PROPERTY_EXPTIME,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.INTEGER, "43222222","","",	"JWT/JWTGen",	"JWT")
		};
	}

	/**
	 * <I>ClaimsTable</I> instance contains <I>claimsRow</I> rows
	 * <pre>
	 * Table name = claims
	 * Row names = claimsRow
	 * </pre>
	 */
	public class ClaimsTable extends NodePropertyTable {
		private static final long serialVersionUID = 1L;

		protected static final String TABLE_NAME = "claims";

		private ClaimsTable() {
			this.name = TABLE_NAME;
		}
		@SuppressWarnings("unchecked")
		@Override
		public Vector<claimsRow> getRows() {
			return (Vector<claimsRow>) super.getRows();
		}

		public claimsRow createRow() {
			return new claimsRow();
		}

	/**
	 * Adds a claimsRow to the table
	 * @param row claimsRow ; the row to add to the table
	 */ 
		public void addRow(claimsRow row) {
			rows.add(row);
		}
	/**
	 * Remove a claimsRow from the table
	 * @param row claimsRow ; the row to remove from the table
	 */ 
		public void removeRow(claimsRow row) {
			rows.remove(row);
		}
	}

	/**
	 * <I>claimsRow</I> is used by <I>ClaimsTable</I> instance
	 * <pre>
	 * Table name = claims
	 * Row names = claimsRow
	 * </pre>
	 */
	public class claimsRow extends NodePropertyRow {
	private static final long serialVersionUID = 1L;

	protected static final String ROW_NAME = "claimsRow";
	protected final static String PROPERTY_KEY = "key";
	protected final static String PROPERTY_VALUE = "value";

		private claimsRow() {
			this.name = ROW_NAME;
			this.nodeProperties = getNodeProperties();
		}

	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(PROPERTY_KEY,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"JWT/JWTGen",	"JWT"),
			new NodeProperty(PROPERTY_VALUE,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"JWT/JWTGen",	"JWT")
		};
	}


	/**
	 * Set the <I>claimsRow</I> "<I>key</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>key</I>"
	 */
	public void setKey(String value) {
		setProperty(PROPERTY_KEY, value);
	}

	/**
	 * Get the <I>claimsRow</I> "<I>key</I>" property
	 * 
	 * @return String; the value of the property "<I>key</I>"
	 */
	public String getKey() {
		return (String)getPropertyValue(PROPERTY_KEY);
	}

	/**
	 * Set the <I>claimsRow</I> "<I>value</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>value</I>"
	 */
	public void setValue(String value) {
		setProperty(PROPERTY_VALUE, value);
	}

	/**
	 * Get the <I>claimsRow</I> "<I>value</I>" property
	 * 
	 * @return String; the value of the property "<I>value</I>"
	 */
	public String getValue() {
		return (String)getPropertyValue(PROPERTY_VALUE);
	}
}
	/**
	 * Retrieve the claims table for the node <I>JWTGenNodeUDN</I>
	 * @return ClaimsTable instance which contains claimsRow rows

	 */
	public ClaimsTable getClaimsTable() {
		for (int i = 0; i < nodePropertyTables.size(); i++) {
			if (nodePropertyTables.get(i) instanceof ClaimsTable)
				return (ClaimsTable)nodePropertyTables.get(i);
			}
		return null;
	}

	public JWTGenNodeUDN() {
		nodePropertyTables.add(new ClaimsTable());
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_FAILURE = new OutputTerminal(this,"OutTerminal.failure");
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
	 * Set the <I>JWTGenNodeUDN</I> "<I>algo</I>" property
	 * 
	 * @param value ENUM_JWTGEN_ALGO ; the value to set the property "<I>algo</I>"
	 */
	public JWTGenNodeUDN setAlgo(ENUM_JWTGEN_ALGO value) {
		setProperty(JWTGenNodeUDN.PROPERTY_ALGO, value.toString());
		return this;
	}

	/**
	 * Get the <I>JWTGenNodeUDN</I> "<I>algo</I>" property
	 * 
	 * @return ENUM_JWTGEN_ALGO; the value of the property "<I>algo</I>"
	 */
	public ENUM_JWTGEN_ALGO getAlgo() {
		ENUM_JWTGEN_ALGO value = ENUM_JWTGEN_ALGO.getEnumFromString((String)getPropertyValue(JWTGenNodeUDN.PROPERTY_ALGO));
		return value;
	}

	/**
	 * Set the <I>JWTGenNodeUDN</I> "<I>sub</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>sub</I>"
	 */
	public JWTGenNodeUDN setSub(String value) {
		setProperty(JWTGenNodeUDN.PROPERTY_SUB, value);
		return this;
	}

	/**
	 * Get the <I>JWTGenNodeUDN</I> "<I>sub</I>" property
	 * 
	 * @return String; the value of the property "<I>sub</I>"
	 */
	public String getSub() {
		return (String)getPropertyValue(JWTGenNodeUDN.PROPERTY_SUB);
	}

	/**
	 * Set the <I>JWTGenNodeUDN</I> "<I>iss</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>iss</I>"
	 */
	public JWTGenNodeUDN setIss(String value) {
		setProperty(JWTGenNodeUDN.PROPERTY_ISS, value);
		return this;
	}

	/**
	 * Get the <I>JWTGenNodeUDN</I> "<I>iss</I>" property
	 * 
	 * @return String; the value of the property "<I>iss</I>"
	 */
	public String getIss() {
		return (String)getPropertyValue(JWTGenNodeUDN.PROPERTY_ISS);
	}

	/**
	 * Set the <I>JWTGenNodeUDN</I> "<I>keyFile</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>keyFile</I>"
	 */
	public JWTGenNodeUDN setKeyFile(String value) {
		setProperty(JWTGenNodeUDN.PROPERTY_KEYFILE, value);
		return this;
	}

	/**
	 * Get the <I>JWTGenNodeUDN</I> "<I>keyFile</I>" property
	 * 
	 * @return String; the value of the property "<I>keyFile</I>"
	 */
	public String getKeyFile() {
		return (String)getPropertyValue(JWTGenNodeUDN.PROPERTY_KEYFILE);
	}

	/**
	 * Set the <I>JWTGenNodeUDN</I> "<I>expTime</I>" property
	 * 
	 * @param value int ; the value to set the property "<I>expTime</I>"
	 */
	public JWTGenNodeUDN setExpTime(int value) {
		setProperty(JWTGenNodeUDN.PROPERTY_EXPTIME, Integer.toString(value));
		return this;
	}

	/**
	 * Get the <I>JWTGenNodeUDN</I> <I>expTime</I> property
	 * 
	 * @return int; the value of the property "<I>expTime</I>"
	 */
	public int getExpTime() {
		String value = (String)getPropertyValue(JWTGenNodeUDN.PROPERTY_EXPTIME);
		return Integer.valueOf(value).intValue();
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "JWTGen";
		return retVal;
	};
}
