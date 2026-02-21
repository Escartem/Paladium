package fr.paladium.palashop.client.ui.kit.item;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.pricing.PricingNode;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementThumbnailRendererNode;
import fr.paladium.palashop.common.shop.network.item.BBPacketGetItem;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.CircleNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ShopItemNode extends Node {
  private final Resource background;
  
  private final Signal<ConditionalBuyableObject<IShopItem>> shopItemSignal;
  
  private final TweenAnimator rotationAnimator;
  
  public Resource getBackground() {
    return this.background;
  }
  
  public Signal<ConditionalBuyableObject<IShopItem>> getShopItemSignal() {
    return this.shopItemSignal;
  }
  
  public TweenAnimator getRotationAnimator() {
    return this.rotationAnimator;
  }
  
  protected ShopItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.background = Resource.of(new ResourceLocation("palashop", "textures/ui/global/card/background.png"));
    this.shopItemSignal = new Signal();
    this.rotationAnimator = TweenAnimator.create(0.0F);
    wait((ISignal)this.shopItemSignal);
    watch(this.shopItemSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD });
    onRender(new NodeRenderCallback<ShopItemNode>() {
          public void apply(ShopItemNode node, double mouseX, double mouseY, float partialTicks) {}
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
          public void pre(@NonNull ShopItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            ShopItemNode.this.onPreRender(mouseX, mouseY, partialTicks);
          }
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
          public void post(@NonNull ShopItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            ShopItemNode.this.onPostRender(mouseX, mouseY, partialTicks);
          }
        });
  }
  
  @NonNull
  public static ShopItemNode create(double x, double y, double width, double height) {
    return new ShopItemNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    float borderRadius = (float)(getHeight() * 0.0423D);
    if (!isMounted() || this.shopItemSignal.getOrDefault() == null) {
      if (this.rotationAnimator.getValue() % 360.0F == 0.0F) {
        skeleton(node -> RectNode.create(0.0D, 0.0D, getWidth(), getHeight()).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).body(()));
      } else {
        skeleton(node -> RectNode.create(0.0D, 0.0D, getWidth(), getHeight()).color(Color.LOADING).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).zlevel(-0.01D).body(()));
      } 
      return;
    } 
    ConditionalBuyableObject<IShopItem> shopItem = (ConditionalBuyableObject<IShopItem>)this.shopItemSignal.getOrDefault();
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(ShopRarityConstant.getColor(shopItem.getRarity()), ShopRarityConstant.getColor(shopItem.getRarity()).brighter(0.3F))
      .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
      .body(rect -> {
          RectNode.create(5.0D, 5.0D, rect.aw(-10.0D), rect.ah(-10.0D)).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create(borderRadius - 5.0F)).zlevel(-0.02D).attach(rect);
          ImageNode.create(5.0D, 5.0D).resource(this.background.textureCoords(0.0D, 0.0D, rect.aw(-10.0D), rect.ah(-10.0D))).height(rect.ah(-10.0D)).zlevel(-0.03D).attach(rect);
          ImageNode.create(rect.dw(2.0D), rect.dh(2.0D)).resource(ShopRarityConstant.getResource(shopItem.getRarity())).width(rect.dw(2.0D)).anchor(Align.CENTER).zlevel(-0.04D).attach(rect);
        }).zlevel(-0.01D)
      .visible(node -> (this.rotationAnimator.getValue() >= 90.0F && this.rotationAnimator.getValue() <= 270.0F))
      .attach(this);
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
      .body(rect -> {
          RectNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight() * 0.647D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).attach(rect);
          ShopElementThumbnailRendererNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight() * 0.647D).data("shop_thumbnail", ((ConditionalBuyableObject)this.shopItemSignal.getOrDefault()).getObject().getItem()).attach(rect);
          double pricingWidth = rect.getWidth() * 0.913D;
          double pricingMargin = rect.dw(2.0D) - pricingWidth / 2.0D;
          double pricingHeight = rect.getHeight() - rect.getHeight() * 0.647D - pricingMargin * 2.0D;
          PricingNode.create(pricingMargin, rect.ah(-(pricingHeight + pricingMargin)), pricingWidth, pricingHeight).item(shopItem).color(ColorConstant.DARK).radius(borderRadius).enabled(()).attach(rect);
        }).enabled(pricingNode -> (this.rotationAnimator.getValue() % 360.0F == 0.0F))
      .attach(this);
    double pricingWidth = getWidth() * 0.913D;
    double pricingMargin = dw(2.0D) - pricingWidth / 2.0D;
    double pricingHeight = getHeight() - getHeight() * 0.647D - pricingMargin * 2.0D;
    ImageNode.create(aw(-pricingMargin), pricingMargin)
      .resource(ShopRarityConstant.getResource(((IShopItem)shopItem.getObject().getItem()).getRarity()))
      .width(getWidth() * 0.2D)
      .anchorX(Align.END)
      .attach(this);
    if (shopItem.getObject().getItem() instanceof IShopItem && shopItem.getObject().hasDiscount())
      RectNode.create(pricingMargin, pricingMargin, pricingHeight * 0.8D, pricingHeight * 0.3D)
        .color(ColorConstant.PINK)
        .effect((NodeEffect)RoundedNodeEffect.create(borderRadius / 2.0F))
        .body(discount -> TextNode.create(0.0D, 0.0D, discount.getWidth(), discount.getHeight()).text(Text.create("-" + String.format("%.0f", new Object[] { Float.valueOf(shopItem.getObject().getRealDiscount() * 100.0F) }) + "%", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, (float)pricingHeight * 0.17F, Color.WHITE)).align(Align.CENTER, Align.CENTER)).attach(discount)).attach(this); 
  }
  
  private final void onPreRender(double mouseX, double mouseY, float partialTicks) {
    this.rotationAnimator.update();
    double zlevel = 0.0D;
    for (Node node : getChildren().recursive()) {
      if (node.getZlevel() < 0.0D)
        continue; 
      node.zlevel(zlevel);
      zlevel += 0.01D;
    } 
    double centerX = getX() + dw(2.0D);
    double centerY = getY() + dh(2.0D);
    GL11.glPushMatrix();
    GL11.glDisable(2884);
    GL11.glTranslated(0.0D, 0.0D, dw(2.0D));
    GL11.glTranslated(centerX, centerY, 0.0D);
    GL11.glRotated((this.rotationAnimator.getValue() % 360.0F), 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(-centerX, -centerY, 0.0D);
  }
  
  private final void onPostRender(double mouseX, double mouseY, float partialTicks) {
    GL11.glEnable(2884);
    GL11.glPopMatrix();
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.isCancelled() || !isHovered(mouseX, mouseY) || this.rotationAnimator.getValue() % 360.0F != 180.0F || this.shopItemSignal.getOrDefault() == null)
      return; 
    flip();
    context.cancel();
  }
  
  @NonNull
  public final ShopItemNode shopItem(@NonNull ConditionalBuyableObject<IShopItem> shopItem) {
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    this.shopItemSignal.set(shopItem);
    return this;
  }
  
  @NonNull
  public final ShopItemNode shopItem(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    (new BBPacketGetItem(id, false)).subscribe(result -> this.shopItemSignal.set(result.getItem())).send();
    return this;
  }
  
  @NonNull
  public final ShopItemNode rotate(float rotation) {
    this.rotationAnimator.setValue(rotation);
    return this;
  }
  
  @NonNull
  public final ShopItemNode flip() {
    this.rotationAnimator.sequence(1000.0F, this.rotationAnimator.getValue() + 180.0F, (TweenEquation)TweenEquations.QUAD_INOUT).start();
    SoundConstant.CARD_FLIP.copy().play();
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\item\ShopItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */