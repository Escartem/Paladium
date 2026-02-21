package fr.paladium.palamod.modules.luckyblock.gui.christmas;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;

public class ClickableNodeChristmasSantaNoescrocTrade extends AClickableNode {
  private UIChristmasSantaNoescrocTrade associatedUI;
  
  private int itemID;
  
  private int step;
  
  public ClickableNodeChristmasSantaNoescrocTrade(UIChristmasSantaNoescrocTrade associatedUI, int step, int itemID, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.itemID = itemID;
    this.associatedUI = associatedUI;
    this.step = step;
  }
  
  public boolean onClick(int mouseX, int mouseY, int mouseButton) {
    if (this.x < mouseX && mouseX < this.x + this.width && this.y < mouseY && mouseY < this.y + this.height)
      if (this.step == 0) {
        if (0 <= this.itemID && this.itemID < ContainerChristmasSantaNoescrocTrade.inventory.func_70302_i_()) {
          this.associatedUI.setSelectedItem(ContainerChristmasSantaNoescrocTrade.inventory.func_70301_a(this.itemID));
        } else {
          this.associatedUI.setSelectedItem(null);
        } 
      } else if (this.step == 1) {
        this.associatedUI.giveItem();
      }  
    return super.onClick(mouseX, mouseY, mouseButton);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\christmas\ClickableNodeChristmasSantaNoescrocTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */