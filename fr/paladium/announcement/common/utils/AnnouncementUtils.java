package fr.paladium.announcement.common.utils;

import fr.paladium.announcement.common.constant.AnnouncementTranslateEnum;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class AnnouncementUtils {
  public static final String LINE = "§8§m----------------------------------------";
  
  private static final char COLOR_CHAR = '&';
  
  public static void sendPrefixedMessage(ICommandSender sender, String... messages) {
    if (sender == null || messages == null || messages.length == 0)
      return; 
    String prefix = (sender instanceof EntityPlayer) ? TTT.format((EntityPlayer)sender, AnnouncementTranslateEnum.MESSAGE_PREFIX.getId(), new Object[0]) : TTT.format(AnnouncementTranslateEnum.MESSAGE_PREFIX.getId(), new Object[0]);
    sender.func_145747_a((IChatComponent)new ChatComponentText(prefix + ChatColor.translateAlternateColorCodes('&', messages[0])));
    for (int i = 1; i < messages.length; i++)
      sender.func_145747_a((IChatComponent)new ChatComponentText(ChatColor.translateAlternateColorCodes('&', messages[i]))); 
  }
  
  public static String extractVersion(String input) {
    String regex = "V\\d+\\.\\d+\\.\\d+\\.\\d+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if (matcher.find())
      return matcher.group(); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\commo\\utils\AnnouncementUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */