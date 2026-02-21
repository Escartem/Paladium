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
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class EmoteCosmeticShopThumbnailRenderer implements ShopElementRenderer<CosmeticShopItem> {
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
    cosmetic.onLoaded(loadedCosmetic -> {
          ((LindwormAnimatable)loadedCosmetic.getEmoteModel().getOrCreateAnimatable((Entity)player)).playAnimation(loadedCosmetic.getEmoteModel().getAnimationFile().getFirstAnimation(), true);
          if (loadedCosmetic.hasEffectModel() && loadedCosmetic.getEffectModel().isAnimated()) {
            String effectAnimationName = loadedCosmetic.getEffectModel().getAnimationFile().getFirstAnimation();
            if (effectAnimationName != null)
              ((LindwormAnimatable)loadedCosmetic.getEffectModel().getOrCreateAnimatable((Entity)player)).playAnimation(effectAnimationName, true); 
          } 
        });
    EntityNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .rotationYaw(-30.0D)
      .yOffset(10.0D)
      .pipeLineLevel(0.0D)
      .effect(MaskNodeEffect::create)
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\rende\\ui\shop\EmoteCosmeticShopThumbnailRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */