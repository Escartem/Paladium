package fr.paladium.palamixins.event.client;

import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class Post extends DrawHoveringTextEvent {
  private final List<String> hoverList;
  
  private final FontRenderer fontRenderer;
  
  public List<String> getHoverList() {
    return this.hoverList;
  }
  
  public FontRenderer getFontRenderer() {
    return this.fontRenderer;
  }
  
  public Post(GuiScreen gui, int mouseX, int mouseY, List<String> hoverList, FontRenderer fontRenderer) {
    super(gui, mouseX, mouseY);
    this.hoverList = hoverList;
    this.fontRenderer = fontRenderer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\DrawHoveringTextEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */