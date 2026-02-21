package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class WaterBreathingContainer extends Node {
  private static final Resource BUBBLE_FULL = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/bubble.png"));
  
  private static final Resource BUBBLE_EXPLODE = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/bubble_explode.png"));
  
  protected WaterBreathingContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (!(Minecraft.func_71410_x()).field_71439_g.func_70055_a(Material.field_151586_h))
      return; 
    int air = (Minecraft.func_71410_x()).field_71439_g.func_70086_ai();
    int full = MathHelper.func_76143_f((air - 2) * 10.0D / 300.0D);
    int partial = MathHelper.func_76143_f(air * 10.0D / 300.0D) - full;
    for (int i = 0; i < full + partial; i++) {
      double x = getX() + 178.0D - i * 25.0D + i * 5.0D;
      DrawUtils.RESOURCE.drawImage(x, getY() - 10.0D, 25.0D, 25.0D, (i < full) ? BUBBLE_FULL : BUBBLE_EXPLODE);
    } 
  }
  
  public static WaterBreathingContainer create(double x, double y, double width, double height) {
    return new WaterBreathingContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\WaterBreathingContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */