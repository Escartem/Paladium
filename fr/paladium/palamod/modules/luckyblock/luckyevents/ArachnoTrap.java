package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class ArachnoTrap extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70634_a(x, y, z);
    for (int ox = -1; ox < 2; ox++) {
      for (int oy = -1; oy < 2; oy++) {
        for (int oz = -1; oz < 2; oz++) {
          if (EventUtils.canInteract((EntityPlayer)player, x + ox, y + oy, z + oz)) {
            player.field_70170_p.func_147449_b(x + ox, y + oy, z + oz, Blocks.field_150321_G);
            if (player.field_70170_p.field_73012_v.nextInt(50) > 35) {
              EntityCaveSpider spider = new EntityCaveSpider(player.field_70170_p);
              spider.func_70634_a((x + ox), (y + oy + 3), (z + oz));
              spider.func_70784_b((Entity)player);
              player.field_70170_p.func_72838_d((Entity)spider);
            } 
          } 
        } 
      } 
    } 
  }
  
  public String getName() {
    return "ArachnoTrap";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ArachnoTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */