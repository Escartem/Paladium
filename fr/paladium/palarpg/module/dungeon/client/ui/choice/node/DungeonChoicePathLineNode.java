package fr.paladium.palarpg.module.dungeon.client.ui.choice.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import javax.vecmath.Vector2d;
import lombok.NonNull;
import org.lwjgl.opengl.Display;

public final class DungeonChoicePathLineNode extends Node {
  private final Node start;
  
  private final Node end;
  
  private Color color;
  
  private boolean dashed;
  
  public Node getStart() {
    return this.start;
  }
  
  public Node getEnd() {
    return this.end;
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public boolean isDashed() {
    return this.dashed;
  }
  
  private DungeonChoicePathLineNode(@NonNull Node start, @NonNull Node end) {
    super(0.0D, 0.0D, 0.0D, 0.0D);
    if (start == null)
      throw new NullPointerException("start is marked non-null but is null"); 
    if (end == null)
      throw new NullPointerException("end is marked non-null but is null"); 
    this.start = start;
    this.end = end;
  }
  
  @NonNull
  public static DungeonChoicePathLineNode create(@NonNull Node start, @NonNull Node end) {
    if (start == null)
      throw new NullPointerException("start is marked non-null but is null"); 
    if (end == null)
      throw new NullPointerException("end is marked non-null but is null"); 
    return new DungeonChoicePathLineNode(start, end);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.color != null)
      if (this.dashed) {
        DrawUtils.SHAPE.drawDashedLine(this.color, 40, Display.getWidth() / 1920.0F * 3.5F, new Vector2d[] { new Vector2d(this.start.ax(this.start.dw(2.0D)), this.start.ay(this.start.dh(2.0D))), new Vector2d(this.end.ax(this.end.dw(2.0D)), this.end.ay(this.end.dh(2.0D))) });
      } else {
        DrawUtils.SHAPE.drawLine(this.color, Display.getWidth() / 1920.0F * 3.5F, new Vector2d[] { new Vector2d(this.start.ax(this.start.dw(2.0D)), this.start.ay(this.start.dh(2.0D))), new Vector2d(this.end.ax(this.end.dw(2.0D)), this.end.ay(this.end.dh(2.0D))) });
      }  
  }
  
  @NonNull
  public DungeonChoicePathLineNode color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return this;
  }
  
  @NonNull
  public DungeonChoicePathLineNode dashed(boolean dashed) {
    this.dashed = dashed;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\choice\node\DungeonChoicePathLineNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */