package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class PutFire extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int ox = -10; ox < 11; ox++) {
      for (int oz = -10; oz < 11; oz++) {
        if (EventUtils.canInteract((EntityPlayer)player, x + ox, player.field_70170_p.func_72976_f(x + ox, z + oz), z + oz))
          player.field_70170_p.func_147449_b(x + ox, player.field_70170_p.func_72976_f(x + ox, z + oz), z + oz, (Block)Blocks.field_150480_ab); 
      } 
    } 
  }
  
  public String getName() {
    return "Allumer le feu";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 40;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "put_fire";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PutFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */