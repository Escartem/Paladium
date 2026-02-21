package fr.paladium.palamod.modules.end.server.utils;

import fr.paladium.palamod.client.utils.ChatColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatUtils {
  private static int CENTER_PX = -1;
  
  public static final String SEPARATOR = "§8§m---------------------------------";
  
  public static void broadcastCenteredMessage(String message) {
    ChatComponentText text = new ChatComponentText(message);
    if (CENTER_PX == -1)
      calculateCenter(); 
    if (message == null || "".equals(message)) {
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
      return;
    } 
    message = ChatColor.translateAlternateColorCodes('&', message);
    int messagePxSize = 0;
    boolean previousCode = false;
    boolean isBold = false;
    for (char c : message.toCharArray()) {
      if (c == '§') {
        previousCode = true;
      } else if (previousCode) {
        previousCode = false;
        if (c == 'l' || c == 'L') {
          isBold = true;
        } else {
          isBold = false;
        } 
      } else {
        DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
        messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
        messagePxSize++;
      } 
    } 
    int halvedMessageSize = messagePxSize / 2;
    int toCompensate = CENTER_PX - halvedMessageSize;
    int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
    int compensated = 0;
    StringBuilder sb = new StringBuilder();
    while (compensated < toCompensate) {
      sb.append(" ");
      compensated += spaceLength;
    } 
    text = new ChatComponentText(sb.toString() + message);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
  }
  
  public static void sendCenteredMessage(EntityPlayer player, String message) {
    ChatComponentText text = new ChatComponentText(message);
    if (CENTER_PX == -1)
      calculateCenter(); 
    if (message == null || "".equals(message))
      player.func_145747_a((IChatComponent)text); 
    message = ChatColor.translateAlternateColorCodes('&', message);
    int messagePxSize = 0;
    boolean previousCode = false;
    boolean isBold = false;
    for (char c : message.toCharArray()) {
      if (c == '§') {
        previousCode = true;
      } else if (previousCode) {
        previousCode = false;
        if (c == 'l' || c == 'L') {
          isBold = true;
        } else {
          isBold = false;
        } 
      } else {
        DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
        messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
        messagePxSize++;
      } 
    } 
    int halvedMessageSize = messagePxSize / 2;
    int toCompensate = CENTER_PX - halvedMessageSize;
    int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
    int compensated = 0;
    StringBuilder sb = new StringBuilder();
    while (compensated < toCompensate) {
      sb.append(" ");
      compensated += spaceLength;
    } 
    text = new ChatComponentText(sb.toString() + message);
    player.func_145747_a((IChatComponent)text);
  }
  
  private static void calculateCenter() {
    String message = "§8§m---------------------------------";
    message = ChatColor.translateAlternateColorCodes('&', message);
    int messagePxSize = 0;
    boolean previousCode = false;
    boolean isBold = false;
    for (char c : message.toCharArray()) {
      if (c == '§') {
        previousCode = true;
      } else if (previousCode) {
        previousCode = false;
        if (c == 'l' || c == 'L') {
          isBold = true;
        } else {
          isBold = false;
        } 
      } else {
        DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
        messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
        messagePxSize++;
      } 
    } 
    int halvedMessageSize = messagePxSize / 2;
    CENTER_PX = halvedMessageSize;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\serve\\utils\ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */