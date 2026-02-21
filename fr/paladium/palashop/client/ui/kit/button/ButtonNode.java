package fr.paladium.palashop.client.ui.kit.button;

import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.shader.impl.RoundedShader;
import fr.paladium.zephyrui.lib.ui.node.Node;
import javax.vecmath.Vector2d;
import lombok.NonNull;

public class ButtonNode extends Node {
  private final TweenAnimator loading;
  
  public TweenAnimator getLoading() {
    return this.loading;
  }
  
  private Color color = ColorConstant.PRIMARY;
  
  private Color hoveredColor = Color.WHITE;
  
  public Color getHoveredColor() {
    return this.hoveredColor;
  }
  
  private Color disabledColor = ColorConstant.GRAY;
  
  public Color getDisabledColor() {
    return this.disabledColor;
  }
  
  private float radius = 6.0F;
  
  public float getRadius() {
    return this.radius;
  }
  
  private boolean animated = true;
  
  public boolean isAnimated() {
    return this.animated;
  }
  
  protected ButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.loading = TweenAnimator.create(1.0F);
    enabled(node -> (!isLoading() && isMounted()));
    onMount(node -> this.loading.setValue(0.0F));
    hoverDuration(150L);
  }
  
  @NonNull
  public static ButtonNode create(double x, double y, double width, double height) {
    return new ButtonNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.loading.update();
    double x = getX();
    double y = getY();
    double width = getWidth();
    double height = getHeight();
    RoundedShader.use(this.radius, (float)(x + this.radius), (float)(y + this.radius), (float)(x + width - this.radius), (float)(y + height - this.radius), () -> {
          Color animatedColor = this.animated ? getColor() : getColor().to(this.hoveredColor, hoverValue(1.0F));
          DrawUtils.SHAPE.drawRect(x, y, width, height, animatedColor);
          if (this.animated) {
            double animationX = getX() - (1.0F - hoverValue(1.0F)) * getWidth() + (20.0F * hoverValue(1.0F));
            double animationY = getY();
            double animationWidth = getWidth();
            double animationHeight = getHeight();
            DrawUtils.SHAPE.drawPolygon(this.hoveredColor, new Vector2d[] { new Vector2d(-20.0D + animationX, animationY + animationHeight), new Vector2d(animationX + animationWidth, animationY + animationHeight), new Vector2d(animationX + animationWidth - 20.0D, animationY), new Vector2d(-40.0D + animationX, animationY) });
          } 
        });
  }
  
  public void drawSkeleton(double mouseX, double mouseY, float ticks) {
    draw(mouseX, mouseY, ticks);
  }
  
  @NonNull
  public <T extends ButtonNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T hoveredColor(@NonNull Color hoveredColor) {
    if (hoveredColor == null)
      throw new NullPointerException("hoveredColor is marked non-null but is null"); 
    this.hoveredColor = hoveredColor;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T disabledColor(@NonNull Color disabledColor) {
    if (disabledColor == null)
      throw new NullPointerException("disabledColor is marked non-null but is null"); 
    this.disabledColor = disabledColor;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T radius(float radius) {
    this.radius = radius;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T animated(boolean animated) {
    this.animated = animated;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T loading(boolean loading) {
    if (loading) {
      this.loading.sequence(200.0F, 1.0F, (TweenEquation)TweenEquations.QUART_IN).start();
    } else {
      this.loading.sequence(200.0F, 0.0F, (TweenEquation)TweenEquations.QUART_IN).start();
    } 
    return (T)this;
  }
  
  public boolean isLoading() {
    return (this.loading.getValue() != 0.0F);
  }
  
  @NonNull
  public Color getColor() {
    if (!isLoading() && !isEnabled())
      return this.disabledColor; 
    if (isLoading())
      return this.color.to(this.disabledColor, this.loading.getValue()); 
    return this.color;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\button\ButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */