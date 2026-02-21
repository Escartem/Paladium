package fr.paladium.palarpg.module.profile.client.ui.kit.icon;

import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class RPGProfileCostIconNode extends ImageNode {
  public static final Resource COST_ICON = Resource.of(new ResourceLocation("palarpg", "textures/profile/skilltree/cost.png"));
  
  private static final Resource BLUR_COST = Resource.of(new ResourceLocation("palarpg", "textures/profile/skilltree/cost_blur.png"));
  
  protected RPGProfileCostIconNode(double x, double y) {
    super(x, y);
    resource(COST_ICON);
  }
  
  protected RPGProfileCostIconNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    resource(COST_ICON);
  }
  
  public static RPGProfileCostIconNode create(double x, double y) {
    return new RPGProfileCostIconNode(x, y);
  }
  
  public static RPGProfileCostIconNode create(double x, double y, double width, double height) {
    return new RPGProfileCostIconNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    double width = (getWidth() == 0.0D) ? getResource().getWidth() : getWidth();
    double height = (getHeight() == 0.0D) ? getResource().getHeight() : getHeight();
    double widthRatio = width / getResource().getWidth();
    double heightRatio = height / getResource().getHeight();
    ImageNode.create(width / 2.0D, height / 2.0D)
      .resource(BLUR_COST)
      .size(BLUR_COST.getWidth() * widthRatio, BLUR_COST.getHeight() * heightRatio)
      .anchor(Align.CENTER, Align.CENTER)
      .attach((Node)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\icon\RPGProfileCostIconNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */