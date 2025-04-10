package com.cedge.signer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class Signatureflow_JavaCompute {

	public static void getProperties( String propLoc, String pass,String sourcefile,String [] signature) 
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
		Neft_Sign n = new Neft_Sign();
		String signed = "";
        String currentLine = "";
        StringBuffer strBuff = null;
        BufferedReader br =null;
 		strBuff = new StringBuffer();
 	  
		
//		try {
//			br = new BufferedReader(new FileReader(sourcefile));
//			try {
//				while ((currentLine = br.readLine()) != null) {
//					if (!currentLine.contains("-}")) {
//						currentLine=currentLine.replaceAll("\\s+$", " ");
//						currentLine=currentLine.replaceAll("^\\s+$", "");
//						if("".equals(currentLine))
//						{
//							continue;
//						}
//						strBuff.append(currentLine).append("\n");
//					}
//				}
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String FullMsg = strBuff.toString();
//		if (!FullMsg.endsWith("-}")) {
//			FullMsg = FullMsg.trim().concat("\n-}");
//		}
//		String tempmesg[] = FullMsg.split("\\{4:");
////	    header = tempmesg[0];
//		String Block4Msg = tempmesg[1];
//		Block4Msg ="{4:"+Block4Msg;
    	try
        { 
        signed  = Neft_Sign.sign(propLoc, pass, sourcefile);
        System.out.println("Signature of the message : "+signed.trim());
        signature[0] = signed;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

}
