package fr.paladium.announcement.server.command;

import fr.paladium.announcement.common.constant.AnnouncementTranslateEnum;
import fr.paladium.announcement.common.network.SCAnnouncementPacket;
import fr.paladium.announcement.common.pojo.parsed.PaladiumPost;
import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import java.util.ArrayList;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class PatchnoteCommand extends ASubCommand {
  public static final String NAME = "patchnote";
  
  public static final String DESCRIPTION = "Ouvrir la dernière annonce";
  
  private static PalaAnnouncementManager manager = PalaAnnouncementManager.getInstance();
  
  public static boolean openUI(EntityPlayerMP sender) {
    EntityPlayerMP player = sender;
    ArrayList<PaladiumPost> lastNewsList = new ArrayList<>();
    lastNewsList.add(manager.getCachedPosts().get(0));
    if (!manager.getCachedPosts().isEmpty()) {
      (new SCAnnouncementPacket(lastNewsList)).send(player);
    } else {
      AnnouncementTranslateEnum.COMMAND_OPEN_EMPTY.message((ICommandSender)player, new Object[0]);
    } 
    return true;
  }
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    return openUI((EntityPlayerMP)sender);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\command\PatchnoteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */