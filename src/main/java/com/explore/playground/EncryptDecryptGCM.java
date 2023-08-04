package com.explore.playground;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptDecryptGCM {
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final SecretKey SECRET = new SecretKeySpec("c3BhcnJhb3dfbG9jYWxfa2V5".getBytes(), "AES");
    // AES-GCM needs GCMParameterSpec
    public static String encrypt(String strToEncrypt) throws Exception {
        byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, SECRET, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        String encryptedStr = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
        System.out.println("IV: " + Base64.getEncoder().encodeToString(iv));
        return String.format("{%s}%s", Base64.getEncoder().encodeToString(iv), encryptedStr);
    }

    public static String decrypt(String strToDecrypt) throws Exception {
        Pattern p = Pattern.compile("\\{(.+)}(.+)");
        Matcher m = p.matcher(strToDecrypt);
        if (m.find() && m.groupCount() == 2) {
            String encodedIV = m.group(1);
            byte[] IV = Base64.getDecoder().decode(encodedIV.getBytes());
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, SECRET, new GCMParameterSpec(TAG_LENGTH_BIT, IV));
            return new String(cipher.doFinal(Base64.getDecoder().decode(m.group(2))));
        }
        else {
            throw new RuntimeException("IV is missing");
        }
    }

    public static void main(String[] args) throws Exception {
        String pText = "test123@test.com";
        String encryptedText = EncryptDecryptGCM.encrypt(pText);
        System.out.println(encryptedText);
        String decryptedText = EncryptDecryptGCM.decrypt(encryptedText);
        System.out.println(decryptedText);
    }
}
