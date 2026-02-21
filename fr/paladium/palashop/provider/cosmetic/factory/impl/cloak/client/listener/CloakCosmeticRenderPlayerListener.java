package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palashop.provider.cosmetic.client.event.CosmeticRenderEvent;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto.CloakCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.render.world.CloakCosmeticRenderer;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class CloakCosmeticRenderPlayerListener {
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onRenderPlayerSpecialPre(RenderPlayerEvent.Specials.Pre event) {
    EntityPlayer entity = event.entityPlayer;
    if (!event.renderCape || entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optional = cosmeticPlayer.getOutfit().get(CloakCosmeticFactory.ID);
    if (!optional.isPresent())
      return; 
    for (ICosmetic equippedCosmetic : ((CosmeticPlayer.EquippedCosmetic)optional.get()).getCosmetics()) {
      if (equippedCosmetic instanceof CloakCosmeticClient) {
        CloakCosmeticClient cosmetic = (CloakCosmeticClient)equippedCosmetic;
        CosmeticRenderEvent.execute((Entity)entity, (ICosmetic)cosmetic, cosmeticRenderEvent -> {
              CloakCosmeticRenderer.render(entity, cosmetic, event.partialRenderTick);
              event.renderCape = false;
            });
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\listener\CloakCosmeticRenderPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */