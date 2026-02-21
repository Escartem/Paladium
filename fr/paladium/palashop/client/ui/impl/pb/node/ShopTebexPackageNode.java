package fr.paladium.palashop.client.ui.impl.pb.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.common.tebex.TebexManager;
import fr.paladium.palashop.common.tebex.dto.TebexPackage;
import fr.paladium.palashop.server.config.tebex.TebexPackageConfig;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
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
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public final class ShopTebexPackageNode extends Node {
  private final BooleanSignal loadingSignal = new BooleanSignal();
  
  private String token;
  
  private TebexPackage tebexPackage;
  
  private TebexPackageConfig tebexPackageConfig;
  
  private boolean recommended;
  
  private Node loadingNode;
  
  private Node fallingSectionNode;
  
  private long lastUpdate;
  
  private boolean cursor;
  
  protected ShopTebexPackageNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static ShopTebexPackageNode create(double x, double y, double width, double height) {
    return new ShopTebexPackageNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    if (this.tebexPackage == null || this.tebexPackageConfig == null) {
      RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
        .color(ColorConstant.LIGHT_DARK)
        .effect((NodeEffect)RoundedNodeEffect.create(20.0F))
        .body(rect -> {
            RectNode.create(0.0D, 0.0D, rect.getWidth(), 293.0D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(20.0F)).attach(rect);
            if (this.tebexPackageConfig != null)
              ImageNode.create(rect.dw(2.0D), 293.0D).resource(Resource.of(this.tebexPackageConfig.getThumbnail())).height(293.0D).anchor(Align.CENTER, Align.END).attach(rect); 
            RectNode.create(rect.dw(2.0D) - 210.0D, 293.0D + rect.ah(-293.0D) / 2.0D - 56.0D, 421.0D, 113.0D).color(ColorConstant.DARK).effect((NodeEffect)RoundedNodeEffect.create(20.0F)).body(()).attach(rect);
          }).attach(this);
      return;
    } 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(20.0F))
      .body(rect -> {
          this.fallingSectionNode = RectNode.create(0.0D, 0.0D, rect.getWidth(), 293.0D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(20.0F)).overflow(OverflowProperty.HIDDEN).attach(rect);
          ((RectNode)RectNode.create(0.0D, 0.0D, rect.getWidth(), 293.0D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(20.0F)).onDraw(())).attach(rect);
          if (this.recommended)
            RectNode.create(15.0D, 15.0D, 150.0D, 36.0D).color(ColorConstant.PRIMARY).effect((NodeEffect)RoundedNodeEffect.create(6.0F)).body(()).attach(rect); 
          ImageNode.create(rect.dw(2.0D), 293.0D).resource(Resource.of(this.tebexPackageConfig.getThumbnail())).height(293.0D).anchor(Align.CENTER, Align.END).attach(rect);
          ((RectNode)RectNode.create(rect.dw(2.0D) - 210.0D, 293.0D + rect.ah(-293.0D) / 2.0D - 56.0D, 421.0D, 113.0D).color(ColorConstant.DARK).effect((NodeEffect)RoundedNodeEffect.create(20.0F)).body(()).onDraw(())).attach(rect);
        }).onClick((node, mouseX, mouseY, clickType) -> {
          this.loadingSignal.set(Boolean.valueOf(true));
          TebexManager.getCheckout(this.token, Minecraft.func_71410_x().func_110432_I().func_111285_a(), this.tebexPackage.getData().getId()).thenAccept(()).exceptionally(());
        }).attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.recommended && !this.cursor) {
      Mouse.setCursorPosition((int)getUi().getAbsoluteX(getAbsoluteX() + dw(2.0D)), (int)(getUi().getAbsoluteHeight(1080.0D) - getUi().getAbsoluteY(getAbsoluteY() + dh(2.0D))));
      this.cursor = true;
    } 
    if (this.loadingNode != null && ((Boolean)this.loadingSignal.getOrDefault()).booleanValue()) {
      GL11.glPushMatrix();
      double resourceX = getX() + this.loadingNode.getAbsoluteX() - getAbsoluteX();
      double resourceY = getY() + this.loadingNode.getAbsoluteY() - getAbsoluteY();
      double resourceWidth = this.loadingNode.getWidth();
      double resourceHeight = resourceWidth;
      GL11.glTranslated(resourceX + resourceWidth / 2.0D, resourceY + resourceHeight / 2.0D, 0.0D);
      GL11.glRotated((System.currentTimeMillis() / 2L % 360L), 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-(resourceX + resourceWidth / 2.0D), -(resourceY + resourceHeight / 2.0D), 0.0D);
      GL11.glTranslated(0.0D, 0.0D, 1.0D);
      DrawUtils.RESOURCE.drawImage(resourceX, resourceY, resourceWidth, resourceHeight, ResourceConstant.ICON_LOADING);
      Color.reset();
      GL11.glPopMatrix();
    } 
    if (this.tebexPackage == null || this.tebexPackageConfig == null || this.fallingSectionNode == null)
      return; 
    if (hoverValue(1.0F) == 0.0F) {
      this.fallingSectionNode.clearChildren();
      this.lastUpdate = 0L;
      return;
    } 
    if (this.fallingSectionNode.getChildren(ItemFallingNode.class).isEmpty()) {
      int row = 8;
      int column = 13;
      for (int ox = 0; ox < 8; ox++) {
        for (int oy = 0; oy < 13; oy++) {
          double size = 30.0D + Math.random() * 25.0D;
          ItemFallingNode.create(ox * dw(8.0D) - 20.0D + Math.random() * 40.0D, oy * dh(12.0D) - 40.0D + Math.random() * 80.0D, size)
            .rotation(Math.random() * 360.0D)
            .zindex((int)size)
            .attach(this.fallingSectionNode);
        } 
      } 
    } 
    if (this.lastUpdate == 0L)
      this.lastUpdate = System.currentTimeMillis(); 
    long now = System.currentTimeMillis();
    long delta = now - this.lastUpdate;
    for (Node child : this.fallingSectionNode.getChildren(ItemFallingNode.class)) {
      if (child.getX() < 20.0D)
        child.x(20.0D); 
      if (child.getX() + child.getWidth() + 20.0D > child.getParent().getWidth())
        child.x(child.getParent().getWidth() - child.getWidth() - 20.0D); 
      child.y(child.getY() + delta * 0.1D * child.getHeight() / 100.0D);
      if (child.getY() > getHeight()) {
        child.y(-dh(7.0D));
        child.x(child.getDefaultX() - 20.0D + Math.random() * 40.0D);
        double size = 30.0D + Math.random() * 25.0D;
        child.size(size, size);
        child.zindex((int)size);
        ((ItemFallingNode)child).rotation(Math.random() * 360.0D);
      } 
    } 
    this.lastUpdate = now;
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    return (super.isHovered(mouseX, mouseY, checkEnabled) || ((Boolean)this.loadingSignal.getOrDefault()).booleanValue());
  }
  
  @NonNull
  public final ShopTebexPackageNode data(@NonNull String token, @NonNull TebexPackage tebexPackage, @NonNull TebexPackageConfig tebexPackageConfig) {
    if (token == null)
      throw new NullPointerException("token is marked non-null but is null"); 
    if (tebexPackage == null)
      throw new NullPointerException("tebexPackage is marked non-null but is null"); 
    if (tebexPackageConfig == null)
      throw new NullPointerException("tebexPackageConfig is marked non-null but is null"); 
    this.token = token;
    this.tebexPackage = tebexPackage;
    this.tebexPackageConfig = tebexPackageConfig;
    return this;
  }
  
  @NonNull
  public final ShopTebexPackageNode recommended(boolean recommended) {
    this.recommended = recommended;
    return this;
  }
  
  private static class ItemFallingNode extends Node {
    private double rotation;
    
    protected ItemFallingNode(double x, double y, double width, double height) {
      super(x, y, width, height);
    }
    
    public static ItemFallingNode create(double x, double y, double size) {
      return new ItemFallingNode(x, y, size, size);
    }
    
    public void draw(double mouseX, double mouseY, float ticks) {
      GL11.glPushMatrix();
      GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
      GL11.glRotated(this.rotation, 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.ITEM_PB);
      GL11.glPopMatrix();
    }
    
    public ItemFallingNode rotation(double rotation) {
      this.rotation = rotation;
      return this;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\pb\node\ShopTebexPackageNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */