package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class Dolby extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    try {
      if ((Minecraft.func_71410_x().func_110438_M()).field_110620_b
        .func_110589_b(new ResourceLocation("PalaSound")))
        (Minecraft.func_71410_x().func_110438_M()).field_110620_b
          .func_110590_a(new ResourceLocation("PalaSound")); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public String getName() {
    return "Dolby 5.1";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "dolby";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Dolby.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */