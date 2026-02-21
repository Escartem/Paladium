package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.solar.node;

import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.util.ResourceLocation;

public class PlanetNode extends Node {
  private final String name;
  
  private final Resource texture;
  
  public String getName() {
    return this.name;
  }
  
  public Resource getTexture() {
    return this.texture;
  }
  
  private PlanetNode(String name, double x, double y, double width, double height, Resource texture) {
    super(x, y, width, height);
    this.texture = texture;
    this.name = name;
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double x = getX();
    double y = getY();
    double width = getWidth();
    double height = getHeight();
    if (isHovered()) {
      x -= 10.0D;
      y -= 10.0D;
      width += 20.0D;
      height += 20.0D;
    } 
    DrawUtils.RESOURCE.drawImage(x, y, width, height, this.texture);
  }
  
  public static PlanetNode create(String name, double x, double y, double width, double height) {
    return new PlanetNode(name, x, y, width, height, Resource.of(new ResourceLocation("palamod", "textures/ui/march/solar/icon/" + name + ".png")));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\clien\\ui\solar\node\PlanetNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */