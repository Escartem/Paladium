package fr.paladium.palashop.provider.box.client.overlay.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementThumbnailRendererNode;
import fr.paladium.palashop.provider.box.client.overlay.UIShopBoxOverlay;
import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ShopBoxItemNode extends Node {
  private Node mask;
  
  private BoxReward reward;
  
  public Node getMask() {
    return this.mask;
  }
  
  public BoxReward getReward() {
    return this.reward;
  }
  
  protected ShopBoxItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static ShopBoxItemNode create(double x, double y, double width, double height) {
    return new ShopBoxItemNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    float borderRadius = (float)(h() * 0.0985D);
    if (this.reward.getType() == BoxReward.Type.SHOP_ITEM) {
      IShopItem item = this.reward.getShopItem();
      RectNode.create(0.0D, 0.0D, w(), h())
        .color(ShopRarityConstant.getColor(item.getRarity()))
        .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
        .body(rect -> {
            RectNode.create(0.0D, 0.0D, rect.w(), rect.h() * 0.76D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).attach(rect);
            ShopElementThumbnailRendererNode.create(0.0D, 0.0D, rect.w(), rect.h() * 0.76D).data("shop_thumbnail", item).attach(rect);
            ImageNode.create(rect.aw(-rect.w() * 0.01D), rect.w() * 0.01D).resource(ShopRarityConstant.getResource(item.getRarity())).width(rect.w() * 0.25D).anchorX(Align.END).zlevel(100.0D).attach(rect);
            int fontSize = 26;
            Text text = Text.create(item.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, fontSize, Color.WHITE), Align.CENTER);
            while (DrawUtils.TEXT.getLines(rect.aw(-borderRadius), text).size() > 2 && fontSize > 1)
              text = Text.create(item.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, --fontSize, Color.WHITE), Align.CENTER); 
            TextNode.create((borderRadius / 2.0F), rect.h() * 0.89D).text(text).mode(TextMode.SPLIT).width(rect.aw(-borderRadius)).anchorY(Align.CENTER).attach(rect);
          }).attach(this);
    } else {
      RectNode.create(0.0D, 0.0D, w(), h())
        .color(ColorConstant.GRAY)
        .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
        .body(rect -> ImageNode.create(0.0D, 0.0D, rect.w(), rect.h()).resource(Resource.of(new ResourceLocation("palashop", "textures/overlay/boxes/boost/" + this.reward.getType().name().toLowerCase() + ".png"))).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).attach(rect))
        
        .attach(this);
    } 
  }
  
  public boolean isVisible() {
    if (getUi() instanceof UIShopBoxOverlay && ((UIShopBoxOverlay)getUi()).isMask())
      return false; 
    if (this.mask != null && (
      getAbsoluteX() + getWidth() < this.mask.getAbsoluteX() || getAbsoluteX() > this.mask.getAbsoluteX() + this.mask.getWidth() || getAbsoluteY() + getHeight() < this.mask.getAbsoluteY() || getAbsoluteY() > this.mask.getAbsoluteY() + this.mask.getHeight()))
      return false; 
    return super.isVisible();
  }
  
  @NonNull
  public final ShopBoxItemNode reward(@NonNull BoxReward reward) {
    if (reward == null)
      throw new NullPointerException("reward is marked non-null but is null"); 
    this.reward = reward;
    return this;
  }
  
  @NonNull
  public final ShopBoxItemNode mask(@NonNull Node mask) {
    if (mask == null)
      throw new NullPointerException("mask is marked non-null but is null"); 
    this.mask = mask;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\node\ShopBoxItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */