package fr.paladium.paladiumui.kit.chart;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.ChartNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.vecmath.Vector2d;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class ChartNode extends ChartNode {
  private Function<Number, String> formatter = Object::toString;
  
  public Function<Number, String> getFormatter() {
    return this.formatter;
  }
  
  private Color backgroundColor = ColorConstant.BLACK;
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  private boolean smooth = true;
  
  private double marginLeft;
  
  private double marginRight;
  
  private double marginTop;
  
  private double marginBottom;
  
  private double rowMarginLeft;
  
  public boolean isSmooth() {
    return this.smooth;
  }
  
  public double getMarginLeft() {
    return this.marginLeft;
  }
  
  public double getMarginRight() {
    return this.marginRight;
  }
  
  public double getMarginTop() {
    return this.marginTop;
  }
  
  public double getMarginBottom() {
    return this.marginBottom;
  }
  
  public double getRowMarginLeft() {
    return this.rowMarginLeft;
  }
  
  private boolean marginLeftAuto = true;
  
  private TweenAnimator animator;
  
  private double hoverX;
  
  private double targetHoverX;
  
  public boolean isMarginLeftAuto() {
    return this.marginLeftAuto;
  }
  
  public TweenAnimator getAnimator() {
    return this.animator;
  }
  
  public double getHoverX() {
    return this.hoverX;
  }
  
  public double getTargetHoverX() {
    return this.targetHoverX;
  }
  
  protected ChartNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    margin(width * 0.054D);
  }
  
  @NonNull
  public static ChartNode create(double x, double y, double width, double height) {
    return new ChartNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor);
    if (!isLoaded()) {
      DrawUtils.TEXT.drawText(getX() + dw(2.0D), getY() + dh(2.0D) - TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 96.0F).dh(2.0D), Text.create("NO DATA", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 96.0F, Color.WHITE), Align.CENTER));
      return;
    } 
    if (this.animator == null)
      this.animator = TweenAnimator.create(0.0F).sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
    this.animator.update();
    double scaleFactor = getUi().getViewportWidth() / getUi().getWidth();
    int fontSize = (int)(getWidth() * 0.013D);
    float stroke = (float)(getWidth() * 0.0045D / scaleFactor);
    Number min = getMin();
    Number max = getMax();
    Number average = getAverage();
    if (this.marginLeftAuto) {
      this.marginLeft = this.rowMarginLeft + TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, fontSize, Color.WHITE).getWidth(this.formatter.apply(Integer.valueOf(max.intValue())));
    } else {
      this.marginLeft = this.rowMarginLeft;
    } 
    double canvasX = getX() + this.marginLeft;
    double canvasY = getY() + this.marginTop;
    double canvasWidth = getWidth() - this.marginLeft - this.marginRight;
    double canvasHeight = getHeight() - this.marginTop - this.marginBottom;
    double averageY = canvasY + canvasHeight - canvasHeight * (average.doubleValue() * this.animator.getValue() - min.doubleValue()) / (max.doubleValue() - min.doubleValue());
    int i;
    for (i = 0; i < Math.floor(getWidth() / fontSize); i++) {
      if (i % 2 != 0) {
        double d = getX() + (i * fontSize);
        DrawUtils.SHAPE.drawLine(ColorConstant.LIGHT_BACKGROUND, stroke * 0.7F, new Vector2d[] { new Vector2d(d, averageY), new Vector2d(d + fontSize, averageY) });
      } 
    } 
    i = 0;
    String hoveredLabel = null;
    double hoveredLabelX = 0.0D;
    double absoluteHoveredLabelX = 0.0D;
    if (isHovered())
      for (String label : getLabels()) {
        double absoluteLabelX = getAbsoluteX() + this.marginLeft + canvasWidth * i / (getLabels().size() - 1);
        double distX = Math.abs(mouseX - absoluteLabelX);
        double hoveredDistX = Math.abs(mouseX - absoluteHoveredLabelX);
        if (distX < hoveredDistX) {
          hoveredLabel = label;
          hoveredLabelX = canvasX + canvasWidth * i / (getLabels().size() - 1);
          absoluteHoveredLabelX = absoluteLabelX;
        } 
        i++;
      }  
    for (Map.Entry<String, ChartNode.ChartData> entry : (Iterable<Map.Entry<String, ChartNode.ChartData>>)getDataMap().entrySet()) {
      Vector2d last = null;
      double d1 = canvasX;
      double d2 = canvasWidth / (getLabels().size() - 1);
      for (String label : getLabels()) {
        ChartNode.ChartData data = entry.getValue();
        if (data == null || !data.has(label)) {
          d1 += d2;
          continue;
        } 
        Number value = data.get(label);
        Color color = (data instanceof ColoredChartData) ? ((ColoredChartData)data).getColor() : Color.WHITE;
        double oy = canvasY + canvasHeight - canvasHeight * (value.doubleValue() * this.animator.getValue() - min.doubleValue()) / (max.doubleValue() - min.doubleValue());
        if (last != null) {
          Vector2d start = new Vector2d(last);
          Vector2d startControl = new Vector2d(last.x + d2 / 3.0D * this.animator.getValue(), last.y);
          Vector2d end = new Vector2d(d1, oy);
          Vector2d endControl = new Vector2d(d1 - d2 / 3.0D * this.animator.getValue(), oy);
          if (this.smooth) {
            DrawUtils.SHAPE.drawCurvedLine(color, stroke, start, startControl, end, endControl);
          } else {
            DrawUtils.SHAPE.drawLine(color, stroke, new Vector2d[] { start, end });
          } 
        } 
        double radius = stroke * 2.0D * scaleFactor;
        boolean hovered = label.equals(hoveredLabel);
        if (hovered)
          DrawUtils.SHAPE.drawLine(ColorConstant.GRAY, stroke * 0.7F, new Vector2d[] { new Vector2d(d1, oy), new Vector2d(d1, canvasY + canvasHeight) }); 
        GL11.glTranslated(0.0D, 0.0D, 1.0D);
        DrawUtils.SHAPE.drawCircle(d1, oy, color, radius + radius * 0.3D);
        DrawUtils.SHAPE.drawCircle(d1, oy, hovered ? Color.WHITE : ColorConstant.GRAY, radius);
        GL11.glTranslated(0.0D, 0.0D, -1.0D);
        last = new Vector2d(d1, oy);
        d1 += d2;
      } 
    } 
    double ox = canvasX;
    double offset = canvasWidth / (getLabels().size() - 1);
    for (String label : getLabels()) {
      TextInfo labelInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, fontSize, Color.WHITE);
      DrawUtils.TEXT.drawText(ox, getY() + getHeight() - this.marginBottom + stroke * 8.0D * scaleFactor - labelInfo.dh(2.0D), Text.create(label, labelInfo, Align.CENTER));
      ox += offset;
    } 
    ox = canvasX - stroke * 8.0D * scaleFactor;
    TextInfo yAxisInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, fontSize, Color.WHITE);
    DrawUtils.TEXT.drawText(ox, canvasY + canvasHeight - yAxisInfo.dh(2.0D), Text.create(this.formatter.apply(Integer.valueOf(min.intValue())), yAxisInfo, Align.END));
    DrawUtils.TEXT.drawText(ox, canvasY - yAxisInfo.dh(2.0D), Text.create(this.formatter.apply(Integer.valueOf(max.intValue())), yAxisInfo, Align.END));
    if (hoveredLabel != null) {
      List<String> hoveredData = new LinkedList<>();
      for (Map.Entry<String, ChartNode.ChartData> entry : (Iterable<Map.Entry<String, ChartNode.ChartData>>)getDataMap().entrySet()) {
        if (!((ChartNode.ChartData)entry.getValue()).has(hoveredLabel))
          continue; 
        hoveredData.add((String)entry.getKey() + ": §l" + (String)this.formatter.apply(((ChartNode.ChartData)entry.getValue()).get(hoveredLabel)) + ((getYAxis().getSuffix() != null) ? getYAxis().getSuffix() : ""));
      } 
      double padding = 10.0D;
      double hoverWidth = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 11.0F, Color.WHITE).getWidth(hoveredLabel);
      double hoverHeight = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 11.0F, Color.WHITE).getHeight() + (hoveredData.isEmpty() ? false : 5);
      TextInfo hoverInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 12.0F, Color.WHITE);
      for (String text : hoveredData) {
        double textWidth = hoverInfo.getWidth(text);
        hoverWidth = Math.max(hoverWidth, textWidth);
        hoverHeight += hoverInfo.getHeight();
      } 
      hoverWidth += 20.0D;
      hoverHeight += 20.0D;
      this.targetHoverX = hoveredLabelX + 10.0D;
      double hoverY = mouseY - getAbsoluteY() - getY() - hoverHeight - 10.0D;
      if (this.targetHoverX + hoverWidth > getX() + getWidth() - 10.0D)
        this.targetHoverX = hoveredLabelX - hoverWidth - 10.0D; 
      if (hoverY < getY() + 10.0D)
        hoverY = getY() + 10.0D; 
      if (hoverY + hoverHeight > canvasY + canvasHeight - 10.0D)
        hoverY = canvasY + canvasHeight - hoverHeight - 10.0D; 
      this.hoverX = (this.hoverX == 0.0D) ? this.targetHoverX : getUi().lerpByFramerate(this.hoverX, this.targetHoverX, 0.3D, 0.3D, true);
      GL11.glTranslated(0.0D, 0.0D, 1.0D);
      DrawUtils.SHAPE.drawRoundedRect(this.hoverX - 1.0D, hoverY - 1.0D, hoverWidth + 2.0D, hoverHeight + 2.0D, ColorConstant.LIGHT_BACKGROUND, 6.0F);
      DrawUtils.SHAPE.drawRoundedRect(this.hoverX, hoverY, hoverWidth, hoverHeight, ColorConstant.BACKGROUND, 5.0F);
      double textY = hoverY + 10.0D;
      DrawUtils.TEXT.drawText(this.hoverX + 10.0D, textY, Text.create(hoveredLabel, TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 11.0F, Color.WHITE)));
      textY += TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 11.0F, Color.WHITE).getHeight() + 5.0D;
      for (String text : hoveredData) {
        DrawUtils.TEXT.drawText(this.hoverX + 10.0D, textY, Text.create(text, hoverInfo));
        textY += hoverInfo.getHeight();
      } 
      GL11.glTranslated(0.0D, 0.0D, -1.0D);
    } else {
      this.hoverX = 0.0D;
    } 
    getUi().setRenderPipelineLevel(getUi().getRenderPipelineLevel() + 1.0D);
  }
  
  @NonNull
  public final <T extends ChartNode> T animate() {
    this.animator = null;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T backgroundColor(@NonNull Color backgroundColor) {
    if (backgroundColor == null)
      throw new NullPointerException("backgroundColor is marked non-null but is null"); 
    this.backgroundColor = backgroundColor;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T smooth(boolean smooth) {
    this.smooth = smooth;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T margin(double margin) {
    this.rowMarginLeft = margin;
    this.marginRight = margin;
    this.marginTop = margin;
    this.marginBottom = margin;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T marginLeft(double marginLeft) {
    this.rowMarginLeft = marginLeft;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T marginLeft(boolean auto) {
    this.marginLeftAuto = auto;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T marginRight(double marginRight) {
    this.marginRight = marginRight;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T marginTop(double marginTop) {
    this.marginTop = marginTop;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T marginBottom(double marginBottom) {
    this.marginBottom = marginBottom;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ChartNode> T formatter(@NonNull Function<Number, String> formatter) {
    if (formatter == null)
      throw new NullPointerException("formatter is marked non-null but is null"); 
    this.formatter = formatter;
    return (T)this;
  }
  
  public static class ColoredChartData extends ChartNode.ChartData {
    private Color color;
    
    public ColoredChartData() {}
    
    public Color getColor() {
      return this.color;
    }
    
    protected ColoredChartData(Map<String, Number> dataMap) {
      super(dataMap);
    }
    
    @NonNull
    public static ColoredChartData create() {
      return new ColoredChartData();
    }
    
    @NonNull
    public static ColoredChartData create(Map<String, Number> dataMap) {
      return new ColoredChartData(dataMap);
    }
    
    @NonNull
    public final <T extends ColoredChartData> T color(@NonNull Color color) {
      if (color == null)
        throw new NullPointerException("color is marked non-null but is null"); 
      this.color = color;
      return (T)this;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\chart\ChartNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */