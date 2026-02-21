package fr.paladium.palamod.modules.argus.strings;

public class StringMods {
  public static String _a = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[4];
        this.t = -43139267;
        buf[0] = (byte)(this.t >>> 13);
        this.t = 212728750;
        buf[1] = (byte)(this.t >>> 13);
        this.t = -1369150545;
        buf[2] = (byte)(this.t >>> 16);
        this.t = 242513158;
        buf[3] = (byte)(this.t >>> 21);
        return new String(buf);
      }
    }).toString();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\strings\StringMods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */