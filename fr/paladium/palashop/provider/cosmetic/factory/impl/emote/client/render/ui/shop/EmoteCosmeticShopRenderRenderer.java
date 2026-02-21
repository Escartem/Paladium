package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.shop.node.EmoteCosmeticPropertyTagNode;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class EmoteCosmeticShopRenderRenderer implements ShopElementRenderer<CosmeticShopItem> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull CosmeticShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (element.getCosmeticFactory() == null || element.getCosmeticId() == null)
      return; 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(element.getCosmeticFactory());
    if (!optionalFactory.isPresent())
      return; 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(element.getCosmeticId());
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof EmoteCosmeticClient))
      return; 
    EmoteCosmeticClient cosmetic = (EmoteCosmeticClient)optionalCosmetic.get();
    FakeEntityPlayerMP player = new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    ((CosmeticPlayer.EquippedCosmetic)cosmeticPlayer.getOutfit().get(cosmetic.getFactory().getId()).get()).equip((ICosmetic)cosmetic);
    Signal<EmoteCosmeticClient> cosmeticSignal = new Signal();
    cosmetic.onLoaded(loadedCosmetic -> {
          ((LindwormAnimatable)loadedCosmetic.getEmoteModel().getOrCreateAnimatable((Entity)player)).playAnimation(loadedCosmetic.getEmoteModel().getAnimationFile().getFirstAnimation(), true);
          if (loadedCosmetic.hasEffectModel() && loadedCosmetic.getEffectModel().isAnimated()) {
            String effectAnimationName = loadedCosmetic.getEffectModel().getAnimationFile().getFirstAnimation();
            if (effectAnimationName != null)
              ((LindwormAnimatable)loadedCosmetic.getEffectModel().getOrCreateAnimatable((Entity)player)).playAnimation(effectAnimationName, true); 
          } 
          cosmeticSignal.set(loadedCosmetic);
        });
    EntityViewerNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .sizeRange(0.6D, 1.2D)
      .rotationPitchRange(0.0D, 0.0D)
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .rotationYaw(-30.0D)
      .yOffset(20.0D)
      .pipeLineLevel(0.0D)
      .effect(MaskNodeEffect::create)
      .attach(container);
    FlexNode.horizontal(24.0D, container.ah(-24.0D) - container.getHeight() * 0.069D, container.getHeight() * 0.069D)
      .margin(15.0D)
      .onMount(flex -> {
          EmoteCosmetic.EmoteCosmeticProperties.EmoteCosmeticCancelProperty cancelProperty = ((EmoteCosmetic.EmoteCosmeticProperties)cosmetic.getProperties()).getCancel();
          if (cancelProperty.isMove() && cancelProperty.isSneak() && cancelProperty.isJump() && cancelProperty.isInteract()) {
            EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("static").color(Color.decode("#FF5C00")).attach(flex);
          } else if (!cancelProperty.isMove() && !cancelProperty.isSneak() && !cancelProperty.isJump() && !cancelProperty.isInteract()) {
            EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("all").color(Color.decode("#5AD401")).attach(flex);
          } else {
            if (!cancelProperty.isMove())
              EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("walk").color(Color.decode("#37E3EE")).attach(flex); 
            if (!cancelProperty.isSneak())
              EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("sneak").color(Color.decode("#FFD101")).attach(flex); 
            if (!cancelProperty.isJump())
              EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("jump").color(Color.decode("#1E6AFF")).attach(flex); 
            if (!cancelProperty.isInteract())
              EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("attack").color(Color.decode("#F52037")).attach(flex); 
          } 
          if (((EmoteCosmetic.EmoteCosmeticProperties)cosmetic.getProperties()).isLoop())
            EmoteCosmeticPropertyTagNode.create(0.0D, 0.0D, flex.getHeight()).text("loop").color(Color.decode("#FC65C9")).attach(flex); 
        }).zlevel(1000.0D)
      .wait((ISignal)cosmeticSignal)
      .attach(container);
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(EmoteCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\rende\\ui\shop\EmoteCosmeticShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */