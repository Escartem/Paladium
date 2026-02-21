package fr.paladium.palashop.provider.cosmetic.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.cosmetic.client.event.CosmeticRenderEvent;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.behavior.CosmeticBehavior;
import fr.paladium.palashop.provider.cosmetic.common.dto.behavior.CosmeticBehaviorManager;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class CosmeticBehaviorListener {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onCosmeticRender(CosmeticRenderEvent event) {
    for (CosmeticBehavior<? extends ICosmetic> behavior : (Iterable<CosmeticBehavior<? extends ICosmetic>>)CosmeticBehaviorManager.getBehaviors(event.getCosmetic()))
      behavior.unsafeRender(event.getEntity(), event.getCosmetic()); 
  }
  
  @SubscribeEvent
  public void onCosmeticTick(TickEvent.PlayerTickEvent event) {
    EntityPlayer entity = event.player;
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    if (cosmeticPlayer == null)
      return; 
    for (CosmeticPlayer.EquippedCosmetic equippedCosmetic : cosmeticPlayer.getOutfit().getEquippedCosmeticMap().values()) {
      for (ICosmetic cosmetic : equippedCosmetic.getCosmetics()) {
        if (cosmetic != null)
          for (CosmeticBehavior<? extends ICosmetic> behavior : (Iterable<CosmeticBehavior<? extends ICosmetic>>)CosmeticBehaviorManager.getBehaviors(cosmetic))
            behavior.unsafeTick((Entity)entity, cosmetic);  
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\listener\CosmeticBehaviorListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */