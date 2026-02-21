package fr.paladium.palaclicker.common.network.packet.shop;

import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketClickerShopData extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    PlayerClickerData data = PlayerClickerData.get((Entity)player);
    if (data == null)
      return; 
    for (ClickerShopType type : ClickerShopType.values())
      data.getShopItems(type); 
    reply(new BBPacketClickerShopDataObject(data.getShopItems()));
  }
  
  public class BBPacketClickerShopDataObject {
    private final Map<ClickerShopType, List<ClickerShopItem>> shopItems;
    
    public BBPacketClickerShopDataObject(Map<ClickerShopType, List<ClickerShopItem>> shopItems) {
      this.shopItems = shopItems;
    }
    
    public Map<ClickerShopType, List<ClickerShopItem>> getShopItems() {
      return this.shopItems;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\shop\BBPacketClickerShopData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */