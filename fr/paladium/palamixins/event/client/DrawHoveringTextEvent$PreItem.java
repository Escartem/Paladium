package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

@Cancelable
public class PreItem extends DrawHoveringTextEvent {
  private final ItemStack itemStack;
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public PreItem(ItemStack stack, GuiScreen gui, int mouseX, int mouseY) {
    super(gui, mouseX, mouseY);
    this.itemStack = stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\DrawHoveringTextEvent$PreItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */