package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;

public final class CommandEntry {
  public static final int PAGE_SIZE = 5;
  
  public static final String SPACER = "------------------------------------";
  
  private final Object instance;
  
  private final String command;
  
  private final String[] aliases;
  
  private final String description;
  
  private final String permission;
  
  private final SenderType[] sender;
  
  private final boolean help;
  
  private final List<SubCommandEntry> subCommandList;
  
  public CommandEntry(Object instance, String command, String[] aliases, String description, String permission, SenderType[] sender, boolean help, List<SubCommandEntry> subCommandList) {
    this.instance = instance;
    this.command = command;
    this.aliases = aliases;
    this.description = description;
    this.permission = permission;
    this.sender = sender;
    this.help = help;
    this.subCommandList = subCommandList;
  }
  
  public Object getInstance() {
    return this.instance;
  }
  
  public String getCommand() {
    return this.command;
  }
  
  public String[] getAliases() {
    return this.aliases;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public SenderType[] getSender() {
    return this.sender;
  }
  
  public boolean isHelp() {
    return this.help;
  }
  
  public List<SubCommandEntry> getSubCommandList() {
    return this.subCommandList;
  }
  
  public void help(@NonNull CommandContext context, int page) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    long count = this.subCommandList.stream().filter(subCommand -> subCommand.canExecute(context)).filter(SubCommandEntry::isHelp).count();
    if (count == 0L) {
      context.error("Impossible de trouver une commande correspondante.");
      return;
    } 
    int maxPage = (int)Math.ceil(((float)count / 5.0F));
    if (page < 0) {
      page = 0;
    } else if (page >= maxPage) {
      page = maxPage - 1;
    } 
    String header = "§8[§c/" + this.command + "§8][§7" + (page + 1) + "/" + maxPage + "§8]";
    String spacer = "§7§m" + StringUtils.repeat("------------------------------------".charAt(0), ("------------------------------------".length() - header.length() - 10) / 2);
    int length = header.length() - 10 + (spacer.length() - 4) * 2 - 1;
    ChatComponentText headerComponent = new ChatComponentText(spacer + header + spacer);
    if (this.description != null && !this.description.isEmpty()) {
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§7" + StringUtils.capitalize(this.description.endsWith(".") ? this.description : (this.description + ".")))));
      style.func_150238_a(EnumChatFormatting.GRAY);
      headerComponent.func_150255_a(style);
    } 
    context.breakLine();
    context.send((IChatComponent)headerComponent);
    context.breakLine();
    int index = 0;
    for (SubCommandEntry subCommand : this.subCommandList.stream().sorted(Comparator.comparing(SubCommandEntry::getPriority)).sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).sorted((o1, o2) -> Integer.compare((o1.getParameters()).length, (o2.getParameters()).length)).collect(Collectors.toList())) {
      if (!subCommand.canExecute(context) || !subCommand.isHelp())
        continue; 
      if (index < page * 5 || index >= (page + 1) * 5) {
        index++;
        continue;
      } 
      subCommand.help(context);
      index++;
    } 
    if (page != maxPage - 1) {
      context.breakLine();
      ChatComponentText nextComponent = new ChatComponentText(" §8[§c»§8] §7Afficher la page suivante");
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour afficher la page suivante")));
      style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this.command + " help " + (page + 2)));
      style.func_150238_a(EnumChatFormatting.GRAY);
      nextComponent.func_150255_a(style);
      context.send((IChatComponent)nextComponent);
    } 
    if (page != 0) {
      if (page == maxPage - 1)
        context.breakLine(); 
      ChatComponentText previousComponent = new ChatComponentText(" §8[§c«§8] §7Afficher la page précédente");
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour afficher la page précédente")));
      style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this.command + " help " + page));
      style.func_150238_a(EnumChatFormatting.GRAY);
      previousComponent.func_150255_a(style);
      context.send((IChatComponent)previousComponent);
    } 
    context.breakLine();
    context.send((IChatComponent)new ChatComponentText("§7§m" + StringUtils.repeat("------------------------------------".charAt(0), length)));
    context.breakLine();
  }
  
  public SubCommandEntry getRoot() {
    return this.subCommandList.stream().filter(SubCommandEntry::isRoot).findFirst().orElse(null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\CommandEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */