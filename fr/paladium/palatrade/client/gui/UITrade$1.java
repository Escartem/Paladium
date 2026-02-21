package fr.paladium.palatrade.client.gui;

import fr.paladium.palatrade.client.gui.node.CheckboxNode;
import java.util.function.Consumer;

class null extends CheckboxNode {
  null(double x, double y, double width, double height, Consumer<CheckboxNode> callback) {
    super(x, y, width, height, callback);
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    return (super.isHovered(mouseX, mouseY) && UITrade.access$000(UITrade.this));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\gui\UITrade$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */