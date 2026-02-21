package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.cosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;

public class KillMessageCosmeticCosmeticRenderRenderer implements ShopElementRenderer<CosmeticNavbarElement> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull CosmeticNavbarElement element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    TextNode.create(container.dw(2.0D), 83.0D)
      .text(Text.create(element.getName(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE)).modifier(TextModifier.CAPITALIZE))
      .anchor(Align.CENTER)
      .attach(container);
    CosmeticPlayer player = CosmeticPlayer.me();
    if (player == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = player.getOutfit().get(element.getFactory());
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    FlexNode.vertical(0.0D, 160.0D, container.getWidth())
      .margin(9.0D)
      .align(Align.CENTER)
      .body(flex -> {
          for (ICosmetic cosmetic : equippedCosmetic.getCosmetics())
            RectNode.create(0.0D, 0.0D, 577.0D, 68.0D).color(ColorConstant.BLACK).effect((NodeEffect)RoundedNodeEffect.create(13.0F)).body(()).attach(flex); 
        }).attach(container);
  }
  
  public boolean isValid(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return element.getFactory().equals(KillMessageCosmeticFactory.ID);
  }
  
  @NonNull
  public Class<CosmeticNavbarElement> getElementType() {
    return CosmeticNavbarElement.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\rende\\ui\cosmetic\KillMessageCosmeticCosmeticRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */