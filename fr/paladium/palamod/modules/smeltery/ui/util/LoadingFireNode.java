package fr.paladium.palamod.modules.smeltery.ui.util;

import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public class LoadingFireNode extends LoadingNode {
  private static final Resource FIRE_BACKGROUND = Resource.of(new ResourceLocation("palamod", "textures/gui/fire_loading.png"));
  
  private static final Resource FIRE_BACKGROUND_FILLER = Resource.of(new ResourceLocation("palamod", "textures/gui/fire_loading_filler.png"));
  
  protected LoadingFireNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), FIRE_BACKGROUND.linear());
    getUi().mask(getX(), getY() + getHeight() * (1.0D - this.progress), getWidth(), getHeight(), () -> DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), FIRE_BACKGROUND_FILLER.linear()));
  }
  
  public static LoadingFireNode create(double x, double y, double width, double height) {
    return new LoadingFireNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\u\\util\LoadingFireNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */