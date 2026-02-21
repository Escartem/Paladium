package fr.paladium.palaforgeutils.lib.chat;

import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatUtils {
  private static int CENTER_PX = -1;
  
  public static final String SEPARATOR = "§8§m---------------------------------";
  
  public static final char COLOR_CHAR = '&';
  
  public static final String PREFIX = "§7[§cPaladium§7] ";
  
  public static void sendPrefixedMessage(@NonNull ICommandSender sender, @NonNull String... messages) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (messages == null)
      throw new NullPointerException("messages is marked non-null but is null"); 
    if (messages.length == 0)
      return; 
    sender.func_145747_a((IChatComponent)new ChatComponentText("§7[§cPaladium§7] " + ChatColor.translateAlternateColorCodes('&', messages[0])));
    for (int i = 1; i < messages.length; i++)
      sender.func_145747_a((IChatComponent)new ChatComponentText(ChatColor.translateAlternateColorCodes('&', messages[i]))); 
  }
  
  public static void sendColoredMessage(@NonNull ICommandSender sender, @NonNull String... messages) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (messages == null)
      throw new NullPointerException("messages is marked non-null but is null"); 
    for (String message : messages)
      sender.func_145747_a((IChatComponent)new ChatComponentText(ChatColor.translateAlternateColorCodes('&', message))); 
  }
  
  public static void broadcastCenteredMessage(String message) {
    ChatComponentText text = new ChatComponentText(message);
    if (CENTER_PX == -1)
      calculateCenter(); 
    if (message == null || message.equals("")) {
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
      } else if (previousCode == true) {
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
    if (message == null || message.equals(""))
      player.func_145747_a((IChatComponent)text); 
    message = ChatColor.translateAlternateColorCodes('&', message);
    int messagePxSize = 0;
    boolean previousCode = false;
    boolean isBold = false;
    for (char c : message.toCharArray()) {
      if (c == '§') {
        previousCode = true;
      } else if (previousCode == true) {
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
      } else if (previousCode == true) {
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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\chat\ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */