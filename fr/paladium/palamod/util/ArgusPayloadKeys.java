package fr.paladium.palamod.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ArgusPayloadKeys {
  private static final String encryptionKey = "p2s5v8y/B?D(G+Kb";
  
  private static final String characterEncoding = "UTF-8";
  
  private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
  
  private static final String aesEncryptionAlgorithem = "AES";
  
  public static byte[] encrypt(byte[] data) {
    try {
      Base64.Encoder encoder = Base64.getEncoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      byte[] key = "p2s5v8y/B?D(G+Kb".getBytes("UTF-8");
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(key);
      cipher.init(1, secretKey, ivparameterspec);
      return cipher.doFinal(encoder.encode(data));
    } catch (Exception E) {
      System.err.println("Encrypt Exception : " + E.getMessage());
      return null;
    } 
  }
  
  public static byte[] encrypt(byte[] key, byte[] data) {
    try {
      Base64.Encoder encoder = Base64.getEncoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(key);
      cipher.init(1, secretKey, ivparameterspec);
      return cipher.doFinal(encoder.encode(data));
    } catch (Exception E) {
      System.err.println("Encrypt Exception : " + E.getMessage());
      return null;
    } 
  }
  
  private static byte[] asLongBytes(long... l) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    for (long a : l)
      bb.putLong(a); 
    return bb.array();
  }
  
  public static byte[] decrypt(byte[] data, long a, long b) {
    try {
      long ml = a >> (int)(34L + b) << 9L;
      long po = 8948189371489L;
      byte[] recap = asLongBytes(new long[] { ml, 8948189371489L });
      Base64.Decoder decoder = Base64.getDecoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      SecretKeySpec secretKey = new SecretKeySpec(recap, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(recap);
      cipher.init(2, secretKey, ivparameterspec);
      return decoder.decode(cipher.doFinal(data));
    } catch (Exception E) {
      System.err.println("Encrypt Exception : " + E.getMessage());
      return null;
    } 
  }
  
  public static byte[] decrypt(byte[] data, byte[] arrk) {
    try {
      Base64.Decoder decoder = Base64.getDecoder();
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      SecretKeySpec secretKey = new SecretKeySpec(arrk, "AES");
      IvParameterSpec ivparameterspec = new IvParameterSpec(arrk);
      cipher.init(2, secretKey, ivparameterspec);
      return decoder.decode(cipher.doFinal(data));
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


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\ArgusPayloadKeys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */