package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.scheduler.PaladiumRunnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

class null extends PaladiumRunnable {
  int x = minX;
  
  public void run() {
    int tmpX = this.x + 1;
    int blocksReplaced = 0;
    for (; this.x <= maxX && this.x <= tmpX; this.x++) {
      for (int y = 0; y <= maxY; y++) {
        for (int z = minZ; z <= maxZ; z++) {
          if (EventUtils.canInteract(p_77659_3_, this.x, y, z)) {
            p_77659_2_.func_147468_f(this.x, y, z);
            blocksReplaced++;
          } 
        } 
      } 
    } 
    if (blocksReplaced == 0) {
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§e[Foreuse] §aAction terminée."));
      cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemForeuse$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */