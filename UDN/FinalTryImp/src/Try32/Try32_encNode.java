package Try32;

import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;

import java.util.HashMap;
import java.util.Map;

import com.ibm.broker.plugin.*;

public class Try32_encNode extends MbNode implements MbNodeInterface {

	String _Pro2;
	String _Pro1;
	String key;
	String value;
	Map <String, String> table = new HashMap<>();
	
	public String getKey() {
		return key;
	}

	public Map<String, String> getTable() {
		return table;
	}

	public void setTable(Map<String, String> table) {
		this.table = table;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Try32_encNode() throws MbException {
		createInputTerminal("in");
		createOutputTerminal("out");
		createOutputTerminal("failure");
	}

	public static String getNodeName() {
		return "Try32_encNode";
	}

	public String getPro2() {
		return _Pro2;
	}

	public void setPro2(String pro2) {
		_Pro2 = pro2;
	}

	public String getPro1() {
		return _Pro1;
	}

	public void setPro1(String pro1) {
		_Pro1 = pro1;
	}

	
	@Override
	public void evaluate(MbMessageAssembly assembly, MbInputTerminal in) throws MbException {

		
		MbMessage newMsg = new MbMessage();

		MbElement outRoot = newMsg.getRootElement();
		MbElement outJsonRoot = outRoot.createElementAsLastChild(MbJSON.PARSER_NAME);
		MbElement outJsonData =outJsonRoot.createElementAsLastChild(MbElement.TYPE_NAME, MbJSON.DATA_ELEMENT_NAME, null);
		
		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "pro1", getPro1());
		outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "pro2", getPro2());
		
		MbTable tableGroup = (MbTable) getUserDefinedAttribute("table");
		//int j = 0;
		String k = null;
		if (tableGroup != null) {
		    int rowCount = tableGroup.size();
		    for (int i = 0; i < rowCount; i++) {
		        tableGroup.moveToRow(i);
		        key = (String) tableGroup.getValue("key");
		        value = (String) tableGroup.getValue("value");
		        
		        k = null;
		        k = "key"+i;
				outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, k, getKey());
				k=null;
		        k = "value"+i;
				outJsonData.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "keyfile", getValue());
        
		    }
		}
		
		System.out.println(table);
		System.out.println(_Pro1);
		System.out.println(_Pro2);
		System.out.println(key);
		System.out.println(value);
		System.out.println(k);
		
    	MbMessageAssembly newAssembly = new MbMessageAssembly(assembly, newMsg);
      MbOutputTerminal out = getOutputTerminal("out");
      out.propagate(newAssembly);	
		
		
	}

}
