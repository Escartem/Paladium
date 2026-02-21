package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Victim extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (Object p : player.field_70170_p.field_73010_i) {
      if (p instanceof EntityPlayerMP)
        ((EntityPlayerMP)p).func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§o" + player
              .func_70005_c_() + "§4 s'est fait boloss.")); 
    } 
  }
  
  public String getName() {
    return "Victime";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Victim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */