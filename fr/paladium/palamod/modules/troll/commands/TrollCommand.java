package fr.paladium.palamod.modules.troll.commands;

import fr.paladium.palamod.modules.troll.PTroll;
import fr.paladium.palamod.modules.troll.modules.ATrollModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class TrollCommand extends CommandBase {
  public String func_71517_b() {
    return "troll";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "troll";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (args.length >= 2 && sender instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)sender;
      String targetName = args[0];
      EntityPlayer target = player.field_70170_p.func_72924_a(targetName);
      if (target == null || !(target instanceof EntityPlayerMP)) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cLe joueur §7" + targetName + " §cn'est pas en ligne"));
        return;
      } 
      ATrollModule module = findModule(args[1]);
      if (module != null) {
        List<String> arguments = new ArrayList<>();
        for (int i = 2; i < args.length; i++)
          arguments.add(args[i]); 
        module.perform(player, (EntityPlayerMP)target, 
            Arrays.<String, Object>copyOf(arguments.toArray(), arguments.size(), String[].class));
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§eTroll§8] » §eAucun module trouvé"));
        sendUsage(sender);
      } 
    } else {
      sendUsage(sender);
    } 
  }
  
  public List func_71516_a(ICommandSender sender, String[] args) {
    List<String> list = new ArrayList<>();
    if (args.length == 1)
      return new ArrayList(); 
    if (args.length == 2)
      PTroll.getModules().forEach(module -> list.add(module.getName())); 
    return list;
  }
  
  private ATrollModule findModule(String search) {
    int lastDistance = -1;
    ATrollModule best = null;
    for (ATrollModule module : PTroll.getModules()) {
      int distance = computeLevenshteinDistance(module.getName(), search);
      if (best == null) {
        best = module;
        lastDistance = distance;
        continue;
      } 
      if (distance < lastDistance) {
        best = module;
        lastDistance = distance;
      } 
    } 
    return best;
  }
  
  private void sendUsage(ICommandSender sender) {
    sender.func_145747_a((IChatComponent)new ChatComponentText("§eVoici la liste des modules disponibles"));
    for (ATrollModule module : PTroll.getModules()) {
      ChatComponentText chatComponentText = new ChatComponentText("§8» §e" + module.getName() + " §7(" + module.getDescription() + ")");
      chatComponentText.func_150255_a((new ChatStyle()).func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§eUsage: §c/" + module
                .getUsage()))));
      sender.func_145747_a((IChatComponent)chatComponentText);
    } 
    sender.func_145747_a((IChatComponent)new ChatComponentText("§c/troll <player> <module> [<arguments>]"));
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  private int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
    int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];
    for (int k = 0; k <= lhs.length(); k++)
      distance[k][0] = k; 
    for (int j = 1; j <= rhs.length(); j++)
      distance[0][j] = j; 
    for (int i = 1; i <= lhs.length(); i++) {
      for (int m = 1; m <= rhs.length(); m++)
        distance[i][m] = minimum(distance[i - 1][m] + 1, distance[i][m - 1] + 1, distance[i - 1][m - 1] + (
            (lhs.charAt(i - 1) == rhs.charAt(m - 1)) ? 0 : 1)); 
    } 
    return distance[lhs.length()][rhs.length()];
  }
  
  private int minimum(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\commands\TrollCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */