package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class FoodNode extends Node {
  private static final Resource FOOD_EMPTY = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/food_empty.png")).nearest();
  
  private static final Resource FOOD_FULL = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/food_full.png")).nearest();
  
  private static final Resource FOOD_HUNGER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/food_hunger.png")).nearest();
  
  private boolean half = false;
  
  private boolean full = false;
  
  private boolean hunger = false;
  
  protected FoodNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static FoodNode create(double x, double y, double width, double height) {
    return new FoodNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ImageNode.create(0.0D, 0.0D)
      .resource(FOOD_EMPTY)
      .size(getWidth(), getHeight())
      .attach(this);
    if (this.full) {
      ImageNode.create(0.0D, 0.0D)
        .resource(this.hunger ? FOOD_HUNGER : FOOD_FULL)
        .size(getWidth(), getHeight())
        .attach(this);
    } else if (this.half) {
      ImageNode.create(dw(2.0D), 0.0D)
        .resource((this.hunger ? FOOD_HUNGER : FOOD_FULL).copy().textureCoords(dw(2.0D), 0.0D, dw(2.0D), getHeight()))
        .size(getWidth(), getHeight())
        .attach(this);
    } 
  }
  
  public <T extends FoodNode> T half(boolean half) {
    this.half = half;
    return (T)this;
  }
  
  public <T extends FoodNode> T full(boolean full) {
    this.full = full;
    return (T)this;
  }
  
  public <T extends FoodNode> T hunger(boolean hunger) {
    this.hunger = hunger;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\FoodNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */