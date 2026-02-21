package fr.paladium.palashop.provider.cosmetic.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class CosmeticRenderPlayerListener {
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onRenderNametag(RenderLivingEvent.Specials.Pre event) {
    if (!(event.entity instanceof fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP))
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderNametag(RenderPlayerEvent.Specials.Pre event) {
    if (!(event.entity instanceof fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP))
      return; 
    event.renderCape = false;
    event.renderItem = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\listener\CosmeticRenderPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */