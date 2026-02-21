package fr.paladium.palamod.modules.miner.ui.util;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterRecipe;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import org.lwjgl.opengl.GL11;

public class FakeItemSlotNode extends Node {
  private AutoCrafterRecipe recipe;
  
  public AutoCrafterRecipe getRecipe() {
    return this.recipe;
  }
  
  private boolean selected = false;
  
  public boolean isSelected() {
    return this.selected;
  }
  
  protected FakeItemSlotNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.recipe == null)
      return; 
    if (isSelected()) {
      GL11.glColor3f(1.0F, 0.0F, 0.0F);
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.SLOT);
    } else {
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.SLOT);
    } 
    double iconSize = dw(1.5D);
    double x = getX() + dw(2.0D) - iconSize / 2.0D;
    double y = getY() + dh(2.0D) - iconSize / 2.0D;
    DrawUtils.ITEM.drawItem(x, y, iconSize, this.recipe.getResult());
  }
  
  public static FakeItemSlotNode create(double x, double y, double width, double height) {
    return new FakeItemSlotNode(x, y, width, height);
  }
  
  public <T extends FakeItemSlotNode> T bindRecipe(AutoCrafterRecipe recipe) {
    this.recipe = recipe;
    return (T)this;
  }
  
  public <T extends FakeItemSlotNode> T selected(boolean selected) {
    this.selected = selected;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\u\\util\FakeItemSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */