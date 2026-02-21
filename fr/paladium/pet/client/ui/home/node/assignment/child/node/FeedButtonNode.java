package fr.paladium.pet.client.ui.home.node.assignment.child.node;

import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import net.minecraft.client.Minecraft;

public class FeedButtonNode extends MinecraftTextCallToActionNode {
  private final String title;
  
  public FeedButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height, 5.0D, PetTranslateEnum.GUI_NODE_FEED_TITLE.text());
    this.title = PetTranslateEnum.GUI_NODE_FEED_TITLE.text();
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontSize = -10;
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), fontSize);
    GuiUtils.drawCenteredStringWithCustomFont(mc, this.title, this.x + this.width / 2.0D, this.y + this.height / 2.0D - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES
        
        .getFont(), fontSize, true, Color.BLACK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\assignment\child\node\FeedButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */