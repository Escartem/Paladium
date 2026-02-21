package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TimeMachine extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int cx = x - 2; cx < x + 2; cx++) {
      for (int cy = y - 2; cy < y; cy++) {
        for (int cz = z - 2; cz < z + 2; cz++) {
          if (EventUtils.canInteract((EntityPlayer)player, cx, cy, cz))
            if (!player.field_70170_p.func_147439_a(cx, cy, cz).func_149688_o().equals(Material.field_151579_a))
              player.field_70170_p.func_147449_b(cx, cy, cz, BlocksRegister.FLASHY_BLOCK);  
        } 
      } 
    } 
  }
  
  public String getName() {
    return "Time Machine";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "flashy_block";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TimeMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */