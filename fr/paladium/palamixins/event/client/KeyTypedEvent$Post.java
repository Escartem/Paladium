package fr.paladium.palamixins.event.client;

import net.minecraft.client.gui.GuiScreen;

public class Post extends KeyTypedEvent {
  public Post(GuiScreen gui, char character, int keyCode) {
    super(gui, character, keyCode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\KeyTypedEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */