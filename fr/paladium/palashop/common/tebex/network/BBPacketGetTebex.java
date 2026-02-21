package fr.paladium.palashop.common.tebex.network;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palashop.PalaShopMod;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetTebex extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    reply(PalaShopMod.getServer().getConfig().getTebex());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\tebex\network\BBPacketGetTebex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */