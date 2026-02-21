package fr.paladium.palamixins.internal.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.SlotCanTakeEvent;
import fr.paladium.palamixins.event.client.DrawHoveringTextEvent;
import fr.paladium.palamixins.event.client.KeyTypedEvent;

public class ClientListener {
  @SubscribeEvent
  public void onRenderTooltipItem(DrawHoveringTextEvent.PreItem event) {
    System.out.println("Render Tooltip Item: " + event.getItemStack() + " - mouseX " + event.getMouseX() + " - mouseY " + event.getMouseY());
  }
  
  @SubscribeEvent
  public void onRenderTooltipTab(DrawHoveringTextEvent.PreTab event) {
    System.out.println("Render Tooltip Tab: " + event.getHoverList() + " - mouseX " + event.getMouseX() + " - mouseY " + event.getMouseY());
  }
  
  @SubscribeEvent
  public void onRenderTooltipPost(DrawHoveringTextEvent.Post event) {
    System.out.println("Render Tooltip Post: " + event.getHoverList() + " - mouseX " + event.getMouseX() + " - mouseY " + event.getMouseY() + " - fontRenderer " + event.getFontRenderer());
  }
  
  @SubscribeEvent
  public void onKeyTypedEventPre(KeyTypedEvent.Pre event) {
    System.out.println("Key Typed Pre: code " + event.getKeyCode() + " - char " + event.getCharacter());
  }
  
  @SubscribeEvent
  public void onKeyTypedEventPost(KeyTypedEvent.Post event) {
    System.out.println("Key Typed Post: code " + event.getKeyCode() + " - char " + event.getCharacter());
  }
  
  @SubscribeEvent
  public void onCraftItem(SlotCanTakeEvent event) {
    System.out.println(event.getSlot() + " " + event.getPlayer().func_70005_c_() + " " + event.getItemStack());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\internal\client\ClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */