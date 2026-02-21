package fr.paladium.palamixins.event.client;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

@Cancelable
public class RenderRecordOverlayEvent extends Event {
  private final String textDisplayed;
  
  public String getTextDisplayed() {
    return this.textDisplayed;
  }
  
  public RenderRecordOverlayEvent(String textDisplayed) {
    this.textDisplayed = textDisplayed;
  }
  
  public static class Pre extends RenderRecordOverlayEvent {
    public Pre(String textDisplayed) {
      super(textDisplayed);
    }
  }
  
  public static class Post extends RenderRecordOverlayEvent {
    public Post(String textDisplayed) {
      super(textDisplayed);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\client\RenderRecordOverlayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */