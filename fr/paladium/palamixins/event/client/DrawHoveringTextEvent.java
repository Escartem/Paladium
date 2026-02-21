package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;

@SideOnly(Side.CLIENT)
public class DrawHoveringTextEvent extends GuiScreenEvent {
  private final int mouseX;
  
  private final int mouseY;
  
  public int getMouseX() {
    return this.mouseX;
  }
  
  public int getMouseY() {
    return this.mouseY;
  }
  
  protected DrawHoveringTextEvent(GuiScreen gui, int mouseX, int mouseY) {
    super(gui);
    this.mouseX = mouseX;
    this.mouseY = mouseY;
  }
  
  @Cancelable
  public static class PreItem extends DrawHoveringTextEvent {
    private final ItemStack itemStack;
    
    public ItemStack getItemStack() {
      return this.itemStack;
    }
    
    public PreItem(ItemStack stack, GuiScreen gui, int mouseX, int mouseY) {
      super(gui, mouseX, mouseY);
      this.itemStack = stack;
    }
  }
  
  @Cancelable
  public static class PreTab extends DrawHoveringTextEvent {
    private final List<String> hoverList;
    
    public List<String> getHoverList() {
      return this.hoverList;
    }
    
    public PreTab(GuiScreen gui, List<String> hoverList, int mouseX, int mouseY) {
      super(gui, mouseX, mouseY);
      this.hoverList = hoverList;
    }
  }
  
  public static class Post extends DrawHoveringTextEvent {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\DrawHoveringTextEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */