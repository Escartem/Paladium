package fr.paladium.palavoicechat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSDisconnectVCPacket extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    IoNettyVoIPServer.getInstance().disconnectPlayer(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\CSDisconnectVCPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */