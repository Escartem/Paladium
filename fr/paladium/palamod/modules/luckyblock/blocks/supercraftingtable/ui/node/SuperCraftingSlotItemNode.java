package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui.SuperCraftingTableResources;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;

public class SuperCraftingSlotItemNode extends AClickableNode {
  private ShapedRecipes recipe;
  
  private ResourceLocation texture = new ResourceLocation(SuperCraftingTableResources.SLOT_LONG
      .getTexture() + ".png");
  
  private ResourceLocation hoveredTexture = new ResourceLocation(SuperCraftingTableResources.SLOT_LONG
      .getTexture() + ".png");
  
  public SuperCraftingSlotItemNode(double x, double y, double width, double height, ShapedRecipes recipe) {
    super(x, y, width, height);
    this.recipe = recipe;
    ANode node = (new TextNodeLabel(width(18.0F), height(41.0F), recipe.func_77571_b().func_82833_r(), Fonts.MONTSERRAT_BOLD.getFont(), 40, new Color(255, 236, 220))).setTextOverflow(TextOverflow.ELLIPSIS).setTextAlign(TextAlign.LEFT).setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()).setZindex(100);
    node.setWidth(width(40.0F));
    addChild(node);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, this.texture, this.width, this.height);
    GuiUtils.renderScaledItemStackIntoGUI(this.recipe.func_77571_b(), this.x + width(3.1F), this.y + height(25), height(4));
  }
  
  public void onRelease(int mouseX, int mouseY, int mouseButton) {}
  
  public void onHover(int mouseX, int mouseY) {}
  
  public void onHoverOut(int mouseX, int mouseY) {}
  
  public void fixedUpdate() {}
  
  public void onKeyTyped(char arg0, int arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtabl\\ui\node\SuperCraftingSlotItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */