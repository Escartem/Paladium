package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.network.BBPacketAnimateWearableCosmetic;
import java.util.Optional;
import net.minecraft.client.settings.GameSettings;

public class WearableCosmeticKeybindListener {
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    if (!GameSettings.func_100015_a(WearableCosmeticFactory.getClient().getActionKey()))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.me();
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    for (ICosmetic cosmetic : ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getCosmetics()) {
      if (cosmetic != null)
        (new BBPacketAnimateWearableCosmetic(cosmetic.getId(), "action")).send(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\listener\WearableCosmeticKeybindListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */