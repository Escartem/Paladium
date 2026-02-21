package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;

public class KillMessageCosmeticShopRenderRenderer implements ShopElementRenderer<CosmeticShopItem> {
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
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof KillMessageCosmeticClient))
      return; 
    ((KillMessageCosmeticClient)optionalCosmetic.get()).onLoaded(cosmetic -> {
          String message = cosmetic.getFormattedMessage((Minecraft.func_71410_x()).field_71439_g.func_70005_c_(), "{Victime}").replace("§[", "[");
          Text sourceText = Text.create(message, TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER);
          float ratio = (float)(container.aw(-40.0D) / sourceText.aw(40.0D));
          Text text = Text.create(message, TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F * ratio, Color.WHITE)).align(Align.CENTER, Align.CENTER);
          double width = text.aw(40.0D);
          double height = text.getHeight() * 2.0D;
          RectNode.create(container.dw(2.0D) - width / 2.0D, container.dh(2.0D) - height / 2.0D, width, height).color(ColorConstant.BLACK).effect((NodeEffect)RoundedNodeEffect.create((float)(height * 0.10999999940395355D))).body(()).attach(container);
        });
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(KillMessageCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\rende\\ui\shop\KillMessageCosmeticShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */