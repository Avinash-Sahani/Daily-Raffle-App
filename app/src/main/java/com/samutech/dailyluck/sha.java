package com.samutech.dailyluck;

import java.security.MessageDigest;

public class sha {



public static byte[] encryptSHA(byte[] data,String shaN) throws Exception

{
    MessageDigest sha=MessageDigest.getInstance(shaN);
    sha.update(data);
    return  sha.digest();
}






///
public static String hash_hmac(String type, String value, String key)
{
    try {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance(type);
        javax.crypto.spec.SecretKeySpec secret = new javax.crypto.spec.SecretKeySpec(key.getBytes(), type);
        mac.init(secret);
        byte[] digest = mac.doFinal(value.getBytes());
        StringBuilder sb = new StringBuilder(digest.length*2);
        String s;
        for (byte b : digest){
            s = Integer.toHexString((int)(b));
            if(s.length() == 1) sb.append('0');

            sb.append(s);

        }
        String a= sb.toString();
        a=a.replace("f","");
        return a;
    } catch (Exception e) {
        android.util.Log.v("TAG","Exception ["+e.getMessage()+"]", e);
    }
    return "";
}
}
