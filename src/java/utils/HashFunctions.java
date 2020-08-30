/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyen
 */
public class HashFunctions {
    static Logger logger = Logger.getLogger(Logger.class.getName());
     public static String getHash(byte[] inputBytes, String algorithm){
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
            
        } catch (Exception e) {
               logger.error("HashFunction_Exception " + e.getMessage());
        }
        
        
        return hashValue;
    }
}
