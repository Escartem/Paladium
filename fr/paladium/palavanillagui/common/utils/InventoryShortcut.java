package fr.paladium.palavanillagui.common.utils;

import fr.paladium.palavanillagui.client.ClientProxy;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;

public class InventoryShortcut {
  private String imageLink;
  
  private String translationKey;
  
  private String command;
  
  public InventoryShortcut() {}
  
  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }
  
  public void setTranslationKey(String translationKey) {
    this.translationKey = translationKey;
  }
  
  public void setCommand(String command) {
    this.command = command;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof InventoryShortcut))
      return false; 
    InventoryShortcut other = (InventoryShortcut)o;
    if (!other.canEqual(this))
      return false; 
    Object this$imageLink = getImageLink(), other$imageLink = other.getImageLink();
    if ((this$imageLink == null) ? (other$imageLink != null) : !this$imageLink.equals(other$imageLink))
      return false; 
    Object this$translationKey = getTranslationKey(), other$translationKey = other.getTranslationKey();
    if ((this$translationKey == null) ? (other$translationKey != null) : !this$translationKey.equals(other$translationKey))
      return false; 
    Object this$command = getCommand(), other$command = other.getCommand();
    return !((this$command == null) ? (other$command != null) : !this$command.equals(other$command));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof InventoryShortcut;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $imageLink = getImageLink();
    result = result * 59 + (($imageLink == null) ? 43 : $imageLink.hashCode());
    Object $translationKey = getTranslationKey();
    result = result * 59 + (($translationKey == null) ? 43 : $translationKey.hashCode());
    Object $command = getCommand();
    return result * 59 + (($command == null) ? 43 : $command.hashCode());
  }
  
  public String toString() {
    return "InventoryShortcut(imageLink=" + getImageLink() + ", translationKey=" + getTranslationKey() + ", command=" + getCommand() + ")";
  }
  
  public String getImageLink() {
    return this.imageLink;
  }
  
  public String getTranslationKey() {
    return this.translationKey;
  }
  
  public String getCommand() {
    return this.command;
  }
  
  public InventoryShortcut(String imageLink, String translationKey, String command) {
    this.imageLink = imageLink;
    this.translationKey = translationKey;
    this.command = command;
  }
  
  public void execute() {
    Minecraft.func_71410_x().func_147108_a(null);
    (Minecraft.func_71410_x()).field_71439_g.func_71165_d(this.command);
    ClientProxy.configInventoryShortcut.addLastUsed(this);
  }
  
  public String getTranslatedName() {
    return "§c§l" + TTT.format(this.translationKey, new Object[0]) + "§r";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\commo\\utils\InventoryShortcut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */