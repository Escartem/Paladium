package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.allatori.annotations.StringEncryption;
import com.allatori.annotations.StringEncryptionType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
@StringEncryption("maximum")
@StringEncryptionType("strong")
public class LoaderKeys {
  private static final String encryptionKey = "p2s5v8y/B?D(G+Kb";
  
  private static final String characterEncoding = "UTF-8";
  
  private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
  
  private static final String aesEncryptionAlgorithem = "AES";
  
  public static byte[] encrypt(byte[] data) {
    try {
      byte[] rita = Base64.getEncoder().encode(data);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DeflaterOutputStream dos = new DeflaterOutputStream(baos);
      try {
        dos.write(rita);
        dos.flush();
        dos.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
      Base64.Encoder encoder = Base64.getEncoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      byte[] key = "p2s5v8y/B?D(G+Kb".getBytes("UTF-8");
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(key);
      cipher.init(1, secretKey, ivparameterspec);
      return cipher.doFinal(encoder.encode(baos.toByteArray()));
    } catch (Exception E) {
      System.err.println("Encrypt Exception : " + E.getMessage());
      return null;
    } 
  }
  
  public static byte[] decrypt(byte[] data) {
    try {
      Base64.Decoder decoder = Base64.getDecoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      byte[] key = "p2s5v8y/B?D(G+Kb".getBytes("UTF-8");
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(key);
      cipher.init(2, secretKey, ivparameterspec);
      return decoder.decode(cipher.doFinal(data));
    } catch (Exception E) {
      System.err.println("decrypt Exception : " + E.getMessage());
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\LoaderKeys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */