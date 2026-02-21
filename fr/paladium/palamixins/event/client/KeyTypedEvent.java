package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;

@SideOnly(Side.CLIENT)
public class KeyTypedEvent extends GuiScreenEvent {
  private final char character;
  
  private final int keyCode;
  
  public char getCharacter() {
    return this.character;
  }
  
  public int getKeyCode() {
    return this.keyCode;
  }
  
  public KeyTypedEvent(GuiScreen gui, char character, int keyCode) {
    super(gui);
    this.character = character;
    this.keyCode = keyCode;
  }
  
  @Cancelable
  public static class Pre extends KeyTypedEvent {
    public Pre(GuiScreen gui, char character, int keyCode) {
      super(gui, character, keyCode);
    }
  }
  
  public static class Post extends KeyTypedEvent {
    public Post(GuiScreen gui, char character, int keyCode) {
      super(gui, character, keyCode);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\KeyTypedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */