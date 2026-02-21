package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SlimePad extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 7, 1, 7, BlocksRegister.SLIMEPAD_BLOCK, player);
    PlayerUtils.sendMessage((EntityPlayer)player, "Il ne me semblait pas avoir vu de panneau sol glissant pourtant ?");
  }
  
  public String getName() {
    return "J'ai glissé chef";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "slimepad";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SlimePad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */