package fr.paladium.palapass.common.utils;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TranslateUtils {
  public static final char COLOR_CHAR = '&';
  
  public static void sendPrefixedMessage(ICommandSender sender, String... messages) {
    if (sender == null || messages == null)
      return; 
    if (messages.length == 0)
      return; 
    sender.func_145747_a((IChatComponent)new ChatComponentText(TTT.format(PalapassTranslateEnum.MESSAGE_PREFIX.getId(), new Object[0]) + ChatColor.translateAlternateColorCodes('&', messages[0])));
    for (int i = 1; i < messages.length; i++)
      sender.func_145747_a((IChatComponent)new ChatComponentText(ChatColor.translateAlternateColorCodes('&', messages[i]))); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\commo\\utils\TranslateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */