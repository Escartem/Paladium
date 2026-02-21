package fr.paladium.palavoicechat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;

public class SCVoiceServerUnavailablePacket extends ForgePacket {
  @SideOnly(Side.CLIENT)
  public void processClient() {
    System.out.println("[PalaVoiceChat] VoIP server is unavailable.");
    IoNettyVoIPClient.getInstance().disconnect();
    (new Notification(Notification.NotificationType.ERROR, "Le chat de proximité est indisponible, réessayer ultérieurement", "VOICE")).send();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\SCVoiceServerUnavailablePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */