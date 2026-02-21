package fr.paladium.palamod.modules.luckyblock.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class GuiTopleftable extends GuiContainer {
  public GuiTopleftable(Container container) {
    super(container);
  }
  
  public int getGuiLeft() {
    return this.field_147003_i;
  }
  
  public int getGuiTop() {
    return this.field_147009_r;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiTopleftable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */