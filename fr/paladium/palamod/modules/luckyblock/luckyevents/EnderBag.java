package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class EnderBag extends ALuckyEvent {
  public static final String a = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = 699336969;
        buf[0] = (byte)(this.t >>> 13);
        this.t = -1862624917;
        buf[1] = (byte)(this.t >>> 23);
        this.t = -912768599;
        buf[2] = (byte)(this.t >>> 8);
        this.t = -215663877;
        buf[3] = (byte)(this.t >>> 16);
        this.t = -1282338989;
        buf[4] = (byte)(this.t >>> 14);
        this.t = 1428246727;
        buf[5] = (byte)(this.t >>> 11);
        this.t = 1795753082;
        buf[6] = (byte)(this.t >>> 4);
        this.t = -757616541;
        buf[7] = (byte)(this.t >>> 20);
        this.t = -457472060;
        buf[8] = (byte)(this.t >>> 20);
        this.t = 872984805;
        buf[9] = (byte)(this.t >>> 7);
        this.t = 1972774933;
        buf[10] = (byte)(this.t >>> 7);
        this.t = -920122587;
        buf[11] = (byte)(this.t >>> 3);
        this.t = 22338317;
        buf[12] = (byte)(this.t >>> 14);
        this.t = 436009539;
        buf[13] = (byte)(this.t >>> 22);
        this.t = -1252754851;
        buf[14] = (byte)(this.t >>> 20);
        this.t = -512924435;
        buf[15] = (byte)(this.t >>> 13);
        return new String(buf);
      }
    }).toString();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.ENDER_BAG, 1);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Sac de fin";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "sac_de_fin";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\EnderBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */