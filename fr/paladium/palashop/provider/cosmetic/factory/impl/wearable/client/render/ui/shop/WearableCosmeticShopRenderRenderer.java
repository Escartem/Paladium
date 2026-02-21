package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.client.ui.kit.selector.SelectorNode;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class WearableCosmeticShopRenderRenderer implements ShopElementRenderer<CosmeticShopItem> {
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
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof WearableCosmeticClient))
      return; 
    WearableCosmeticClient cosmetic = (WearableCosmeticClient)optionalCosmetic.get();
    FakeEntityPlayerMP player = new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    ((CosmeticPlayer.EquippedCosmetic)cosmeticPlayer.getOutfit().get(cosmetic.getFactory().getId()).get()).equip((ICosmetic)cosmetic);
    EntityViewerNode viewerNode = EntityViewerNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight());
    viewerNode
      .sizeRange(0.6D, 1.2D)
      .rotationPitchRange(0.0D, 0.0D)
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .pipeLineLevel(0.0D)
      .effect(MaskNodeEffect::create)
      .attach(container);
    cosmetic.onLoaded(loadedCosmetic -> {
          viewerNode.rotationYaw((cosmetic.getType() == WearableCosmetic.WearableCosmeticType.BODY) ? 210.0D : -30.0D);
          viewerNode.yOffset((cosmetic.getType() == WearableCosmetic.WearableCosmeticType.HEAD) ? 200.0D : 20.0D);
          if (!loadedCosmetic.getModel().isAnimated())
            return; 
          LindwormAnimatable animatable = (LindwormAnimatable)loadedCosmetic.getModel().getOrCreateAnimatable((Entity)player);
          ((SelectorNode)SelectorNode.create(22.0D, 33.0D, 190.0D).onChange(()).body(()).visible(())).attach(container);
        });
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(WearableCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\rende\\ui\shop\WearableCosmeticShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */