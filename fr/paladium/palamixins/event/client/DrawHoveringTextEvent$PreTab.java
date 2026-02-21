package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

@Cancelable
public class PreTab extends DrawHoveringTextEvent {
  private final List<String> hoverList;
  
  public List<String> getHoverList() {
    return this.hoverList;
  }
  
  public PreTab(GuiScreen gui, List<String> hoverList, int mouseX, int mouseY) {
    super(gui, mouseX, mouseY);
    this.hoverList = hoverList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\DrawHoveringTextEvent$PreTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */