package fr.paladium.palashop.provider.box.client.overlay.node;

import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ShopBoxBoostNode extends Node {
  private static final Resource BOOST_EPIC_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/overlay/boxes/boost/boost_epic.png")).linear();
  
  private static final Resource BOOST_LEGENDARY_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/overlay/boxes/boost/boost_legendary.png")).linear();
  
  private Node mask;
  
  private ShopRarity rarity;
  
  public Node getMask() {
    return this.mask;
  }
  
  public ShopRarity getRarity() {
    return this.rarity;
  }
  
  protected ShopBoxBoostNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static ShopBoxBoostNode create(double x, double y, double width, double height) {
    return new ShopBoxBoostNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    float borderRadius = (float)(h() * 0.0985D);
    RectNode.create(0.0D, 0.0D, w(), h())
      .color(ColorConstant.LIGHT_GRAY)
      .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
      .body(rect -> ImageNode.create(0.0D, 0.0D, rect.w(), rect.h()).resource(
          (this.rarity == ShopRarity.COMMON || this.rarity == ShopRarity.RARE) ? BOOST_EPIC_RESOURCE : BOOST_LEGENDARY_RESOURCE).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).attach(rect))
      
      .attach(this);
  }
  
  public boolean isVisible() {
    if (this.mask != null && (
      getAbsoluteX() + getWidth() < this.mask.getAbsoluteX() || getAbsoluteX() > this.mask.getAbsoluteX() + this.mask.getWidth() || getAbsoluteY() + getHeight() < this.mask.getAbsoluteY() || getAbsoluteY() > this.mask.getAbsoluteY() + this.mask.getHeight()))
      return false; 
    return super.isVisible();
  }
  
  @NonNull
  public final ShopBoxBoostNode rarity(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    this.rarity = rarity;
    return this;
  }
  
  @NonNull
  public final ShopBoxBoostNode mask(@NonNull Node mask) {
    if (mask == null)
      throw new NullPointerException("mask is marked non-null but is null"); 
    this.mask = mask;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\node\ShopBoxBoostNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */