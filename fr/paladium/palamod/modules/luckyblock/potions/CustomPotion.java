package fr.paladium.palamod.modules.luckyblock.potions;

import net.minecraft.potion.Potion;

public class CustomPotion extends Potion {
  public static final String _na = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[18];
        this.t = -718070015;
        buf[0] = (byte)(this.t >>> 11);
        this.t = 467595969;
        buf[1] = (byte)(this.t >>> 22);
        this.t = -916623869;
        buf[2] = (byte)(this.t >>> 8);
        this.t = 1081242816;
        buf[3] = (byte)(this.t >>> 8);
        this.t = 672747643;
        buf[4] = (byte)(this.t >>> 14);
        this.t = -1993912555;
        buf[5] = (byte)(this.t >>> 5);
        this.t = -844204862;
        buf[6] = (byte)(this.t >>> 8);
        this.t = -634043559;
        buf[7] = (byte)(this.t >>> 8);
        this.t = 455794316;
        buf[8] = (byte)(this.t >>> 22);
        this.t = 381934744;
        buf[9] = (byte)(this.t >>> 17);
        this.t = 758746662;
        buf[10] = (byte)(this.t >>> 15);
        this.t = -36835853;
        buf[11] = (byte)(this.t >>> 18);
        this.t = 1154163710;
        buf[12] = (byte)(this.t >>> 20);
        this.t = -1377109529;
        buf[13] = (byte)(this.t >>> 21);
        this.t = -1367158396;
        buf[14] = (byte)(this.t >>> 2);
        this.t = -274733490;
        buf[15] = (byte)(this.t >>> 4);
        this.t = -730441826;
        buf[16] = (byte)(this.t >>> 12);
        this.t = -1386809562;
        buf[17] = (byte)(this.t >>> 4);
        return new String(buf);
      }
    }).toString();
  
  public static CustomPotion effect;
  
  public static int effectID;
  
  public static String name;
  
  public static int color;
  
  public static boolean isBad;
  
  public CustomPotion(int id, boolean isPotionBad, int potionColor, String potionName) {
    super(id, isPotionBad, potionColor);
    func_76390_b("potion." + potionName);
    name = potionName;
    color = potionColor;
    isBad = isPotionBad;
  }
  
  public CustomPotion setIconIndex(int x, int y) {
    super.func_76399_b(x, y);
    return this;
  }
  
  public void loadEffects() {
    effect = new CustomPotion(effectID, isBad, color, name);
  }
  
  public void register() {
    field_76425_a[effect.func_76396_c()] = effect;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\potions\CustomPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */