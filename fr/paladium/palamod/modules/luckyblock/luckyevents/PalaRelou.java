package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PalaRelou extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (EventUtils.canInteract((EntityPlayer)player, x, y, z))
      player.field_70170_p.func_147449_b(x, y, z, BlocksRegister.BLOCK_FAKE_PALADIUM); 
  }
  
  public String getName() {
    return "Ouaiche t’es relou";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "palarelou";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PalaRelou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */