package fr.paladium.palashop.provider.cosmetic.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class CosmeticPlayerListener {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null)
      return; 
    String uuid = UUIDUtils.toString((Entity)player);
    for (Iterator<Map.Entry<String, CosmeticPlayer.EquippedCosmetic>> iterator = cosmeticPlayer.getOutfit().getEquippedCosmeticMap().entrySet().iterator(); iterator.hasNext(); ) {
      Map.Entry<String, CosmeticPlayer.EquippedCosmetic> entry = iterator.next();
      CosmeticProvider.getInstance()
        .getCosmetics(uuid, entry.getKey())
        .thenAccept(cosmetics -> {
            boolean hasChanged = false;
            for (int i = 0; i < (((CosmeticPlayer.EquippedCosmetic)entry.getValue()).getCosmetics()).length; i++) {
              Optional<ICosmetic> optional = ((CosmeticPlayer.EquippedCosmetic)entry.getValue()).get(i);
              if (optional.isPresent()) {
                ICosmetic cosmetic = optional.get();
                if (!cosmetics.stream().anyMatch(())) {
                  ((CosmeticPlayer.EquippedCosmetic)entry.getValue()).remove(i);
                  hasChanged = true;
                } 
              } 
            } 
            if (hasChanged)
              cosmeticPlayer.sync(); 
          });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\server\listener\CosmeticPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */