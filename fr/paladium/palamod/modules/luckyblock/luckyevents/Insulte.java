package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketSendChatMessage;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;

public class Insulte extends ALuckyEvent {
  private String[] insultes = new String[] { "Je crois que je suis extrêmement nul..", "Tapez 1 si vous pensez que je suis nul !", "Mon niveau en PvP est pire qu’un manchot unijambiste sourd muet et aveugle" };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random r = player.field_70170_p.field_73012_v;
    String insulte = this.insultes[r.nextInt(this.insultes.length)];
    PalaMod.getNetwork().sendTo((IMessage)new PacketSendChatMessage(insulte), player);
  }
  
  public String getName() {
    return "Insulte";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Insulte.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */