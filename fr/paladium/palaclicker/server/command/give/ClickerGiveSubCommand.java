package fr.paladium.palaclicker.server.command.give;

import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ClickerGiveSubCommand extends ASubCommand {
  public ClickerGiveSubCommand() {
    ABaseSubCommand sub = PlayerSubCommand.create("(player)").build(this);
    NumberSubCommand.create("(amount)", "give coins to player", Integer.valueOf(0), Integer.valueOf(2147483647)).build((ASubCommand)sub, (sender, data) -> {
          if (!data.getTargetedPlayer().isPresent()) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          EntityPlayerMP player = data.getTargetedPlayer().get();
          int amount = data.getInteger();
          if (amount <= 0) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe montant doit être supérieur à 0."));
            return false;
          } 
          PlayerClickerData playerData = PlayerClickerData.get((Entity)player);
          if (playerData == null) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          playerData.addPoints(amount);
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aLe joueur " + player.getDisplayName() + " a maintenant " + String.format("%.0f", new Object[] { Double.valueOf(playerData.getPoints()) }) + " points."));
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\command\give\ClickerGiveSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */