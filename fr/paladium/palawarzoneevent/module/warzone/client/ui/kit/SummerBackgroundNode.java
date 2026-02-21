package fr.paladium.palawarzoneevent.module.warzone.client.ui.kit;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import javax.vecmath.Vector4f;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SummerBackgroundNode extends Node {
  private static final Resource ORNEMENT = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/ornement.png")).nearest();
  
  private final Color backgroundColor = Color.gradient(Color.decode("#1F232A"), Color.decode("#131A26"), new Vector4f(0.0F, 1.0F, 0.0F, 0.0F));
  
  private double borderSize = 0.0D;
  
  protected SummerBackgroundNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static SummerBackgroundNode create(double x, double y, double width, double height) {
    return new SummerBackgroundNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.borderSize <= 0.0D) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor);
    } else {
      GL11.glEnable(2960);
      GL11.glClear(1024);
      GL11.glColorMask(false, false, false, false);
      GL11.glDepthMask(false);
      GL11.glStencilFunc(519, 1, 255);
      GL11.glStencilOp(7680, 7680, 7681);
      DrawUtils.SHAPE.drawRect(getX(), getY() - this.borderSize, w(), this.borderSize, Color.WHITE);
      DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY(), w() + this.borderSize * 2.0D, h(), Color.WHITE);
      DrawUtils.SHAPE.drawRect(getX(), getY() + h(), w(), this.borderSize, Color.WHITE);
      GL11.glColorMask(true, true, true, true);
      GL11.glDepthMask(true);
      GL11.glStencilFunc(514, 1, 255);
      GL11.glStencilOp(7680, 7680, 7680);
      DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY() - this.borderSize, getWidth() + this.borderSize * 2.0D, getHeight() + this.borderSize * 2.0D, this.backgroundColor);
      GL11.glDisable(2960);
    } 
    DrawUtils.RESOURCE.drawImage(getX() + 5.0D, getY() + 5.0D, w() - 10.0D, h() - 10.0D, ORNEMENT);
  }
  
  public SummerBackgroundNode borderSize(double borderSize) {
    this.borderSize = borderSize;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\clien\\ui\kit\SummerBackgroundNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */