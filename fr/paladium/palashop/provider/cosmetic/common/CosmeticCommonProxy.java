package fr.paladium.palashop.provider.cosmetic.common;

import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.listener.CosmeticBehaviorListener;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmeticData;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmetics;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmeticsCount;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketClearCosmeticPlayerOutfit;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketExecuteWheelCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketSetCosmeticPlayerOutfit;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
import net.minecraft.entity.player.EntityPlayer;

public class CosmeticCommonProxy extends ShopProviderProxy {
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initListener();
    initExtended();
    initNetwork();
  }
  
  private void initListener() {
    addListener(new Class[] { CosmeticBehaviorListener.class });
  }
  
  private void initExtended() {
    ExtendedUtils.registerExtended(EntityPlayer.class, CosmeticPlayer.class, "palashop_COSMETIC", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
  }
  
  private void initNetwork() {
    initNetwork(CosmeticProvider.getInstance().getId());
    addPacket(new Class[] { BBPacketGetCosmeticData.class });
    addPacket(new Class[] { BBPacketGetCosmetics.class });
    addPacket(new Class[] { BBPacketGetCosmeticsCount.class });
    addPacket(new Class[] { CSPacketEquipCosmetic.class });
    addPacket(new Class[] { CSPacketUnEquipCosmetic.class });
    addPacket(new Class[] { CSPacketSetCosmeticPlayerOutfit.class });
    addPacket(new Class[] { CSPacketClearCosmeticPlayerOutfit.class });
    addPacket(new Class[] { CSPacketExecuteWheelCosmetic.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\CosmeticCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */