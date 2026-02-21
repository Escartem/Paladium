package fr.paladium.palamod.modules.paladium.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.network.PacketCloseAskGUI;
import fr.paladium.palamod.modules.paladium.network.PacketOpenAskGUI;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class AskCommand extends CommandBase {
  public String func_71517_b() {
    return "ask";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (args.length < 2) {
      sender.func_145747_a((IChatComponent)new ChatComponentText(func_71518_a(sender)));
      return;
    } 
    String subCommand = args[0];
    if (!"open".equalsIgnoreCase(subCommand) && !"close".equalsIgnoreCase(subCommand)) {
      sender.func_145747_a((IChatComponent)new ChatComponentText(func_71518_a(sender)));
      return;
    } 
    String player = args[1];
    if (player.isEmpty()) {
      reply(sender, "§8[§6Paladium§8] §cLe nom du joueur est invalide.");
      return;
    } 
    EntityPlayerMP target = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(player);
    if (target == null) {
      reply(sender, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne.");
      return;
    } 
    if ("open".equalsIgnoreCase(subCommand)) {
      boolean closeable = true;
      if (args.length > 2)
        closeable = Boolean.parseBoolean(args[2]); 
      try {
        if (BukkitUtils.hasPermission(target.func_110124_au(), "palamod.cmd.ask.bypass")) {
          reply(sender, "§8[§6Paladium§8] §cCe joueur ne peut pas recevoir de demande.");
          return;
        } 
      } catch (Exception exception) {}
      try {
        if (sender instanceof EntityPlayerMP && !BukkitUtils.hasPermission((Entity)sender, "palamod.cmd.ask.closeable"))
          closeable = true; 
      } catch (Exception exception) {}
      PalaMod.getNetHandler().sendTo((IMessage)new PacketOpenAskGUI(closeable), target);
      reply(sender, "§8[§6Paladium§8] §eDemande envoyée à §6" + target.func_70005_c_() + "§e.");
    } else if ("close".equalsIgnoreCase(subCommand)) {
      PalaMod.getNetHandler().sendTo((IMessage)new PacketCloseAskGUI(), target);
      reply(sender, "§8[§6Paladium§8] §eDemande fermée pour §6" + target.func_70005_c_() + "§e.");
    } 
  }
  
  public String func_71518_a(ICommandSender sender) {
    reply(sender, "§8§m---------------");
    reply(sender, "§8[§6Paladium§8] §cLa syntaxe de la commande est incorrecte.");
    reply(sender, "");
    reply(sender, "§8[§6Paladium§8] §e/" + func_71517_b() + " open §8<player> §8[<closeable>]");
    reply(sender, "§8[§6Paladium§8] §e/" + func_71517_b() + " close §8<player>");
    return "§8§m---------------";
  }
  
  public void reply(ICommandSender sender, String message) {
    reply(sender, (IChatComponent)new ChatComponentText(message));
  }
  
  public void reply(ICommandSender sender, IChatComponent message) {
    sender.func_145747_a(message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\AskCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */