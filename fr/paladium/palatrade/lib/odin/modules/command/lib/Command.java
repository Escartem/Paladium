package fr.paladium.palatrade.lib.odin.modules.command.lib;

import fr.paladium.palatrade.lib.odin.modules.command.internal.builder.OdinCommandBuilder;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;

public abstract class Command extends CommandBase {
  private OdinCommandBuilder builder;
  
  public void setBuilder(OdinCommandBuilder builder) {
    this.builder = builder;
  }
  
  public String func_71517_b() {
    return this.builder.getName();
  }
  
  public String func_71518_a(ICommandSender sender) {
    if (!isAllowedSender(sender))
      return "§8[§6Paladium§8] §cVous n'êtes pas autorisé à utiliser cette commande."; 
    String usage = getCommandUsage(sender, new String[0]);
    return (usage == null) ? ("§8/§e" + func_71517_b()) : usage;
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!isAllowedSender(sender)) {
      reply(sender, "§8[§6Paladium§8] §cVous n'êtes pas autorisé à utiliser cette commande.");
      return;
    } 
    boolean hasPermission = true;
    if (this.builder.getPermission() != null && !this.builder.getPermission().isEmpty() && sender instanceof Entity)
      try {
        hasPermission = Bukkit.getPlayer(((Entity)sender).func_110124_au()).hasPermission(this.builder.getPermission());
      } catch (NoClassDefFoundError|Exception e) {
        hasPermission = true;
      }  
    if (!hasPermission) {
      reply(sender, "§8[§6Paladium§8] §cVous n'êtes pas autorisé à utiliser cette commande.");
      return;
    } 
    if (!perform(sender, args)) {
      String usage = getCommandUsage(sender, new String[0]);
      reply(sender, (usage == null) ? ("§8/§e" + func_71517_b()) : usage);
      return;
    } 
  }
  
  public abstract boolean perform(ICommandSender paramICommandSender, String[] paramArrayOfString);
  
  public abstract String getCommandUsage(ICommandSender paramICommandSender, String[] paramArrayOfString);
  
  public void reply(ICommandSender sender, String message) {
    reply(sender, (IChatComponent)new ChatComponentText(message));
  }
  
  public void reply(ICommandSender sender, IChatComponent message) {
    sender.func_145747_a(message);
  }
  
  private boolean isAllowedSender(ICommandSender sender) {
    for (SenderType type : this.builder.getSenderTypes()) {
      if (!type.isAllowed(sender))
        return false; 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\command\lib\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */