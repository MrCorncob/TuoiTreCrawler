/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.analysis.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Corncob
 */
public class MD5Utility {
    public static String getMD5Hash(String raw)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte byteData[] = md.digest();
            
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Utility.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
 
        
    }
}
