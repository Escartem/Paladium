package fr.paladium.announcement.server.command.subcommand;

import fr.paladium.announcement.common.constant.AnnouncementTranslateEnum;
import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

@SubCommandInfo(name = "refresh", permission = "announcement.admin.refresh", type = SubCommandType.STRING)
public class RefreshSubCommand extends ASubCommand {
  @SubCommandInfo(name = "ghost", description = "Refresh toutes les données depuis Ghost", senderTypes = {SenderType.PLAYER}, type = SubCommandType.STRING)
  public boolean displaySelectionInfos(ICommandSender sender, CommandData data) {
    EntityPlayerMP player = (EntityPlayerMP)sender;
    PalaAnnouncementManager.getInstance().importGhostData();
    AnnouncementTranslateEnum.COMMAND_REFRESH_GHOST_SUCCESS.message((ICommandSender)player, new Object[0]);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\command\subcommand\RefreshSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */