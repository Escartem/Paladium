package fr.paladium.palaclicker.server.command;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.command.clear.ClickerClearSubCommand;
import fr.paladium.palaclicker.server.command.give.ClickerGiveSubCommand;
import fr.paladium.palaclicker.server.command.set.ClickerSetSubCommand;
import fr.paladium.palaclicker.server.command.unlock.ClickerUnlockSubCommand;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ClickerCommand extends ASubCommand {
  public ClickerCommand() {
    SubCommandBuilder.create("clear", "clear your clicker data")
      .permission("paladium.cmd.clicker.admin")
      .string()
      .executable()
      .build(this, (ASubCommand)new ClickerClearSubCommand());
    SubCommandBuilder.create("give")
      .permission("paladium.cmd.clicker.admin")
      .string()
      .build(this, (ASubCommand)new ClickerGiveSubCommand());
    SubCommandBuilder.create("unlock")
      .permission("paladium.cmd.clicker.admin")
      .string()
      .build(this, (ASubCommand)new ClickerUnlockSubCommand());
    SubCommandBuilder.create("set")
      .permission("paladium.cmd.clicker.admin")
      .string()
      .build(this, (ASubCommand)new ClickerSetSubCommand());
    StringSubCommand.create("open", "open the clicker")
      .build(this, (sender, data) -> {
          if (sender instanceof EntityPlayerMP) {
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
            return true;
          } 
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\command\ClickerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */