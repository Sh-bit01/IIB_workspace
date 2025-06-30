package Try32;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import java.util.Vector;
import com.ibm.broker.config.appdev.NodePropertyRow;
import com.ibm.broker.config.appdev.NodePropertyTable;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>encNodeUDN</I> instance</p>
 * <p></p>
 */
public class encNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "Try32/encNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/FinalTry/icons/full/obj16/Try32/enc.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/FinalTry/icons/full/obj30/Try32/enc.gif";

	protected final static String PROPERTY_PRO1 = "Pro1";
	protected final static String PROPERTY_PRO2 = "Pro2";

	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(encNodeUDN.PROPERTY_PRO1,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"Try32/enc",	"FinalTry"),
			new NodeProperty(encNodeUDN.PROPERTY_PRO2,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"Try32/enc",	"FinalTry")
		};
	}

	/**
	 * <I>Table</I> instance contains <I>tableRow</I> rows
	 * <pre>
	 * Table name = table
	 * Row names = tableRow
	 * </pre>
	 */
	public class Table extends NodePropertyTable {
		private static final long serialVersionUID = 1L;

		protected static final String TABLE_NAME = "table";

		private Table() {
			this.name = TABLE_NAME;
		}
		@SuppressWarnings("unchecked")
		@Override
		public Vector<tableRow> getRows() {
			return (Vector<tableRow>) super.getRows();
		}

		public tableRow createRow() {
			return new tableRow();
		}

	/**
	 * Adds a tableRow to the table
	 * @param row tableRow ; the row to add to the table
	 */ 
		public void addRow(tableRow row) {
			rows.add(row);
		}
	/**
	 * Remove a tableRow from the table
	 * @param row tableRow ; the row to remove from the table
	 */ 
		public void removeRow(tableRow row) {
			rows.remove(row);
		}
	}

	/**
	 * <I>tableRow</I> is used by <I>Table</I> instance
	 * <pre>
	 * Table name = table
	 * Row names = tableRow
	 * </pre>
	 */
	public class tableRow extends NodePropertyRow {
	private static final long serialVersionUID = 1L;

	protected static final String ROW_NAME = "tableRow";
	protected final static String PROPERTY_KEY = "key";
	protected final static String PROPERTY_VALUE = "value";

		private tableRow() {
			this.name = ROW_NAME;
			this.nodeProperties = getNodeProperties();
		}

	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(PROPERTY_KEY,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"Try32/enc",	"FinalTry"),
			new NodeProperty(PROPERTY_VALUE,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"Try32/enc",	"FinalTry")
		};
	}


	/**
	 * Set the <I>tableRow</I> "<I>key</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>key</I>"
	 */
	public void setKey(String value) {
		setProperty(PROPERTY_KEY, value);
	}

	/**
	 * Get the <I>tableRow</I> "<I>key</I>" property
	 * 
	 * @return String; the value of the property "<I>key</I>"
	 */
	public String getKey() {
		return (String)getPropertyValue(PROPERTY_KEY);
	}

	/**
	 * Set the <I>tableRow</I> "<I>value</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>value</I>"
	 */
	public void setValue(String value) {
		setProperty(PROPERTY_VALUE, value);
	}

	/**
	 * Get the <I>tableRow</I> "<I>value</I>" property
	 * 
	 * @return String; the value of the property "<I>value</I>"
	 */
	public String getValue() {
		return (String)getPropertyValue(PROPERTY_VALUE);
	}
}
	/**
	 * Retrieve the table table for the node <I>encNodeUDN</I>
	 * @return Table instance which contains tableRow rows

	 */
	public Table getTable() {
		for (int i = 0; i < nodePropertyTables.size(); i++) {
			if (nodePropertyTables.get(i) instanceof Table)
				return (Table)nodePropertyTables.get(i);
			}
		return null;
	}

	public encNodeUDN() {
		nodePropertyTables.add(new Table());
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
	 * Set the <I>encNodeUDN</I> "<I>Pro1</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Pro1</I>"
	 */
	public encNodeUDN setPro1(String value) {
		setProperty(encNodeUDN.PROPERTY_PRO1, value);
		return this;
	}

	/**
	 * Get the <I>encNodeUDN</I> "<I>Pro1</I>" property
	 * 
	 * @return String; the value of the property "<I>Pro1</I>"
	 */
	public String getPro1() {
		return (String)getPropertyValue(encNodeUDN.PROPERTY_PRO1);
	}

	/**
	 * Set the <I>encNodeUDN</I> "<I>Pro2</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Pro2</I>"
	 */
	public encNodeUDN setPro2(String value) {
		setProperty(encNodeUDN.PROPERTY_PRO2, value);
		return this;
	}

	/**
	 * Get the <I>encNodeUDN</I> "<I>Pro2</I>" property
	 * 
	 * @return String; the value of the property "<I>Pro2</I>"
	 */
	public String getPro2() {
		return (String)getPropertyValue(encNodeUDN.PROPERTY_PRO2);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "enc";
		return retVal;
	};
}
