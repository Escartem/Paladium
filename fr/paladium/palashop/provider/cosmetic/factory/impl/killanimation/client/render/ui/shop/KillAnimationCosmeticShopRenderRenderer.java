package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class KillAnimationCosmeticShopRenderRenderer implements ShopElementRenderer<CosmeticShopItem> {
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
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof KillAnimationCosmeticClient))
      return; 
    KillAnimationCosmeticClient cosmetic = (KillAnimationCosmeticClient)optionalCosmetic.get();
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    Signal<KillAnimationCosmeticClient> signal = new Signal(cosmetic);
    if (signal.getOrDefault() == null)
      cosmetic.onLoaded(loadedCosmetic -> signal.set(loadedCosmetic)); 
    ((EntityViewerNode)EntityViewerNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .onInit(node -> {
          if (signal.getOrDefault() != null) {
            EntityKillAnimationCosmetic entity = new EntityKillAnimationCosmetic(player.field_70170_p);
            entity.setCosmetic((ICosmetic)signal.getOrDefault());
            entity.setPlayer(player.func_70005_c_());
            node.entity((EntityLivingBase)entity);
            if (cosmetic.getKillModel().isAnimated())
              ((LindwormAnimatable)cosmetic.getKillModel().getOrCreateAnimatable((Entity)entity)).playAnimation("spawn", true); 
            if (cosmetic.getPlayerModel() != null && cosmetic.getPlayerModel().isAnimated())
              ((LindwormAnimatable)cosmetic.getPlayerModel().getOrCreateAnimatable((Entity)entity)).playAnimation("spawn", true); 
          } 
        })).sizeRange(0.2D, 1.2D)
      .rotationPitchRange(-35.0D, 35.0D)
      .rotationYaw(135.0D)
      .yOffset(150.0D)
      .size(0.5D)
      .effect(MaskNodeEffect::create)
      .watch(signal)
      .visible(node -> (signal.getOrDefault() != null))
      .attach(container);
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(KillAnimationCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\rende\\ui\shop\KillAnimationCosmeticShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */