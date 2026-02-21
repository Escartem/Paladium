package fr.paladium.pet.common.network.packet.pet;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.pet.common.handler.FeedPetGuiHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPOpenFeedContainerPacket extends ForgePacket {
  public void processClient() {}
  
  public void processServer(EntityPlayerMP player) {
    FeedPetGuiHandler.open(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\CSPOpenFeedContainerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */