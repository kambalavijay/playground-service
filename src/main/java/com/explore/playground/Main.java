package com.explore.playground;

import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        String hmacMD5Algorithm = "HmacSHA256";
        String data = "abc@test.com";
        String key = "c3BhcnJhb3dfbG9jYWxfa2V5";

        System.out.println(hmacWithApacheCommons(hmacMD5Algorithm, data, key));
        System.out.println(hmacWithApacheCommons(hmacMD5Algorithm, data, key));
        System.out.println(hmacWithApacheCommons(hmacMD5Algorithm, data, key));
        System.out.println(hmacWithApacheCommons(hmacMD5Algorithm, data, key));
    }


    public static String hmacWithApacheCommons(String algorithm, String data, String key) {
        String hmac = new HmacUtils(algorithm, key).hmacHex(data);
        return hmac;
    }
}
