package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketSendChatMessage;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class Jul extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PalaMod.getNetwork()
      .sendTo((IMessage)new PacketSendChatMessage("En vrai le dernier album de JUL il est lourd"), player);
  }
  
  public String getName() {
    return "Fais le signe";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Jul.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */