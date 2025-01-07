package udp;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbPolicy;

public class Access {

	 public static String getPolicyProperty(String policyName, String propertyName ) {
		    String resultPropertyValue = null; 
		    try {
		      MbPolicy myPol = MbPolicy.getPolicy("UserDefined", policyName);
		      if (myPol != null) {
		        resultPropertyValue = myPol.getPropertyValueAsString(propertyName);
		      }
		    } catch (MbException mbe) {
		      System.out.println("Exception caught trying to find UserDefined policy with name '"+policyName+"'. Exception details: '"+mbe.toString()+"'"); 
		    }
		    return resultPropertyValue;
		} }