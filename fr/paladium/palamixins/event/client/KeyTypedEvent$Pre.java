package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.client.gui.GuiScreen;

@Cancelable
public class Pre extends KeyTypedEvent {
  public Pre(GuiScreen gui, char character, int keyCode) {
    super(gui, character, keyCode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\KeyTypedEvent$Pre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */