package fr.paladium.paladiumui.kit.chart;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.RadarChartNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.vecmath.Vector2d;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RadarChartNode extends RadarChartNode<RadarChartNode.RadarChartData> {
  private Color color;
  
  private Color borderColor;
  
  private TweenAnimator animator;
  
  protected RadarChartNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.color = new Color(89, 34, 30);
    this.borderColor = ColorConstant.PRIMARY;
  }
  
  @NonNull
  public static RadarChartNode create(double x, double y, double width, double height) {
    return new RadarChartNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    int size = getDataList().size();
    if (this.animator == null)
      this.animator = TweenAnimator.create(0.0F).sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
    this.animator.update();
    Vector2d[] points = new Vector2d[size];
    int i;
    for (i = 0; i < size; i++)
      points[i] = getPoint(i, dh(2.0D)); 
    DrawUtils.SHAPE.drawPolygon(ColorConstant.LIGHT_BACKGROUND, points);
    points = new Vector2d[size];
    for (i = 0; i < size; i++)
      points[i] = getPoint(i, dh(2.0D) - getWidth() * 0.023000000044703484D); 
    DrawUtils.SHAPE.drawPolygon(ColorConstant.BLACK, points);
    points = new Vector2d[size];
    for (i = 0; i < size; i++)
      points[i] = getPoint(i, dh(2.0D) * 0.85D); 
    DrawUtils.SHAPE.drawPolygon(ColorConstant.LIGHT_BACKGROUND, points);
    points = new Vector2d[size];
    for (i = 0; i < size; i++)
      points[i] = getPoint(i, dh(2.0D) * 0.85D - getWidth() * 0.012000000104308128D); 
    DrawUtils.SHAPE.drawPolygon(ColorConstant.BLACK, points);
    for (i = 0; i < size; i++) {
      DrawUtils.SHAPE.drawLine(ColorConstant.LIGHT_BACKGROUND, (float)getWidth() * 0.0077F, new Vector2d[] { points[i], new Vector2d(getX() + dw(2.0D), getY() + dh(2.0D)) });
    } 
    points = new Vector2d[size];
    double offset = getDataList().stream().anyMatch(data -> (data.getValue().doubleValue() <= 0.0D)) ? 1.0D : 0.0D;
    for (int j = 0; j < size; j++) {
      Number value = ((RadarChartData)getDataList().get(j)).getValue();
      double p = (offset + value.doubleValue()) / (offset + getMax().doubleValue());
      points[j] = getPoint(j, dh(2.0D) * p * 0.65D * this.animator.getValue());
    } 
    double cx = getX() + dw(2.0D);
    double cy = getY() + dh(2.0D);
    for (int k = 0; k < size; k++) {
      DrawUtils.SHAPE.drawShape(4, this.color, new Vector2d[] { new Vector2d(cx, cy), points[k], points[(k + 1) % size] });
    } 
    GL11.glEnable(2848);
    GL11.glLineWidth((float)getWidth() * 0.0077F);
    DrawUtils.SHAPE.drawShape(2, this.borderColor, points);
    GL11.glLineWidth(1.0F);
    GL11.glDisable(2848);
    double iconSize = getWidth() * 0.087D;
    for (int m = 0; m < size; m++) {
      Vector2d point = getPoint(m, dh(2.0D) * 1.15D);
      RadarChartData data = getDataList().get(m);
      if (data.icon != null) {
        DrawUtils.RESOURCE.drawImage(point.x - iconSize / 2.0D, point.y - iconSize / 2.0D, iconSize, iconSize, data.icon);
        Vector2d absolutePoint = getAbsolutePoint(m, dh(2.0D) * 1.15D);
        boolean hovered = (isVisible() && isEnabled() && getUi().isOnTop() && (getOverflowArea() == null || getOverflowArea().isHovered(mouseX, mouseY)) && mouseX >= absolutePoint.x - iconSize / 2.0D && mouseX <= absolutePoint.x + iconSize / 2.0D && mouseY >= absolutePoint.y - iconSize / 2.0D && mouseY <= absolutePoint.y + iconSize / 2.0D);
        if (hovered)
          getUi().drawHover(Arrays.asList(new String[] { data.getLabel() + ": " + data.getValue() }, ), mouseX, mouseY); 
      } 
    } 
    List<String> hoverData = new ArrayList<>();
    for (RadarChartData data : getDataList())
      hoverData.add(data.getLabel() + ": " + data.getValue()); 
    hoverLines(() -> hoverData);
  }
  
  public final Vector2d getPoint(int index, double radius) {
    int size = getDataList().size();
    double angle = 6.283185307179586D / size;
    double cx = getX() + dw(2.0D);
    double cy = getY() + dh(2.0D);
    Vector2d[] points = new Vector2d[size];
    for (int i = 0; i < size; i++) {
      double theta = (i + size / 2) * angle;
      double x = cx + radius * Math.cos(theta);
      double y = cy + radius * Math.sin(theta);
      points[i] = new Vector2d(x, y);
    } 
    Vector2d[] sorted = new Vector2d[size];
    sorted[0] = points[0];
    for (int j = 1; j < size; j++)
      sorted[j] = points[size - j]; 
    return sorted[index];
  }
  
  public final Vector2d getAbsolutePoint(int index, double radius) {
    int size = getDataList().size();
    double angle = 6.283185307179586D / size;
    double cx = getAbsoluteX() + dw(2.0D);
    double cy = getAbsoluteY() + dh(2.0D);
    Vector2d[] points = new Vector2d[size];
    for (int i = 0; i < size; i++) {
      double theta = (i + size / 2) * angle;
      double x = cx + radius * Math.cos(theta);
      double y = cy + radius * Math.sin(theta);
      points[i] = new Vector2d(x, y);
    } 
    Vector2d[] sorted = new Vector2d[size];
    sorted[0] = points[0];
    for (int j = 1; j < size; j++)
      sorted[j] = points[size - j]; 
    return sorted[index];
  }
  
  @NonNull
  public final <T extends RadarChartNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  @NonNull
  public final <T extends RadarChartNode> T color(@NonNull Color color, @NonNull Color borderColor) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    if (borderColor == null)
      throw new NullPointerException("borderColor is marked non-null but is null"); 
    this.color = color;
    this.borderColor = borderColor;
    return (T)this;
  }
  
  @NonNull
  public final <T extends RadarChartNode> T borderColor(@NonNull Color borderColor) {
    if (borderColor == null)
      throw new NullPointerException("borderColor is marked non-null but is null"); 
    this.borderColor = borderColor;
    return (T)this;
  }
  
  public static class RadarChartData extends RadarChartNode.RadarChartData {
    private Resource icon;
    
    public Resource getIcon() {
      return this.icon;
    }
    
    protected RadarChartData(@NonNull String label, Resource icon) {
      this(label, icon, (Number)null);
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
    }
    
    protected RadarChartData(@NonNull String label, Resource icon, Number value) {
      super(label, value);
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
      this.icon = icon;
    }
    
    @NonNull
    public static RadarChartData create(@NonNull String label, ResourceLocation icon) {
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
      return new RadarChartData(label, (icon == null) ? null : Resource.of(icon));
    }
    
    @NonNull
    public static RadarChartData create(@NonNull String label, ResourceLocation icon, Number value) {
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
      return new RadarChartData(label, (icon == null) ? null : Resource.of(icon), value);
    }
    
    @NonNull
    public static RadarChartData create(@NonNull String label, Resource icon) {
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
      return new RadarChartData(label, icon);
    }
    
    @NonNull
    public static RadarChartData create(@NonNull String label, Resource icon, Number value) {
      if (label == null)
        throw new NullPointerException("label is marked non-null but is null"); 
      return new RadarChartData(label, icon, value);
    }
    
    public final <T extends RadarChartData> T icon(ResourceLocation icon) {
      this.icon = Resource.of(icon);
      return (T)this;
    }
    
    public final <T extends RadarChartData> T icon(Resource icon) {
      this.icon = icon;
      return (T)this;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\chart\RadarChartNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */