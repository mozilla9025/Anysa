package app.anysa.crypto;

import android.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class RSACipher {

    public static KeyPair generateKeys() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static String encrypt(String clearText, PublicKey key) throws Exception {
        Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding", "BC");
        c.init(Cipher.ENCRYPT_MODE, key, new OAEPParameterSpec("SHA-256", "MGF1",
                MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        byte[] encodedBytes = Base64.encode(c.doFinal(clearText.getBytes("UTF-8")), Base64.DEFAULT);
        String cipherText = new String(encodedBytes, "UTF-8");
        return cipherText;
    }

    public static String decrypt(String base64cypherText, PrivateKey key) throws Exception {
        Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
        c.init(Cipher.DECRYPT_MODE, key, new OAEPParameterSpec("SHA-256",
                "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        byte[] decodedBytes = c.doFinal(Base64.decode(base64cypherText.getBytes("UTF-8"), Base64.DEFAULT));
        String clearText = new String(decodedBytes, "UTF-8");
        return clearText;
    }

    public static String publicKeyToPem(PublicKey publicKey) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        return "-----BEGIN PUBLIC KEY-----" +
                new String(Base64.encode(x509EncodedKeySpec.getEncoded(), Base64.NO_WRAP), "UTF-8") +
                "-----END PUBLIC KEY-----";
    }

}
