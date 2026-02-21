package fr.paladium.palajobs.core.utils;

import fr.paladium.translate.common.texttotranslate.TTT;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class ChatUtils {
  private static int CENTER_PX = -1;
  
  public static final String SEPARATOR = "§8§m---------------------------------";
  
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
  
  public static void broadcastCenteredTTTMessage(String message, Object... objects) {
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
    for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
      for (Object playerObj : world.field_73010_i) {
        if (!(playerObj instanceof EntityPlayer))
          continue; 
        EntityPlayer player = (EntityPlayer)playerObj;
        ChatComponentText chatComponentText = new ChatComponentText(sb.toString() + TTT.format(player, message, objects));
        player.func_145747_a((IChatComponent)chatComponentText);
      } 
    } 
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
  
  public static void sendCenteredTTTMessage(EntityPlayer player, String message, Object... objects) {
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
    ChatComponentText chatComponentText1 = new ChatComponentText(sb.toString() + TTT.format(player, message, objects));
    player.func_145747_a((IChatComponent)chatComponentText1);
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */