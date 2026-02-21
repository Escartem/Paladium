package fr.paladium.palaspawner.server.subcommand.palaspawner.sub;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaspawner.common.utils.DebugUtils;
import net.minecraft.command.ICommandSender;

@SubCommandInfo(name = "debug", permission = "palaspawner.command.spawner.debug", type = SubCommandType.STRING, senderTypes = {SenderType.PLAYER}, description = "Active le mode debug")
public class DebugSubCommand extends ASubCommand {
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    DebugUtils.DEBUG = !DebugUtils.DEBUG;
    ChatUtils.sendColoredMessage(sender, new String[] { "§aLe mode debug est maintenant " + (DebugUtils.DEBUG ? "activé" : "désactivé") + "§a." });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\server\subcommand\palaspawner\sub\DebugSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */