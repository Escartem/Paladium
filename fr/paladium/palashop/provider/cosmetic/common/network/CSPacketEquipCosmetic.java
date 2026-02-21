package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketEquipCosmetic extends ForgePacket {
  @PacketData
  private String factory;
  
  @PacketData
  private String cosmetic;
  
  @PacketData
  private int index;
  
  public CSPacketEquipCosmetic() {}
  
  public CSPacketEquipCosmetic(String factory, String cosmetic, int index) {
    this.factory = factory;
    this.cosmetic = cosmetic;
    this.index = index;
  }
  
  public void processServer(EntityPlayerMP player) {
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send(player);
      return;
    } 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(this.factory);
    if (!optionalFactory.isPresent()) {
      (new Notification(Notification.NotificationType.ERROR, "Ce cosmétique n'est plus disponible", "palashop")).send(player);
      return;
    } 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(this.cosmetic);
    if (!optionalCosmetic.isPresent()) {
      (new Notification(Notification.NotificationType.ERROR, "Ce cosmétique n'est plus disponible", "palashop")).send(player);
      return;
    } 
    CosmeticProvider.getInstance()
      .hasCosmetic(UUIDUtils.toString((Entity)player), this.cosmetic)
      .thenAccept(has -> {
          if (!has.booleanValue()) {
            (new Notification(Notification.NotificationType.ERROR, "Vous ne possédez pas ce cosmétique", "palashop")).send(player);
            reply(new Object());
            return;
          } 
          Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(this.factory);
          if (!optionalEquippedCosmetic.isPresent()) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible d'équiper ce cosmétique", "palashop")).send(player);
            reply(new Object());
            return;
          } 
          CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
          if (equippedCosmetic.getType() == CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.UNIQUE || this.index == -1) {
            equippedCosmetic.equip(optionalCosmetic.get());
          } else if (equippedCosmetic.getType() == CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.WHEEL) {
            equippedCosmetic.set(this.index, optionalCosmetic.get());
          } 
          cosmeticPlayer.sync();
          reply(new Object());
        }).exceptionally(e -> {
          e.printStackTrace();
          (new Notification(Notification.NotificationType.ERROR, "Impossible d'équiper ce cosmétique", "palashop")).send(player);
          reply(new Object());
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\CSPacketEquipCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */