package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.common.CommonModule;
import fr.paladium.palashop.provider.cosmetic.client.event.CosmeticRenderEvent;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.render.world.WearableCosmeticRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class WearableCosmeticRenderPlayerListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerSpecialPre(RenderPlayerEvent.Specials.Pre event) {
    if (CommonModule.getInstance().getCombatTag().inFight() && event.entityPlayer != (Minecraft.func_71410_x()).field_71439_g)
      return; 
    EntityPlayer entity = event.entityPlayer;
    if (entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optional = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optional.isPresent())
      return; 
    boolean shouldTransformItem = false;
    for (ICosmetic equippedCosmetic : ((CosmeticPlayer.EquippedCosmetic)optional.get()).getCosmetics()) {
      if (equippedCosmetic instanceof WearableCosmeticClient) {
        WearableCosmeticClient cosmetic = (WearableCosmeticClient)equippedCosmetic;
        if (!MinecraftForge.EVENT_BUS.post((Event)new CosmeticRenderEvent.Pre((Entity)entity, (ICosmetic)cosmetic))) {
          WearableCosmeticRenderer.render((Entity)entity, cosmetic, event.partialRenderTick);
          if (event.renderer.field_77109_a.field_78112_f.field_78807_k)
            shouldTransformItem = true; 
          MinecraftForge.EVENT_BUS.post((Event)new CosmeticRenderEvent.Post((Entity)entity, (ICosmetic)cosmetic));
        } 
      } 
    } 
    if (shouldTransformItem) {
      ModelRenderer modelRenderer = event.renderer.field_77109_a.field_78112_f;
      if (modelRenderer.field_78795_f == 0.0F && modelRenderer.field_78796_g == 0.0F && modelRenderer.field_78808_h == 0.0F) {
        if (modelRenderer.field_78800_c != 0.0F || modelRenderer.field_78797_d != 0.0F || modelRenderer.field_78798_e != 0.0F) {
          GL11.glTranslatef(modelRenderer.field_78800_c / 16.0F, modelRenderer.field_78797_d / 16.0F, modelRenderer.field_78798_e / 16.0F);
          GL11.glTranslatef(modelRenderer.field_82906_o, modelRenderer.field_82908_p, modelRenderer.field_82907_q);
        } 
      } else {
        GL11.glTranslatef(modelRenderer.field_78800_c / 16.0F, modelRenderer.field_78797_d / 16.0F, modelRenderer.field_78798_e / 16.0F);
        GL11.glTranslatef(modelRenderer.field_82906_o, modelRenderer.field_82908_p, modelRenderer.field_82907_q);
        if (modelRenderer.field_78808_h != 0.0F)
          GL11.glRotatef(modelRenderer.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F); 
        if (modelRenderer.field_78796_g != 0.0F)
          GL11.glRotatef(modelRenderer.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F); 
        if (modelRenderer.field_78795_f != 0.0F)
          GL11.glRotatef(modelRenderer.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F); 
      } 
      event.renderCape = false;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onRenderPlayerArmor(RenderPlayerEvent.SetArmorModel event) {
    if (CommonModule.getInstance().getCombatTag().inFight() && event.entityPlayer != (Minecraft.func_71410_x()).field_71439_g)
      return; 
    EntityPlayer entity = event.entityPlayer;
    if (entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optional = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optional.isPresent())
      return; 
    for (ICosmetic equippedCosmetic : ((CosmeticPlayer.EquippedCosmetic)optional.get()).getCosmetics()) {
      if (equippedCosmetic instanceof WearableCosmeticClient) {
        WearableCosmeticClient cosmetic = (WearableCosmeticClient)equippedCosmetic;
        if (cosmetic.isLoaded()) {
          WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty.WearableCosmeticHideArmorProperty hideArmorProperty = ((WearableCosmetic.WearableCosmeticProperties)cosmetic.getProperties()).getHide().getArmor();
          if ((event.slot == 0 && hideArmorProperty.isBoots()) || (event.slot == 1 && hideArmorProperty.isLeggings()) || (event.slot == 2 && hideArmorProperty.isChestplate()) || (event.slot == 3 && hideArmorProperty.isHelmet())) {
            event.result = -2;
            return;
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerParts(RenderPlayerEvent.Pre event) {
    if (CommonModule.getInstance().getCombatTag().inFight() && event.entityPlayer != (Minecraft.func_71410_x()).field_71439_g)
      return; 
    EntityPlayer entity = event.entityPlayer;
    if (entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)event.entityPlayer);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optional = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optional.isPresent())
      return; 
    for (ICosmetic equippedCosmetic : ((CosmeticPlayer.EquippedCosmetic)optional.get()).getCosmetics()) {
      if (equippedCosmetic instanceof WearableCosmeticClient) {
        WearableCosmeticClient cosmetic = (WearableCosmeticClient)equippedCosmetic;
        if (cosmetic.isLoaded()) {
          WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty.WearableCosmeticHidePartProperty hidePartProperty = ((WearableCosmetic.WearableCosmeticProperties)cosmetic.getProperties()).getHide().getPart();
          if (hidePartProperty.isHead()) {
            event.renderer.field_77109_a.field_78116_c.field_78807_k = true;
            event.renderer.field_77109_a.field_78114_d.field_78807_k = true;
            event.renderer.field_77109_a.field_78116_c.field_78806_j = false;
            event.renderer.field_77109_a.field_78114_d.field_78806_j = false;
            event.renderer.field_77108_b.field_78116_c.field_78807_k = true;
            event.renderer.field_77108_b.field_78114_d.field_78807_k = true;
            event.renderer.field_77111_i.field_78116_c.field_78807_k = true;
            event.renderer.field_77111_i.field_78114_d.field_78807_k = true;
          } 
          if (hidePartProperty.isBody()) {
            event.renderer.field_77109_a.field_78115_e.field_78807_k = true;
            event.renderer.field_77109_a.field_78115_e.field_78806_j = false;
            event.renderer.field_77108_b.field_78115_e.field_78807_k = true;
            event.renderer.field_77111_i.field_78115_e.field_78807_k = true;
          } 
          if (hidePartProperty.isLeftArm()) {
            event.renderer.field_77109_a.field_78113_g.field_78807_k = true;
            event.renderer.field_77109_a.field_78113_g.field_78806_j = false;
            event.renderer.field_77108_b.field_78113_g.field_78807_k = true;
            event.renderer.field_77111_i.field_78113_g.field_78807_k = true;
          } 
          if (hidePartProperty.isRightArm()) {
            event.renderer.field_77109_a.field_78112_f.field_78807_k = true;
            event.renderer.field_77109_a.field_78112_f.field_78806_j = false;
            event.renderer.field_77108_b.field_78112_f.field_78807_k = true;
            event.renderer.field_77111_i.field_78112_f.field_78807_k = true;
          } 
          if (hidePartProperty.isLeftLeg()) {
            event.renderer.field_77109_a.field_78124_i.field_78807_k = true;
            event.renderer.field_77109_a.field_78124_i.field_78806_j = false;
            event.renderer.field_77108_b.field_78124_i.field_78807_k = true;
            event.renderer.field_77111_i.field_78124_i.field_78807_k = true;
          } 
          if (hidePartProperty.isRightLeg()) {
            event.renderer.field_77109_a.field_78123_h.field_78807_k = true;
            event.renderer.field_77109_a.field_78123_h.field_78806_j = false;
            event.renderer.field_77108_b.field_78123_h.field_78807_k = true;
            event.renderer.field_77111_i.field_78123_h.field_78807_k = true;
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerParts(RenderPlayerEvent.Post event) {
    event.renderer.field_77109_a.field_78116_c.field_78807_k = false;
    event.renderer.field_77109_a.field_78114_d.field_78807_k = false;
    event.renderer.field_77109_a.field_78115_e.field_78807_k = false;
    event.renderer.field_77109_a.field_78113_g.field_78807_k = false;
    event.renderer.field_77109_a.field_78112_f.field_78807_k = false;
    event.renderer.field_77109_a.field_78124_i.field_78807_k = false;
    event.renderer.field_77109_a.field_78123_h.field_78807_k = false;
    event.renderer.field_77109_a.field_78116_c.field_78806_j = true;
    event.renderer.field_77109_a.field_78114_d.field_78806_j = true;
    event.renderer.field_77109_a.field_78115_e.field_78806_j = true;
    event.renderer.field_77109_a.field_78113_g.field_78806_j = true;
    event.renderer.field_77109_a.field_78112_f.field_78806_j = true;
    event.renderer.field_77109_a.field_78124_i.field_78806_j = true;
    event.renderer.field_77109_a.field_78123_h.field_78806_j = true;
    event.renderer.field_77108_b.field_78116_c.field_78807_k = false;
    event.renderer.field_77108_b.field_78114_d.field_78807_k = false;
    event.renderer.field_77108_b.field_78115_e.field_78807_k = false;
    event.renderer.field_77108_b.field_78113_g.field_78807_k = false;
    event.renderer.field_77108_b.field_78112_f.field_78807_k = false;
    event.renderer.field_77108_b.field_78124_i.field_78807_k = false;
    event.renderer.field_77108_b.field_78123_h.field_78807_k = false;
    event.renderer.field_77111_i.field_78116_c.field_78807_k = false;
    event.renderer.field_77111_i.field_78114_d.field_78807_k = false;
    event.renderer.field_77111_i.field_78115_e.field_78807_k = false;
    event.renderer.field_77111_i.field_78113_g.field_78807_k = false;
    event.renderer.field_77111_i.field_78112_f.field_78807_k = false;
    event.renderer.field_77111_i.field_78124_i.field_78807_k = false;
    event.renderer.field_77111_i.field_78123_h.field_78807_k = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\listener\WearableCosmeticRenderPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */