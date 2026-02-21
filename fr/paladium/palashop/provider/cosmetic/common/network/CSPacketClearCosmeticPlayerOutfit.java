package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketClearCosmeticPlayerOutfit extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send(player);
      reply(new Object());
      return;
    } 
    cosmeticPlayer.getOutfit().clear();
    cosmeticPlayer.sync();
    reply(new Object());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\CSPacketClearCosmeticPlayerOutfit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */