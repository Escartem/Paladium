package fr.paladium.announcement.server.command.subcommand;

import fr.paladium.announcement.common.constant.AnnouncementTranslateEnum;
import fr.paladium.announcement.common.network.SCAnnouncementPacket;
import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

@SubCommandInfo(name = "open", permission = "announcement.admin.open", type = SubCommandType.STRING)
public class OpenSubCommand extends ASubCommand {
  private PalaAnnouncementManager manager = PalaAnnouncementManager.getInstance();
  
  @SubCommandInfo(name = "me", description = "Ouvrir l'interface", senderTypes = {SenderType.PLAYER}, type = SubCommandType.STRING)
  public boolean displaySelectionInfos(ICommandSender sender, CommandData data) {
    EntityPlayerMP player = (EntityPlayerMP)sender;
    if (!this.manager.getCachedPosts().isEmpty()) {
      (new SCAnnouncementPacket(this.manager.getCachedPosts())).send(player);
    } else {
      AnnouncementTranslateEnum.COMMAND_OPEN_EMPTY.message((ICommandSender)player, new Object[0]);
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\command\subcommand\OpenSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */