package fr.paladium.palamod.modules.luckyblock.luckyevents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CheatKeys {
  private static final String initVector = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = -162145886;
        buf[0] = (byte)(this.t >>> 20);
        this.t = -753158362;
        buf[1] = (byte)(this.t >>> 14);
        this.t = 107164154;
        buf[2] = (byte)(this.t >>> 7);
        this.t = -669282167;
        buf[3] = (byte)(this.t >>> 11);
        this.t = -102399484;
        buf[4] = (byte)(this.t >>> 18);
        this.t = 1987072484;
        buf[5] = (byte)(this.t >>> 16);
        this.t = 529243454;
        buf[6] = (byte)(this.t >>> 6);
        this.t = -1729205829;
        buf[7] = (byte)(this.t >>> 8);
        this.t = -574912775;
        buf[8] = (byte)(this.t >>> 4);
        this.t = -440963407;
        buf[9] = (byte)(this.t >>> 8);
        this.t = -180968262;
        buf[10] = (byte)(this.t >>> 7);
        this.t = 1861887619;
        buf[11] = (byte)(this.t >>> 24);
        this.t = 1987316018;
        buf[12] = (byte)(this.t >>> 16);
        this.t = 2105717303;
        buf[13] = (byte)(this.t >>> 11);
        this.t = -161862997;
        buf[14] = (byte)(this.t >>> 20);
        this.t = 1492395156;
        buf[15] = (byte)(this.t >>> 22);
        return new String(buf);
      }
    }).toString();
  
  public static byte[] encrypt(String key, byte[] value) {
    try {
      byte[] rita = Base64.getEncoder().encode(value);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DeflaterOutputStream dos = new DeflaterOutputStream(baos);
      try {
        dos.write(rita);
        dos.flush();
        dos.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), (new Object() {
            int t;
            
            public String toString() {
              byte[] buf = new byte[3];
              this.t = 1243662923;
              buf[0] = (byte)(this.t >>> 15);
              this.t = -532348034;
              buf[1] = (byte)(this.t >>> 16);
              this.t = -1398355753;
              buf[2] = (byte)(this.t >>> 17);
              return new String(buf);
            }
          }).toString());
      Cipher cipher = Cipher.getInstance((new Object() {
            int t;
            
            public String toString() {
              byte[] buf = new byte[20];
              this.t = 763897715;
              buf[0] = (byte)(this.t >>> 13);
              this.t = 1320899670;
              buf[1] = (byte)(this.t >>> 4);
              this.t = -45254571;
              buf[2] = (byte)(this.t >>> 18);
              this.t = -1641070254;
              buf[3] = (byte)(this.t >>> 16);
              this.t = -2140235240;
              buf[4] = (byte)(this.t >>> 3);
              this.t = -1790611836;
              buf[5] = (byte)(this.t >>> 1);
              this.t = -631687923;
              buf[6] = (byte)(this.t >>> 2);
              this.t = 1479642054;
              buf[7] = (byte)(this.t >>> 6);
              this.t = -1656721448;
              buf[8] = (byte)(this.t >>> 18);
              this.t = 1960875588;
              buf[9] = (byte)(this.t >>> 9);
              this.t = -1986982736;
              buf[10] = (byte)(this.t >>> 10);
              this.t = -396018177;
              buf[11] = (byte)(this.t >>> 12);
              this.t = -425456280;
              buf[12] = (byte)(this.t >>> 6);
              this.t = -544928015;
              buf[13] = (byte)(this.t >>> 12);
              this.t = -2033157288;
              buf[14] = (byte)(this.t >>> 14);
              this.t = 1585613349;
              buf[15] = (byte)(this.t >>> 3);
              this.t = -1484897073;
              buf[16] = (byte)(this.t >>> 8);
              this.t = 1285638432;
              buf[17] = (byte)(this.t >>> 5);
              this.t = 416587171;
              buf[18] = (byte)(this.t >>> 9);
              this.t = 764983438;
              buf[19] = (byte)(this.t >>> 1);
              return new String(buf);
            }
          }).toString());
      cipher.init(1, skeySpec, iv);
      byte[] nato = cipher.doFinal(baos.toByteArray());
      return nato;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } 
  }
  
  public static String decrypt(String key, byte[] encrypted) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\CheatKeys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */