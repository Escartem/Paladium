package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketSetCosmeticPlayerOutfit extends ForgePacket {
  @PacketData
  private int value;
  
  @PacketData
  private Action action;
  
  public CSPacketSetCosmeticPlayerOutfit() {}
  
  public CSPacketSetCosmeticPlayerOutfit(int value, Action action) {
    this.value = value;
    this.action = action;
  }
  
  public void processServer(EntityPlayerMP player) {
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send(player);
      reply(new Object());
      return;
    } 
    if (this.action == Action.INCREASE) {
      cosmeticPlayer.incrementOutfitId().sync();
    } else if (this.action == Action.DECREASE) {
      cosmeticPlayer.decrementOutfitId().sync();
    } else if (this.action == Action.SET) {
      cosmeticPlayer.setOutfitId(this.value).sync();
    } 
    reply(new Object());
  }
  
  public enum Action {
    INCREASE, DECREASE, SET;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\CSPacketSetCosmeticPlayerOutfit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */