package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class DirtyInventory extends ALuckyEvent {
  public static final String a = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = -1173398708;
        buf[0] = (byte)(this.t >>> 13);
        this.t = 321247809;
        buf[1] = (byte)(this.t >>> 16);
        this.t = 837144825;
        buf[2] = (byte)(this.t >>> 6);
        this.t = -1235993258;
        buf[3] = (byte)(this.t >>> 17);
        this.t = -634235142;
        buf[4] = (byte)(this.t >>> 19);
        this.t = -1023317739;
        buf[5] = (byte)(this.t >>> 11);
        this.t = 1248829364;
        buf[6] = (byte)(this.t >>> 24);
        this.t = 3105119;
        buf[7] = (byte)(this.t >>> 8);
        this.t = -1361304973;
        buf[8] = (byte)(this.t >>> 3);
        this.t = 1931829779;
        buf[9] = (byte)(this.t >>> 19);
        this.t = -172160363;
        buf[10] = (byte)(this.t >>> 3);
        this.t = 1503441991;
        buf[11] = (byte)(this.t >>> 18);
        this.t = -1604707668;
        buf[12] = (byte)(this.t >>> 3);
        this.t = -1582821420;
        buf[13] = (byte)(this.t >>> 18);
        this.t = -459928376;
        buf[14] = (byte)(this.t >>> 14);
        this.t = -304367080;
        buf[15] = (byte)(this.t >>> 21);
        return new String(buf);
      }
    }).toString();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 20; i++)
      player.field_71071_by.func_70441_a(new ItemStack(Blocks.field_150346_d, 64)); 
  }
  
  public String getName() {
    return "Inventaire plein";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 5000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "inventaire_plein";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DirtyInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */