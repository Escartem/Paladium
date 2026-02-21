package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.palamod.modules.luckyblock.gui.GuiTopleftable;

public class LockButton {
  private GuiTopleftable container;
  
  private int code;
  
  private int x;
  
  private int y;
  
  private int sx;
  
  private int sy;
  
  public GuiTopleftable getContainer() {
    return this.container;
  }
  
  public int getCode() {
    return this.code;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getSx() {
    return this.sx;
  }
  
  public int getSy() {
    return this.sy;
  }
  
  public LockButton(GuiTopleftable container, int code, int x, int y, int sx, int sy) {
    this.container = container;
    this.code = code;
    this.x = x;
    this.y = y;
    this.sx = sx;
    this.sy = sy;
  }
  
  public boolean isIn(int mouseX, int mouseY) {
    if (mouseX < this.x + this.container.getGuiLeft() || mouseY < this.y + this.container.getGuiTop())
      return false; 
    return (mouseX <= this.x + this.sx + this.container.getGuiLeft() && mouseY <= this.y + this.sy + this.container.getGuiTop());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LockButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */