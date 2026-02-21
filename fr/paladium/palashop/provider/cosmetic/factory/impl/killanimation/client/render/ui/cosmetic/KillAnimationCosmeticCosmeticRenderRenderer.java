package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.ui.cosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class KillAnimationCosmeticCosmeticRenderRenderer implements ShopElementRenderer<CosmeticNavbarElement> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull CosmeticNavbarElement element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.me();
    Optional<ICosmetic> optionalCosmetic = ((CosmeticPlayer.EquippedCosmetic)cosmeticPlayer.getOutfit().get(element.getFactory()).get()).get(0);
    TextNode.create(container.dw(2.0D), 100.0D)
      .text(Text.create(element.getName(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE)).modifier(TextModifier.CAPITALIZE))
      .anchor(Align.CENTER)
      .attach(container);
    double size = container.getWidth() * 0.68D;
    RectNode.create(container.dw(2.0D) - size / 2.0D, container.dh(2.0D) - size / 2.0D, size, size)
      .color(ColorConstant.BLACK.copy())
      .effect((NodeEffect)RoundedNodeEffect.create((float)size / 20.0F))
      .body(rect -> RectNode.create(10.0D, 10.0D, rect.aw(-20.0D), rect.ah(-20.0D)).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create((float)size / 20.0F - 10.0F)).attach(rect))
      
      .attach(container);
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof KillAnimationCosmeticClient)) {
      ImageNode.create(container.dw(2.0D), container.dh(2.0D))
        .color(Color.WHITE.copyAlpha(0.08F))
        .resource(element.getResource())
        .width(container.dw(2.0D))
        .anchor(Align.CENTER)
        .attach(container);
      return;
    } 
    KillAnimationCosmeticClient cosmetic = (KillAnimationCosmeticClient)optionalCosmetic.get();
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    Signal<KillAnimationCosmeticClient> signal = new Signal(cosmetic);
    if (signal.getOrDefault() == null)
      cosmetic.onLoaded(loadedCosmetic -> signal.set(loadedCosmetic)); 
    ((EntityViewerNode)EntityViewerNode.create(container.dw(2.0D) - size / 2.0D + 10.0D, container.dh(2.0D) - size / 2.0D + 10.0D, size - 20.0D, size - 20.0D)
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
      .yOffset(100.0D)
      .size(0.5D)
      .effect(MaskNodeEffect::create)
      .watch(signal)
      .visible(node -> (signal.getOrDefault() != null))
      .attach(container);
  }
  
  public boolean isValid(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return element.getFactory().equals(KillAnimationCosmeticFactory.ID);
  }
  
  @NonNull
  public Class<CosmeticNavbarElement> getElementType() {
    return CosmeticNavbarElement.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\rende\\ui\cosmetic\KillAnimationCosmeticCosmeticRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */