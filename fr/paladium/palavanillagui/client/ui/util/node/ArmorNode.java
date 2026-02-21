package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ArmorNode extends Node {
  private static final ResourceLocation ARMOR_EMPTY = new ResourceLocation("palavanillagui", "textures/gui/hotbar/armor_empty.png");
  
  private static final ResourceLocation ARMOR_FULL = new ResourceLocation("palavanillagui", "textures/gui/hotbar/armor_full.png");
  
  private boolean half = false;
  
  private boolean full = false;
  
  protected ArmorNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ImageNode.create(0.0D, 0.0D)
      .resource(ARMOR_EMPTY)
      .size(getWidth(), getHeight())
      .attach(this);
    if (this.full) {
      ImageNode.create(0.0D, 0.0D)
        .resource(ARMOR_FULL)
        .size(getWidth(), getHeight())
        .attach(this);
    } else if (this.half) {
      ImageNode.create(0.0D, 0.0D)
        .resource(ARMOR_FULL)
        .size(getWidth(), getHeight())
        .effect((NodeEffect)MaskNodeEffect.create(dw(2.0D), getHeight()))
        .attach(this);
    } 
  }
  
  public static ArmorNode create(double x, double y, double width, double height) {
    return new ArmorNode(x, y, width, height);
  }
  
  public <T extends ArmorNode> T half(boolean half) {
    this.half = half;
    return (T)this;
  }
  
  public <T extends ArmorNode> T full(boolean full) {
    this.full = full;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\ArmorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */