package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.provider.cosmetic.client.event.CosmeticRenderEvent;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.world.EmoteCosmeticRenderer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class EmoteCosmeticRenderPlayerListener {
  public static boolean willPlay = false;
  
  private boolean wasPlaying = false;
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onRenderPlayerSpecialPre(RenderPlayerEvent.Specials.Pre event) {
    EntityPlayer entity = event.entityPlayer;
    if (entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(EmoteCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    for (ICosmetic cosmetic : equippedCosmetic.getCosmetics()) {
      if (cosmetic instanceof EmoteCosmeticClient) {
        EmoteCosmeticClient emote = (EmoteCosmeticClient)cosmetic;
        if (emote.isLoaded()) {
          LindwormAnimatable animatable = (LindwormAnimatable)emote.getEmoteModel().getAnimatable((Entity)entity);
          if (animatable != null && animatable.getCurrentAnimation() != null && (animatable.getController() == null || animatable.getController().getAnimationState() != AnimationState.Stopped)) {
            event.renderItem = false;
            event.renderCape = false;
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
    EntityPlayer entity = event.entityPlayer;
    if (entity.func_82150_aj() || entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)entity);
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(EmoteCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    AtomicBoolean playing = new AtomicBoolean(false);
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    for (ICosmetic cosmetic : equippedCosmetic.getCosmetics()) {
      if (cosmetic instanceof EmoteCosmeticClient) {
        EmoteCosmeticClient emote = (EmoteCosmeticClient)cosmetic;
        if (emote.isLoaded()) {
          LindwormAnimatable animatable = (LindwormAnimatable)emote.getEmoteModel().getAnimatable((Entity)entity);
          if (animatable == null || animatable.getCurrentAnimation() == null || (animatable.getController() != null && animatable.getController().getAnimationState() == AnimationState.Stopped)) {
            emote.getEmoteModel().setLivingAnimations((Entity)entity);
            if (emote.getEffectModel() != null)
              emote.getEffectModel().setLivingAnimations((Entity)entity); 
          } else {
            CosmeticRenderEvent.Pre pre = CosmeticRenderEvent.execute((Entity)entity, cosmetic, cosmeticRenderEvent -> {
                  EmoteCosmeticRenderer.addRendering((Entity)entity);
                  if (emote.hasEffectModel()) {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0F, -entity.field_70129_M, 0.0F);
                    GL11.glRotatef(180.0F - entity.field_70177_z, 0.0F, 1.0F, 0.0F);
                    LindwormRenderer.renderModel((Entity)entity, emote.getEffectModel(), ZUI.isOpen(UIShopBase.class));
                    GL11.glPopMatrix();
                  } 
                  if (!this.wasPlaying && entity == (Minecraft.func_71410_x()).field_71439_g)
                    this.wasPlaying = true; 
                  playing.set(true);
                  willPlay = false;
                });
            if (!pre.isCanceled())
              break; 
          } 
        } 
      } 
    } 
    if (!playing.get() && this.wasPlaying && !willPlay && entity == (Minecraft.func_71410_x()).field_71439_g) {
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 0;
      this.wasPlaying = false;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
    EmoteCosmeticRenderer.reset();
    EmoteCosmeticRenderer.removeRendering(event.entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\listener\EmoteCosmeticRenderPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */