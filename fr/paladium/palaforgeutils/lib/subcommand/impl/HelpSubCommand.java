package fr.paladium.palaforgeutils.lib.subcommand.impl;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaforgeutils.lib.subcommand.utils.SubCommandUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class HelpSubCommand extends ASubCommand {
  public static final String NAME = "help";
  
  public static final String DESCRIPTION = "Affiche l'aide de la commande";
  
  private static final int ELEMENTS_PER_PAGE = 10;
  
  private static final String SUB_NAME = "<page>";
  
  private static final String SUB_DESCRIPTION = "Afficher l'aide";
  
  public HelpSubCommand() {
    NumberSubCommand.create("<page>", "Afficher l'aide")
      .build(this, (sender, data) -> sendCustomHelp(sender, data.getInteger()));
  }
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    return sendCustomHelp(sender, 0);
  }
  
  public boolean sendCustomHelp(ICommandSender sender, int page) {
    List<String> help = new ArrayList<>();
    List<String> commands = SubCommandUtils.buildHelp(sender, getParent());
    int pages = getPages(commands);
    if (page <= 0)
      page = pages; 
    if (page > pages)
      page = 1; 
    help.addAll(getCommands(commands, page));
    ChatUtils.sendColoredMessage(sender, new String[] { "§8§m------------§8[§e" + page + "§8]§m------------" });
    help.forEach(line -> ChatUtils.sendColoredMessage(sender, new String[] { line }));
    ChatComponentText chatComponentText1 = new ChatComponentText("§e«");
    chatComponentText1.func_150255_a((new ChatStyle())
        .func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + 
            getParent().getBuilder().getName() + " help " + (page - 1)))
        .func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bPage précédente"))));
    ChatComponentText chatComponentText2 = new ChatComponentText("§e»");
    chatComponentText2.func_150255_a((new ChatStyle())
        .func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + 
            
            getParent().getBuilder().getName() + " help " + (page + 1)))
        .func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bPage suivante"))));
    IChatComponent chatComponentText = (new ChatComponentText("§8§m-------§8[")).func_150257_a((IChatComponent)chatComponentText1).func_150258_a("§8]§m--------§8[").func_150257_a((IChatComponent)chatComponentText2).func_150258_a("§8]§m-------");
    if (sender instanceof EntityPlayerMP) {
      ((EntityPlayerMP)sender).func_146105_b(chatComponentText);
    } else {
      sender.func_145747_a(chatComponentText);
    } 
    return true;
  }
  
  private List<String> getCommands(List<String> commands, int page) {
    if (commands.size() <= 10)
      return commands; 
    int startIndex = (page - 1) * 10;
    int endIndex = Math.min(startIndex + 10, commands.size());
    return commands.subList(startIndex, endIndex);
  }
  
  private int getPages(List<String> commands) {
    return (int)Math.ceil(commands.size() / 10.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\impl\HelpSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */