package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ChatDetector extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack((Block)BlocksRegister.CHAT_DETECTOR));
  }
  
  public String getName() {
    return "Chat Detector";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 800;
  }
  
  public String getTexture() {
    return "chat_detector";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ChatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */