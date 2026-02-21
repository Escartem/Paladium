package fr.paladium.palaforgeutils.lib.command;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class Command extends CommandBase {
  private CommandBuilder builder;
  
  public void setBuilder(CommandBuilder builder) {
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
      hasPermission = BukkitUtils.hasPermission((Entity)sender, this.builder.getPermission()); 
    if (!hasPermission) {
      reply(sender, "§8[§6Paladium§8] §cVous n'êtes pas autorisé à utiliser cette commande.");
      return;
    } 
    if (!perform(sender, args)) {
      String usage = getCommandUsage(sender, new String[0]);
      reply(sender, (usage == null) ? ("§8/§e" + func_71517_b()) : usage);
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
    if (this.builder.getSenderTypes().isEmpty())
      return true; 
    for (SenderType type : this.builder.getSenderTypes()) {
      if (type.isAllowed(sender))
        return true; 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */