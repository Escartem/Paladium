package fr.paladium.palamod.util;

import fr.paladium.palamod.modules.paladium.utils.IEntity;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.entity.Entity;

public class FastUUID {
  private static final boolean USE_JDK_UUID_TO_STRING;
  
  private static final int UUID_STRING_LENGTH = 36;
  
  static {
    int majorVersion = 0;
    try {
      majorVersion = Integer.parseInt(System.getProperty("java.specification.version"));
    } catch (NumberFormatException numberFormatException) {}
    USE_JDK_UUID_TO_STRING = (majorVersion >= 9);
  }
  
  private static final char[] HEX_DIGITS = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  private static final long[] HEX_VALUES = new long[128];
  
  static {
    Arrays.fill(HEX_VALUES, -1L);
    HEX_VALUES[48] = 0L;
    HEX_VALUES[49] = 1L;
    HEX_VALUES[50] = 2L;
    HEX_VALUES[51] = 3L;
    HEX_VALUES[52] = 4L;
    HEX_VALUES[53] = 5L;
    HEX_VALUES[54] = 6L;
    HEX_VALUES[55] = 7L;
    HEX_VALUES[56] = 8L;
    HEX_VALUES[57] = 9L;
    HEX_VALUES[97] = 10L;
    HEX_VALUES[98] = 11L;
    HEX_VALUES[99] = 12L;
    HEX_VALUES[100] = 13L;
    HEX_VALUES[101] = 14L;
    HEX_VALUES[102] = 15L;
    HEX_VALUES[65] = 10L;
    HEX_VALUES[66] = 11L;
    HEX_VALUES[67] = 12L;
    HEX_VALUES[68] = 13L;
    HEX_VALUES[69] = 14L;
    HEX_VALUES[70] = 15L;
  }
  
  public static UUID parseUUID(CharSequence uuidSequence) {
    if (uuidSequence.length() != 36 || uuidSequence
      .charAt(8) != '-' || uuidSequence
      .charAt(13) != '-' || uuidSequence
      .charAt(18) != '-' || uuidSequence
      .charAt(23) != '-')
      throw new IllegalArgumentException("Illegal UUID string: " + uuidSequence); 
    long mostSignificantBits = getHexValueForChar(uuidSequence.charAt(0)) << 60L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(1)) << 56L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(2)) << 52L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(3)) << 48L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(4)) << 44L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(5)) << 40L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(6)) << 36L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(7)) << 32L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(9)) << 28L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(10)) << 24L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(11)) << 20L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(12)) << 16L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(14)) << 12L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(15)) << 8L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(16)) << 4L;
    mostSignificantBits |= getHexValueForChar(uuidSequence.charAt(17));
    long leastSignificantBits = getHexValueForChar(uuidSequence.charAt(19)) << 60L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(20)) << 56L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(21)) << 52L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(22)) << 48L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(24)) << 44L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(25)) << 40L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(26)) << 36L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(27)) << 32L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(28)) << 28L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(29)) << 24L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(30)) << 20L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(31)) << 16L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(32)) << 12L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(33)) << 8L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(34)) << 4L;
    leastSignificantBits |= getHexValueForChar(uuidSequence.charAt(35));
    return new UUID(mostSignificantBits, leastSignificantBits);
  }
  
  public static String toString(Entity entity) {
    if (entity instanceof IEntity)
      return ((IEntity)entity).getStringUUID(); 
    return toString(entity.func_110124_au());
  }
  
  public static String toString(UUID uuid) {
    if (USE_JDK_UUID_TO_STRING)
      return uuid.toString(); 
    long mostSignificantBits = uuid.getMostSignificantBits();
    long leastSignificantBits = uuid.getLeastSignificantBits();
    char[] uuidChars = new char[36];
    uuidChars[0] = HEX_DIGITS[(int)((mostSignificantBits & 0xF000000000000000L) >>> 60L)];
    uuidChars[1] = HEX_DIGITS[(int)((mostSignificantBits & 0xF00000000000000L) >>> 56L)];
    uuidChars[2] = HEX_DIGITS[(int)((mostSignificantBits & 0xF0000000000000L) >>> 52L)];
    uuidChars[3] = HEX_DIGITS[(int)((mostSignificantBits & 0xF000000000000L) >>> 48L)];
    uuidChars[4] = HEX_DIGITS[(int)((mostSignificantBits & 0xF00000000000L) >>> 44L)];
    uuidChars[5] = HEX_DIGITS[(int)((mostSignificantBits & 0xF0000000000L) >>> 40L)];
    uuidChars[6] = HEX_DIGITS[(int)((mostSignificantBits & 0xF000000000L) >>> 36L)];
    uuidChars[7] = HEX_DIGITS[(int)((mostSignificantBits & 0xF00000000L) >>> 32L)];
    uuidChars[8] = '-';
    uuidChars[9] = HEX_DIGITS[(int)((mostSignificantBits & 0xF0000000L) >>> 28L)];
    uuidChars[10] = HEX_DIGITS[(int)((mostSignificantBits & 0xF000000L) >>> 24L)];
    uuidChars[11] = HEX_DIGITS[(int)((mostSignificantBits & 0xF00000L) >>> 20L)];
    uuidChars[12] = HEX_DIGITS[(int)((mostSignificantBits & 0xF0000L) >>> 16L)];
    uuidChars[13] = '-';
    uuidChars[14] = HEX_DIGITS[(int)((mostSignificantBits & 0xF000L) >>> 12L)];
    uuidChars[15] = HEX_DIGITS[(int)((mostSignificantBits & 0xF00L) >>> 8L)];
    uuidChars[16] = HEX_DIGITS[(int)((mostSignificantBits & 0xF0L) >>> 4L)];
    uuidChars[17] = HEX_DIGITS[(int)(mostSignificantBits & 0xFL)];
    uuidChars[18] = '-';
    uuidChars[19] = HEX_DIGITS[(int)((leastSignificantBits & 0xF000000000000000L) >>> 60L)];
    uuidChars[20] = HEX_DIGITS[(int)((leastSignificantBits & 0xF00000000000000L) >>> 56L)];
    uuidChars[21] = HEX_DIGITS[(int)((leastSignificantBits & 0xF0000000000000L) >>> 52L)];
    uuidChars[22] = HEX_DIGITS[(int)((leastSignificantBits & 0xF000000000000L) >>> 48L)];
    uuidChars[23] = '-';
    uuidChars[24] = HEX_DIGITS[(int)((leastSignificantBits & 0xF00000000000L) >>> 44L)];
    uuidChars[25] = HEX_DIGITS[(int)((leastSignificantBits & 0xF0000000000L) >>> 40L)];
    uuidChars[26] = HEX_DIGITS[(int)((leastSignificantBits & 0xF000000000L) >>> 36L)];
    uuidChars[27] = HEX_DIGITS[(int)((leastSignificantBits & 0xF00000000L) >>> 32L)];
    uuidChars[28] = HEX_DIGITS[(int)((leastSignificantBits & 0xF0000000L) >>> 28L)];
    uuidChars[29] = HEX_DIGITS[(int)((leastSignificantBits & 0xF000000L) >>> 24L)];
    uuidChars[30] = HEX_DIGITS[(int)((leastSignificantBits & 0xF00000L) >>> 20L)];
    uuidChars[31] = HEX_DIGITS[(int)((leastSignificantBits & 0xF0000L) >>> 16L)];
    uuidChars[32] = HEX_DIGITS[(int)((leastSignificantBits & 0xF000L) >>> 12L)];
    uuidChars[33] = HEX_DIGITS[(int)((leastSignificantBits & 0xF00L) >>> 8L)];
    uuidChars[34] = HEX_DIGITS[(int)((leastSignificantBits & 0xF0L) >>> 4L)];
    uuidChars[35] = HEX_DIGITS[(int)(leastSignificantBits & 0xFL)];
    return new String(uuidChars);
  }
  
  static long getHexValueForChar(char c) {
    try {
      if (HEX_VALUES[c] < 0L)
        throw new IllegalArgumentException("Illegal hexadecimal digit: " + c); 
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Illegal hexadecimal digit: " + c);
    } 
    return HEX_VALUES[c];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\FastUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */