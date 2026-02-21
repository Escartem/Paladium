package fr.paladium.palashop.common.shop.network.page;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.page.ShopPageData;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetPageData extends ForgePacket {
  @PacketData
  private String id;
  
  @PacketData
  private String dataClass;
  
  public BBPacketGetPageData() {}
  
  public BBPacketGetPageData(String id, String dataClass) {
    this.id = id;
    this.dataClass = dataClass;
  }
  
  public void processServer(EntityPlayerMP player) {
    Optional<ShopPage> optional = ShopManager.getPage(this.id);
    if (!optional.isPresent())
      return; 
    ShopPage page = optional.get();
    if (page.getData() == null)
      return; 
    try {
      Class<?> clazz = Class.forName(this.dataClass, false, Thread.currentThread().getContextClassLoader());
      if (!ShopPageData.class.isAssignableFrom(clazz))
        return; 
      Class<? extends ShopPageData> shopPageDataClass = (Class)clazz;
      ShopManager.User.getUser(UUIDUtils.toString((Entity)player))
        .thenAccept(user -> {
            ShopPageData data = page.getData(shopPageDataClass);
            data.create(user, (EntityPlayer)player);
            reply(new BBPacketGetPageDataData(data));
          }).exceptionally(t -> {
            t.printStackTrace();
            return null;
          });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public class BBPacketGetPageDataData {
    private final ShopPageData data;
    
    public BBPacketGetPageDataData(ShopPageData data) {
      this.data = data;
    }
    
    public ShopPageData getData() {
      return this.data;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\page\BBPacketGetPageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */