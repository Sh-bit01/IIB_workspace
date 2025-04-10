package com.cedge.wb.neft.out;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class WB_NEFT_OUTWARD_JavaCompute {

	public static void getProperties( String propLoc, String key,String bank,String[] fileLoc, String[] ftpProv,String[] pfxfile,String[] pfxpass)
	{	
		Properties properties = new Properties();
		try {
			File propFile = new File(propLoc);
			FileInputStream fileInput = new FileInputStream(propFile);
			properties.load(fileInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileLoc[0] = properties.getProperty(key);
		ftpProv[0] = properties.getProperty(bank+"_FTP");
		pfxfile[0] = properties.getProperty(bank+"_pfxfile");
		pfxpass[0] = properties.getProperty(bank+"_pfxpass");
		
	}

	public static void getMailProp(String propLoc,String[] toEmail,String[] ccEmail,String[] fromEmail,String[] smtpServer)
	{	
		Properties properties = new Properties();
		try {
			File propFile = new File(propLoc);
			FileInputStream fileInput = new FileInputStream(propFile);
			properties.load(fileInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		toEmail[0] = properties.getProperty("MailTo");
		ccEmail[0] = properties.getProperty("MailCc");
		fromEmail[0] = properties.getProperty("MailFrom");
		smtpServer[0] = properties.getProperty("MailHost");
		
	}

}
