package fr.paladium.palajobs.client.ui.node.textfield;

import fr.paladium.lib.apollon.nodes.field.TextFieldNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MinecraftSearchTextFieldNode extends TextFieldNode {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palajobs", "textures/gui/buttons/field.png");
  
  private static final ResourceLocation BACKGROUND_FOCUSED = new ResourceLocation("palajobs", "textures/gui/buttons/field_focused.png");
  
  private static final ResourceLocation ICON = new ResourceLocation("palajobs", "textures/gui/icons/search.png");
  
  public MinecraftSearchTextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    setX(x + width(7.0F));
    setWidth(width(72.0F));
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    GuiUtils.drawImageTransparent(this.defaultX, this.defaultY, isFocused() ? BACKGROUND_FOCUSED : BACKGROUND, this.defaultWidth, this.defaultHeight, false);
    GuiUtils.drawImageTransparent(this.defaultX + this.defaultWidth * 0.82D, this.y + this.height / 2.0D - this.defaultWidth * 0.05D, ICON, this.defaultWidth * 0.1D, this.defaultWidth * 0.1D, false);
    if (getText().isEmpty())
      GuiUtils.drawStringWithCustomFont(mc, "recherche...".toUpperCase(), this.x, this.y + height(50.0F) - getFontHeight() / 2.0D, new Color(1.0F, 1.0F, 1.0F, 0.39F), getFont(), getFontSize()); 
    super.draw(mc, mouseX, mouseY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\textfield\MinecraftSearchTextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */