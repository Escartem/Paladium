package fr.paladium.paladiumui.kit.background;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class BackgroundNode extends Node {
  private Color backgroundColor = ColorConstant.BACKGROUND;
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  private Color borderColor = ColorConstant.LIGHT_BACKGROUND;
  
  private Resource resource;
  
  public Color getBorderColor() {
    return this.borderColor;
  }
  
  public Resource getResource() {
    return this.resource;
  }
  
  private float resourceOpacity = 0.3F;
  
  public float getResourceOpacity() {
    return this.resourceOpacity;
  }
  
  private double innerRadius = 10.0D;
  
  public double getInnerRadius() {
    return this.innerRadius;
  }
  
  private double outerRadius = 35.0D;
  
  public double getOuterRadius() {
    return this.outerRadius;
  }
  
  protected BackgroundNode(double width, double height) {
    super(960.0D - width / 2.0D, 540.0D - height / 2.0D, width, height);
  }
  
  protected BackgroundNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static BackgroundNode create(double x, double y, double width, double height) {
    return new BackgroundNode(x, y, width, height);
  }
  
  @NonNull
  public static BackgroundNode create(double width, double height) {
    return new BackgroundNode(width, height);
  }
  
  @NonNull
  public static BackgroundNode create() {
    return new BackgroundNode(1850.0D, 1010.0D);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor);
    double outerInnerRadius = this.innerRadius * 1.5D;
    DrawUtils.SHAPE.drawRect(getX() - this.outerRadius, getY() - outerInnerRadius, this.outerRadius, getHeight() + outerInnerRadius * 2.0D, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY() - outerInnerRadius, this.outerRadius, getHeight() + outerInnerRadius * 2.0D, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() - outerInnerRadius, getY() - this.outerRadius, getWidth() + outerInnerRadius * 2.0D, this.outerRadius, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() - outerInnerRadius, getY() + getHeight(), getWidth() + outerInnerRadius * 2.0D, this.outerRadius, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() - this.innerRadius, getY(), this.innerRadius, getHeight(), this.borderColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY(), this.innerRadius, getHeight(), this.borderColor);
    DrawUtils.SHAPE.drawRect(getX(), getY() - this.innerRadius, getWidth(), this.innerRadius, this.borderColor);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), this.innerRadius, this.borderColor);
    if (this.resource == null)
      return; 
    getUi().mask(getX() - this.outerRadius, getY() - this.outerRadius, getWidth() + this.outerRadius * 2.0D, getHeight() + this.outerRadius * 2.0D, () -> {
          GL11.glColor4f(1.0F, 1.0F, 1.0F, this.resourceOpacity);
          getUi().mask(getX(), getY(), getWidth(), getHeight(), ());
          Color.reset();
        });
  }
  
  @NonNull
  public BackgroundNode backgroundColor(@NonNull Color backgroundColor) {
    if (backgroundColor == null)
      throw new NullPointerException("backgroundColor is marked non-null but is null"); 
    this.backgroundColor = backgroundColor;
    return this;
  }
  
  @NonNull
  public BackgroundNode borderColor(@NonNull Color borderColor) {
    if (borderColor == null)
      throw new NullPointerException("borderColor is marked non-null but is null"); 
    this.borderColor = borderColor;
    return this;
  }
  
  @NonNull
  public BackgroundNode resource(@NonNull Resource resource) {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    this.resource = resource;
    return this;
  }
  
  @NonNull
  public BackgroundNode resourceOpacity(float resourceOpacity) {
    this.resourceOpacity = resourceOpacity;
    return this;
  }
  
  @NonNull
  public BackgroundNode radius(double radius) {
    return radius(radius, radius);
  }
  
  @NonNull
  public BackgroundNode radius(double innerRadius, double outerRadius) {
    this.innerRadius = innerRadius;
    this.outerRadius = outerRadius;
    return this;
  }
  
  @NonNull
  public BackgroundNode innerRadius(double innerRadius) {
    this.innerRadius = innerRadius;
    return this;
  }
  
  @NonNull
  public BackgroundNode outerRadius(double outerRadius) {
    this.outerRadius = outerRadius;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\background\BackgroundNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */