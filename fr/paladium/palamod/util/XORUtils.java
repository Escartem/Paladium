package fr.paladium.palamod.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

public class XORUtils {
  public static byte[] asBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }
  
  public static byte[] asBytes(long l1, long l2) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(l1);
    bb.putLong(l2);
    return bb.array();
  }
  
  static String encrypt(String input, String key) {
    String plain = input.length() + "&" + input;
    return DatatypeConverter.printHexBinary(
        xor2(plain.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
  }
  
  static String decrypt(String encrypted, String key) {
    String plain = new String(xor2(DatatypeConverter.parseHexBinary(encrypted), key.getBytes(StandardCharsets.UTF_8)));
    return plain.substring(plain.indexOf('&') + 1);
  }
  
  static byte[] xor2(byte[] in, byte[] key) {
    byte[] out = new byte[in.length];
    for (int i = 0; i < in.length; i++)
      out[i] = (byte)(in[i] ^ key[i % key.length] & 0xA); 
    return out;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\XORUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */