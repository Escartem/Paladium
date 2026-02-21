package fr.paladium.palavoicechat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;

public class SCPacketReconnectAfterDown extends ForgePacket {
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (PalaVoiceChatMod.getClientProxy().getClientConfig().isVoiceChatEnabled() && !IoNettyVoIPClient.getInstance().isConnected()) {
      (new Notification(Notification.NotificationType.INFO, "Tentative de reconnexion au voice chat en cours...", "VOICE")).send();
      IoNettyVoIPClient.getInstance().connect();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\SCPacketReconnectAfterDown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */