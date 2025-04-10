package com.cedge.guj.rtgs;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;

public class FORM_XML extends MbJavaComputeNode 
{
	public void evaluate(MbMessageAssembly inAssembly) throws MbException 
	{
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
	
		MbMessage outMessage = new MbMessage();
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly , outMessage);

		try 
		{
			copyMessageHeaders(inMessage, outMessage);
			//	--utr = inXML.getFirstElementByPath("RequestPayload/AppHdr/BizMsgIdr").getValueAsString();
			String msgType = "" , bizMsgIdr = "", evtParam = "" ;
			MbElement msgDefIdr = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/AppHdr/MsgDefIdr");
			if (msgDefIdr != null) 
			{
				msgType = msgDefIdr.getValueAsString();
				MbElement localEnv = outAssembly.getLocalEnvironment().getRootElement();
				MbElement bankDetails = localEnv.createElementAsLastChild(MbXMLNSC.FOLDER, "BankDetails", null);
				bankDetails.createElementAsLastChild(MbXMLNSC.FIELD, "MsgType", msgType);
				if (msgType.equals("admi.004.001.01")) 
				{
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/AppHdr/BizMsgIdr");
					MbElement evtParamEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/SysEvtNtfnV01/EvtInf/EvtParam");
					if (bizMsgIdrEle != null && evtParamEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						evtParam = evtParamEle.getValueAsString();
						bankDetails.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
						bankDetails.createElementAsLastChild(MbXMLNSC.FIELD, "EvtParam", evtParam);
					}
				}
				
				//addMqrfh2MsgType(inMessage, outMessage ,msgType);
			}
			
			MbElement root = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document").copy();
			MbElement outRoot = outMessage.getRootElement().createElementAsLastChild("XMLNSC");
			outRoot.addAsLastChild(root);
			out.propagate(outAssembly);
		} 
		catch (MbException e) 
		{
			throw e;
		}
		catch (RuntimeException e) 
		{
			throw e;
		}
		catch (Exception e) 
		{
			throw new MbUserException(this, "evaluate()", "", "", e.toString(), null);
		}
		//out.propagate(outAssembly);
	}

	public void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage) throws MbException 
	{
		MbElement outRoot = outMessage.getRootElement();
		MbElement header = inMessage.getRootElement().getFirstChild();
		while (header != null && header.getNextSibling() != null)
		{
			outRoot.addAsLastChild(header.copy());
			header = header.getNextSibling();
		}
	}
	public void addMqrfh2MsgType(MbMessage inMessage, MbMessage outMessage , String msgType) throws Exception
	{
		addMqrfh2(inMessage, outMessage);
		MbElement bankDetails = outMessage.getRootElement().getFirstElementByPath("/MQRFH2/usr/BankDetails");
		if (bankDetails == null) 
		{
			MbElement mqrfh2 = outMessage.getRootElement().getFirstElementByPath("/MQRFH2/usr");
			MbElement bankdetails = mqrfh2.createElementAsLastChild(MbElement.TYPE_NAME, "BankDetails", null);
			MbElement msgtype = bankdetails.createElementAsLastChild(MbElement.TYPE_NAME, "MsgType", null);
			msgtype.createElementAsLastChild(MbElement.TYPE_VALUE, null, msgType);
		}
		else 
		{
			MbElement msgtype = outMessage.getRootElement().getFirstElementByPath("/MQRFH2/usr/BankDetails/MsgType").getLastChild();
			msgtype.setValue(msgType);
		}
		
	}
	public void addMqrfh2(MbMessage inMsg,MbMessage outMsg) throws MbException
	{
		MbElement mqrfh2 = outMsg.getRootElement().getFirstElementByPath("/MQRFH2");
		if (mqrfh2 == null) 
		{
			MbElement root = outMsg.getRootElement();
			MbElement body = root.getLastChild();

			MbElement format = body.getFirstElementByPath("/MQMD/Format");
			if (format!=null) 
			{
				format.setValue("MQHRF2");
			}
			MbElement rfh2 = body.createElementAfter("MQHRF2");
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Version", new Integer(2));
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Format",null);
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Encoding", new Integer(546));
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "CodedCharSetId", new Integer(850));
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Flags", new Integer(0));
			rfh2.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "NameValueCCSID", new Integer(1208));

			MbElement usr = outMsg.getRootElement().getFirstElementByPath("/MQRFH2/usr");
			if (usr==null) 
			{
				MbElement mqrfh2v1 = outMsg.getRootElement().getFirstElementByPath("/MQRFH2");
				mqrfh2v1.createElementAsLastChild(MbElement.TYPE_NAME, "usr", null);
			}

		}
	}
}
