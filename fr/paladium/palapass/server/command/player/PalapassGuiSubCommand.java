package fr.paladium.palapass.server.command.player;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.manager.PalapassManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalapassGuiSubCommand extends ASubCommand {
  public static final String NAME = "gui";
  
  public static final String PERMISSION = "palapass.player";
  
  public boolean performCurrentNode(ICommandSender sender, CommandData data) {
    if (!(sender instanceof net.minecraft.entity.player.EntityPlayer)) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
      return false;
    } 
    PalapassManager.openPalapass((EntityPlayerMP)sender);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\player\PalapassGuiSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */