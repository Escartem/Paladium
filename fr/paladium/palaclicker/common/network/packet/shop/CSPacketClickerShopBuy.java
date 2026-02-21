package fr.paladium.palaclicker.common.network.packet.shop;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketClickerShopBuy extends ForgePacket {
  @PacketData(PacketSide.CLIENT)
  private ClickerShopType type;
  
  @PacketData(PacketSide.CLIENT)
  private String itemId;
  
  public CSPacketClickerShopBuy(ClickerShopType type, String itemId) {
    this.type = type;
    this.itemId = itemId;
  }
  
  public CSPacketClickerShopBuy() {}
  
  public void processServer(EntityPlayerMP player) {
    PlayerClickerData data = PlayerClickerData.get((Entity)player);
    if (data == null)
      return; 
    Optional<ClickerShopItem> shopItem = PalaClickerMod.getServer().getShopConfig().getItemList().stream().filter(item -> item.getId().equals(this.itemId)).findAny();
    if (!shopItem.isPresent()) {
      for (ClickerShopItem item : data.getShopItems(this.type)) {
        if (!item.getId().equals(this.itemId))
          continue; 
        data.generateShopItems(this.type);
        reply(Boolean.valueOf(false));
        return;
      } 
      return;
    } 
    int quantity = data.getShopQuantity(this.type, this.itemId);
    double price = ((ClickerShopItem)shopItem.get()).getPrice(quantity);
    if (data.getPoints() < price || quantity >= 99) {
      reply(Boolean.valueOf(false));
      return;
    } 
    if (quantity >= ((ClickerShopItem)shopItem.get()).getQuantity()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    data.addPoints(-price);
    data.increaseShopQuantity(this.type, this.itemId);
    InventoryUtils.giveOrDropitems((EntityPlayer)player, ((ClickerShopItem)shopItem.get()).getItemStack().func_77946_l());
    for (ClickerShopType type : ClickerShopType.values())
      data.getShopItems(type); 
    reply(Boolean.valueOf(true));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\shop\CSPacketClickerShopBuy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */