package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetCosmetics extends ForgePacket {
  @PacketData
  private String factory;
  
  public BBPacketGetCosmetics() {}
  
  public BBPacketGetCosmetics(String factory) {
    this.factory = factory;
  }
  
  public void processServer(EntityPlayerMP player) {
    String uuid = UUIDUtils.toString((Entity)player);
    ((this.factory == null || this.factory.isEmpty()) ? CosmeticProvider.getInstance().getCosmetics(uuid) : CosmeticProvider.getInstance().getCosmetics(uuid, this.factory))
      .thenAccept(cosmetics -> {
          List<ShopCosmeticData> data = new ArrayList<>();
          Map<String, Boolean> factories = new HashMap<>();
          for (ShopCosmeticData cosmetic : cosmetics) {
            String factory = cosmetic.getFactory();
            if (factories.containsKey(factory)) {
              if (((Boolean)factories.get(factory)).booleanValue())
                data.add(cosmetic); 
              continue;
            } 
            boolean exists = CosmeticProvider.getInstance().getFactory(factory).isPresent();
            if (exists)
              data.add(cosmetic); 
            factories.put(factory, Boolean.valueOf(exists));
          } 
          reply(new BBPacketGetCosmeticsData(data));
        }).exceptionally(throwable -> {
          reply(new BBPacketGetCosmeticsData(null));
          return null;
        });
  }
  
  public class BBPacketGetCosmeticsData {
    private final List<ShopCosmeticData> data;
    
    public BBPacketGetCosmeticsData(List<ShopCosmeticData> data) {
      this.data = data;
    }
    
    public List<ShopCosmeticData> getData() {
      return this.data;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\BBPacketGetCosmetics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */