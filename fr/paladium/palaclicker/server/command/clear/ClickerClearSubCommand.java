package fr.paladium.palaclicker.server.command.clear;

import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ClickerClearSubCommand extends ASubCommand {
  public ClickerClearSubCommand() {
    PlayerSubCommand.create("(player)", "clear player clicker data").build(this, (sender, data) -> {
          if (!data.getTargetedPlayer().isPresent()) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          PlayerClickerData playerData = PlayerClickerData.get(data.getTargetedPlayer().get());
          if (playerData == null) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          playerData.clear();
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aLe clicker du joueur " + ((EntityPlayerMP)data.getTargetedPlayer().get()).getDisplayName() + " a été réinitialisé."));
          return true;
        });
  }
  
  public boolean performCurrentNode(ICommandSender sender, CommandData data) {
    if (!(sender instanceof EntityPlayerMP)) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
      return false;
    } 
    PlayerClickerData playerData = PlayerClickerData.get((Entity)sender);
    if (playerData == null) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
      return false;
    } 
    playerData.clear();
    sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aVotre clicker a été réinitialisé."));
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\command\clear\ClickerClearSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */