package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetCosmeticsCount extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    String uuid = UUIDUtils.toString((Entity)player);
    Map<ShopRarity, Integer> data = new HashMap<>();
    CosmeticProvider.getInstance()
      .getCosmetics(uuid)
      .thenAccept(cosmetics -> {
          for (ShopCosmeticData cosmeticData : cosmetics) {
            Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(cosmeticData.getFactory());
            if (!optionalFactory.isPresent())
              continue; 
            CosmeticFactory factory = optionalFactory.get();
            Optional<ICosmetic> optionalCosmetic = factory.getCosmetic(cosmeticData.getCosmetic());
            if (!optionalCosmetic.isPresent())
              continue; 
            ICosmetic cosmetic = optionalCosmetic.get();
            ShopRarity rarity = cosmetic.getRarity();
            data.put(rarity, Integer.valueOf(((Integer)data.getOrDefault(rarity, Integer.valueOf(0))).intValue() + 1));
          } 
          reply(new BBPacketGetCosmeticsCountData(data));
        }).exceptionally(throwable -> {
          reply(new BBPacketGetCosmeticsCountData(null));
          return null;
        });
  }
  
  public class BBPacketGetCosmeticsCountData {
    private final Map<ShopRarity, Integer> data;
    
    public BBPacketGetCosmeticsCountData(Map<ShopRarity, Integer> data) {
      this.data = data;
    }
    
    public Map<ShopRarity, Integer> getData() {
      return this.data;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\BBPacketGetCosmeticsCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */