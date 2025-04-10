package com.cedge.guj;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GUJ_NEFT_INWARD_LoadProperties 
{
	public static void getProperties( String propLoc, String key, String[] fileLoc, String[] ftpProv , String bankName)
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
		fileLoc[0] = properties.getProperty(key+"_IN");
		String ab = "";
		ab = bankName+"_FTP";
		ftpProv[0] = properties.getProperty(bankName+"_FTP");
		
	}
    public static void getMailProp( String propLoc, String[] toEmail, String[] ccEmail, String[] fromEmail, String[] smtpServer)
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
