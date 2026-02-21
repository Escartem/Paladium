package fr.paladium.palashop.client.ui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.api.server.pb.dto.PBData;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.giftcard.UIShopGiftCardPage;
import fr.paladium.palashop.client.ui.impl.pb.UIShopPBPopup;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarPage;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarTab;
import fr.paladium.palashop.client.ui.store.UIShopStore;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
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
import fr.paladium.zephyrui.lib.ui.node.hover.HoverElement;
import fr.paladium.zephyrui.lib.ui.node.hover.impl.NodeHoverElement;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class ShopNavbarNode extends Node {
  private final Resource selector;
  
  private final TweenAnimator pageAnimatorX;
  
  private final TweenAnimator pageAnimatorWidth;
  
  @NonNull
  private ShopNavbarElement current;
  
  protected ShopNavbarNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.selector = Resource.of(new ResourceLocation("palashop", "textures/ui/navbar/selector.png"));
    this.pageAnimatorX = TweenAnimator.create(0.0F);
    this.pageAnimatorWidth = TweenAnimator.create(0.0F);
  }
  
  @NonNull
  public static ShopNavbarNode create(double x, double y, double width, double height) {
    return new ShopNavbarNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    if (this.current == null)
      throw new RuntimeException("Unable to initialize ShopNavbarNode without a ShopNavbarElement"); 
    UIShopPage uiShopPage = (UIShopPage)getUi();
    if (uiShopPage == null)
      throw new RuntimeException("Unable to initialize ShopNavbarNode without a UIShopPage"); 
    UIShopStore store = uiShopPage.getStore();
    Node pbHoverNode = RectNode.create(1074.0D, 106.0D, 580.0D, 228.0D).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create(7.0F)).body(rect -> {
          rect.ui(ui);
          TextNode.create(40.0D, 40.0D).text(Text.create("PB Permanent", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE))).attach(rect);
          TextNode.create(40.0D, 75.0D).text(Text.create("Ces points boutiques sont disponibles pour toutes les saisons.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 12.0F, ColorConstant.LIGHT_GRAY))).mode(TextMode.SPLIT).width(282.0D).attach(rect);
          FlexNode.horizontal(rect.aw(-40.0D), 40.0D, 21.0D).margin(10.0D).align(Align.CENTER).body(()).anchorX(Align.END).attach(rect);
          TextNode.create(40.0D, 123.0D).text(Text.create("PB Saisonnier", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE))).attach(rect);
          TextNode.create(40.0D, 158.0D).text(Text.create("Ces points boutiques sont disponibles durant la saison actuelle mais reset après celle-ci.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 12.0F, ColorConstant.LIGHT_GRAY))).mode(TextMode.SPLIT).width(282.0D).attach(rect);
          FlexNode.horizontal(rect.aw(-40.0D), 123.0D, 21.0D).margin(10.0D).align(Align.CENTER).body(()).anchorX(Align.END).attach(rect);
        }).watch(store.getPbSignal(), new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY });
    RectNode.create(0.0D, getHeight(), getWidth(), 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    ImageNode.create(54.0D, 35.0D, 40.0D, 43.0D)
      .resource(ResourceConstant.LOGO)
      .attach(this);
    RectNode.create(152.0D, 0.0D, 2.0D, getHeight())
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    FlexNode.horizontal(152.0D, 0.0D, getHeight())
      .body(flex -> {
          double ox = 152.0D;
          boolean hasActive = false;
          for (ShopNavbarPage page : ShopNavbarManager.getPageList().ordered()) {
            AtomicBoolean visible = new AtomicBoolean(false);
            page.isAvailable().thenAccept(());
            boolean active = page.equals(this.current);
            Text text = Text.create(page.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER);
            double nodeWidth = text.getWidth() + 50.0D;
            ContainerNode.create(0.0D, 0.0D, nodeWidth, flex.getHeight()).body(()).visible(()).attach(flex);
            if (active) {
              if (this.pageAnimatorX.getValue() == 0.0F || this.pageAnimatorWidth.getValue() == 0.0F) {
                this.pageAnimatorX.setValue((float)ox);
                this.pageAnimatorWidth.setValue((float)nodeWidth);
              } else {
                this.pageAnimatorX.sequence(200.0F, (float)ox, (TweenEquation)TweenEquations.QUAD_INOUT).start();
                this.pageAnimatorWidth.sequence(200.0F, (float)nodeWidth, (TweenEquation)TweenEquations.QUAD_INOUT).start();
              } 
              hasActive = true;
            } 
            ox += nodeWidth;
          } 
          if (!hasActive) {
            this.pageAnimatorX.setValue(0.0F);
            this.pageAnimatorWidth.setValue(0.0F);
          } 
        }).attach(this);
    ImageNode.create(aw(-112.0D) + 56.0D - 10.0D, dh(2.0D) - 10.0D, 20.0D, 20.0D)
      .resource(ResourceConstant.ICON_CLOSE)
      .onClick((node, mouseX, mouseY, clickType) -> Minecraft.func_71410_x().func_147108_a(null))
      
      .attach(this);
    RectNode.create(aw(-112.0D), 0.0D, 2.0D, getHeight())
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    FlexNode.horizontal(aw(-112.0D), 0.0D, getHeight())
      .align(Align.CENTER)
      .anchorX(Align.END)
      .body(flex -> {
          RectNode.create(0.0D, 0.0D, 2.0D, flex.getHeight()).color(ColorConstant.LIGHT_DARK).attach(flex);
          ContainerNode.create(0.0D, 0.0D, 55.0D, flex.getHeight()).attach(flex);
          FlexNode.horizontal(0.0D, 0.0D, flex.getHeight()).align(Align.CENTER).margin(76.0D).body(()).attach(flex);
          ContainerNode.create(0.0D, 0.0D, 55.0D, flex.getHeight()).attach(flex);
          RectNode.create(0.0D, 0.0D, 2.0D, flex.getHeight()).color(ColorConstant.LIGHT_DARK).attach(flex);
          ContainerNode.create(0.0D, 0.0D, 30.0D, flex.getHeight()).attach(flex);
          FlexNode.vertical(0.0D, 0.0D, 0.0D).body(()).onDraw(()).attach(flex);
          ContainerNode.create(0.0D, 0.0D, 30.0D, flex.getHeight()).attach(flex);
        }).attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.pageAnimatorX.update();
    this.pageAnimatorWidth.update();
    if (this.pageAnimatorX.getValue() == 0.0F || this.pageAnimatorWidth.getValue() == 0.0F)
      return; 
    double selectorX = this.pageAnimatorX.getValue();
    double selectorWidth = this.pageAnimatorWidth.getValue();
    GL11.glTranslated(0.0D, 0.0D, 1.0D);
    DrawUtils.RESOURCE.drawImage(selectorX + selectorWidth / 2.0D - 11.0D, getY(), 22.0D, 11.0D, this.selector);
    DrawUtils.SHAPE.drawRect(selectorX, getY() + ah(-6.0D), selectorWidth, 6.0D, ColorConstant.PRIMARY);
    GL11.glTranslated(0.0D, 0.0D, -1.0D);
  }
  
  @NonNull
  public final ShopNavbarNode current(@NonNull ShopNavbarElement current) {
    if (current == null)
      throw new NullPointerException("current is marked non-null but is null"); 
    this.current = current;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\node\ShopNavbarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */