package fr.paladium.palacommunityevent.client.gui.container.node;

import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.CommunityStep;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class CommunityEventBigStepNode extends TexturedNodeButton {
  private final CommunityEvent communityEvent;
  
  private final CommunityStep communityStep;
  
  private long ticker = System.currentTimeMillis();
  
  private int index = 0;
  
  public CommunityEventBigStepNode(double x, double y, double width, double height, String texture, String hoveredTexture, CommunityEvent communityEvent, CommunityStep communityStep) {
    super(x, y, width, height);
    setTexture(texture);
    setHoveredTexture(hoveredTexture);
    this.communityEvent = communityEvent;
    this.communityStep = communityStep;
  }
  
  public void draw(Minecraft mc, int arg1, int arg2) {
    super.draw(mc, arg1, arg2);
    boolean commonStep = this.communityStep instanceof fr.paladium.palacommunityevent.common.pojo.CommonCommunityStep;
    String str = GuiUtils.formatNumber(commonStep ? (long)(this.communityEvent.totalItems * this.communityStep.percentToReach / 100.0F) : (long)(this.communityEvent.totalItemsPerPlayer * this.communityStep.percentToReach / 100.0F));
    GuiUtils.drawCenteredStringWithCustomFont(mc, str, this.x + 
        
        width(50.0F), this.y - 
        height(30), Color.WHITE, Fonts.MONTSERRAT_BOLD
        
        .getFont(), 100);
    if (System.currentTimeMillis() - this.ticker >= 3000L) {
      this.index++;
      this.ticker = System.currentTimeMillis();
      if (this.index >= this.communityStep.rewards.size())
        this.index = 0; 
    } 
    GuiUtils.renderScaledItemStackIntoGUI(this.communityStep.rewards.get(this.index), this.x + width(50.0F) - (8.0F * width(4.0F)), this.y + height(50.0F) - (8.0F * width(4.0F)), width(4.0F), Color.white);
    getHovers().clear();
    addHover("§bPalier des " + this.communityStep.percentToReach + "%");
    addHover("");
    for (int i = 0; i < this.communityStep.rewards.size(); i++) {
      ItemStack is = this.communityStep.rewards.get(i);
      addHover(((this.index == i) ? "§e" : "§8") + "» §c" + is.func_82833_r() + " §8[x" + is.field_77994_a + "]");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\node\CommunityEventBigStepNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */