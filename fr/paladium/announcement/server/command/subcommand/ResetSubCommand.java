package fr.paladium.announcement.server.command.subcommand;

import fr.paladium.announcement.common.constant.AnnouncementTranslateEnum;
import fr.paladium.announcement.common.data.AnnouncementPlayer;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

@SubCommandInfo(name = "reset", permission = "announcement.admin.reset", type = SubCommandType.STRING)
public class ResetSubCommand extends ASubCommand {
  @SubCommandInfo(name = "me", description = "Reset ses données", senderTypes = {SenderType.PLAYER}, type = SubCommandType.STRING)
  public boolean displaySelectionInfos(ICommandSender sender, CommandData data) {
    EntityPlayerMP player = (EntityPlayerMP)sender;
    AnnouncementPlayer eep = AnnouncementPlayer.get((EntityPlayer)player);
    if (eep != null) {
      eep.setLastAnnouncement(0L);
      eep.sync();
    } 
    AnnouncementTranslateEnum.COMMAND_RESET_SUCCESS.message((ICommandSender)player, new Object[0]);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\command\subcommand\ResetSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */