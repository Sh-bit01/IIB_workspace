package com.cedge.signer;


import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;

import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
public class Neft_Sign {

	 static String finalsigned;
//	 private static final String systemLineSeparator = System.getProperty("line.separator");
	    public Neft_Sign()
	    {
	    }
	    @SuppressWarnings("rawtypes")
		public static String sign(String pfxfile, String password, String sourcefile)
	    {
	        String STORENAME = pfxfile;
	        String STOREPASS = password;
	        String INFILE = sourcefile;
	        try
	        {
	            KeyStore clientStore = KeyStore.getInstance("PKCS12");
	            clientStore.load(new FileInputStream(STORENAME), STOREPASS.toCharArray());
	            Enumeration aliases = clientStore.aliases();
	            String aliaz = "";
	            while(aliases.hasMoreElements()) 
	            {
	                aliaz = (String)aliases.nextElement();
	                if(clientStore.isKeyEntry(aliaz))
	                {
	                    break;
	                }
	            }
	            Security.addProvider(new BouncyCastleProvider());
	            char pass[] = STOREPASS.toCharArray();
	            PrivateKey key = (PrivateKey)clientStore.getKey(aliaz, pass);
	            Certificate chain[] = clientStore.getCertificateChain(aliaz);
	            CertStore certsAndCRLs = CertStore.getInstance("Collection", new CollectionCertStoreParameters(Arrays.asList(new Certificate[] {
	                chain[0]
	            })), "BC");
	            X509Certificate cert = (X509Certificate)chain[0];
	            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
	            gen.addSigner(key, cert, CMSSignedDataGenerator.DIGEST_SHA1);
	            gen.addCertificatesAndCRLs(certsAndCRLs);
	            org.bouncycastle.cms.CMSProcessable data = new CMSProcessableByteArray(INFILE.getBytes());
	            CMSSignedData signed = gen.generate(data, false, "BC");
	            signed = new CMSSignedData(data, signed.getEncoded());
	            byte signature[] = signed.getContentInfo().getDEREncoded();
//	            finalsigned= Base64Coder.encodeLines(signature);
	            finalsigned= Base64Coder.encodeLines(signature);
//	            finalsigned = Base64Coder.encodeLines(signature, 0, signature.length, 64, systemLineSeparator);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.toString());
	        }
	        return finalsigned;
	    }
	}