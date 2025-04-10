package com.cedge.guj.rtgs;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;

public class GET_MSGID extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		try 
		{
			MbMessage outMessage = new MbMessage(inMessage);
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			
			String msgType = "" , bizMsgIdr = "", msgId = "" ;
			MbElement msgDefIdr = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/AppHdr/MsgDefIdr");
			if (msgDefIdr != null) 
			{
				msgType = msgDefIdr.getValueAsString();
				MbElement environment = outAssembly.getGlobalEnvironment().getRootElement();
//				MbElement localEnv = outAssembly.getLocalEnvironment().getRootElement();
				MbElement uniqueId = environment.createElementAsLastChild(MbXMLNSC.FOLDER, "UniqueId", null);
//				uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "MsgType", msgType);
				
				if (msgType.equals("pacs.008.001.03")) 
				{//FIToFICstmrCdtTrf.GrpHdr.MsgId;
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/FIToFICstmrCdtTrf/GrpHdr/MsgId");
					if (bizMsgIdrEle != null) 
					{//UniqueId.BizMsgIdr
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
				else if (msgType.equals("pacs.009.001.03")) 
				{//FinInstnCdtTrf.GrpHdr.MsgId;
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/FinInstnCdtTrf/GrpHdr/MsgId");
					if (bizMsgIdrEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
				else if (msgType.equals("pacs.004.001.03")) 
				{
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/PmtRtr/GrpHdr/MsgId");
					if (bizMsgIdrEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
				else if (msgType.equals("camt.054.001.03")) 
				{
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/BkToCstmrDbtCdtNtfctn/GrpHdr/MsgId");
					if (bizMsgIdrEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
				else if (msgType.equals("pacs.002.001.04")) //pacs.002.001.04
				{
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/Document/FIToFIPmtStsRpt/GrpHdr/MsgId");
					if (bizMsgIdrEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
				else if (msgType.equals("admi.004.001.01")) 
				{
					MbElement bizMsgIdrEle = inMessage.getRootElement().getLastChild().getFirstElementByPath("/XMLNSC/RequestPayload/AppHdr/BizMsgIdr");
					if (bizMsgIdrEle != null) 
					{
						bizMsgIdr = bizMsgIdrEle.getValueAsString();
						uniqueId.createElementAsLastChild(MbXMLNSC.FIELD, "BizMsgIdr", bizMsgIdr);
					}
				}
			}
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
		out.propagate(outAssembly);
	}
}
