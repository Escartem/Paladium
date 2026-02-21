package fr.paladium.palashop.common.shop.network.page;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.condition.ShopConditions;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetPages extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    List<ShopPage> pageList = new ArrayList<>(ShopManager.getPages());
    ShopManager.User.getUser(UUIDUtils.toString((Entity)player))
      .thenAccept(user -> {
          for (ShopPage page : ShopManager.getPages()) {
            for (String condition : page.getConditions()) {
              if (ShopConditions.validate(user, (EntityPlayer)player, condition, false) != null) {
                pageList.remove(page);
                break;
              } 
            } 
          } 
          reply(new BBPacketGetPagesData(pageList));
        }).exceptionally(t -> {
          reply(new BBPacketGetPagesData(new ArrayList<>()));
          t.printStackTrace();
          return null;
        });
  }
  
  public class BBPacketGetPagesData {
    private final List<ShopPage> pageList;
    
    public BBPacketGetPagesData(List<ShopPage> pageList) {
      this.pageList = pageList;
    }
    
    public List<ShopPage> getPageList() {
      return this.pageList;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\page\BBPacketGetPages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */