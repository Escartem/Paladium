package fr.paladium.palamixins.event.client.chat;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

@Cancelable
public class ClientChatEvent extends Event {
  private String message;
  
  public ClientChatEvent(String message) {
    this.message = message;
  }
  
  public String getMessage() {
    return this.message;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\chat\ClientChatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */