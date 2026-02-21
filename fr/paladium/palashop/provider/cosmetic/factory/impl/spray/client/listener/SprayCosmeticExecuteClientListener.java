package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palashop.provider.cosmetic.common.event.CosmeticExecuteWheelEvent;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import net.minecraft.util.MovingObjectPosition;

public class SprayCosmeticExecuteClientListener {
  @SubscribeEvent
  public void onSprayExecute(CosmeticExecuteWheelEvent.Pre event) {
    if (event.getCosmetic().getFactory() != SprayCosmeticFactory.getInstance() || event.getTarget().getType() == MovingObjectPosition.MovingObjectType.BLOCK)
      return; 
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\listener\SprayCosmeticExecuteClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */