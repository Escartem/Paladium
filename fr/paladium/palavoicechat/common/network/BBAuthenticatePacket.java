package fr.paladium.palavoicechat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBAuthenticatePacket extends ForgePacket {
  @PacketData
  private String serverIP;
  
  @PacketData
  private int serverPort;
  
  @PacketData
  private UUID secret;
  
  public BBAuthenticatePacket(String serverIP, int serverPort, UUID secret) {
    this.serverIP = serverIP;
    this.serverPort = serverPort;
    this.secret = secret;
  }
  
  public BBAuthenticatePacket() {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    System.out.println("[PalaVoiceChat] Authenticating to VoIP server " + this.serverIP + ":" + this.serverPort);
    IoNettyVoIPClient.getInstance().authenticate(this.serverIP, this.serverPort, this.secret);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    IoNettyVoIPServer.getInstance().connectPlayer(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\BBAuthenticatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */