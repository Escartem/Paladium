package fr.paladium.announcement.common.constant;

import fr.paladium.announcement.common.utils.AnnouncementUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public enum AnnouncementTranslateEnum {
  MESSAGE_PREFIX("§7[§cAnnouncement§7] "),
  COMMAND_REFRESH_GHOST_SUCCESS("§aVous avez bien mis à jour les données depuis Ghost !"),
  COMMAND_RESET_SUCCESS("§aVous avez bien reset vos données !"),
  COMMAND_OPEN_EMPTY("§eIl n'y a aucun patchnote à afficher.");
  
  private final String id;
  
  public String getId() {
    return this.id;
  }
  
  AnnouncementTranslateEnum(String id) {
    this.id = id;
  }
  
  public void message(ICommandSender sender, Object... args) {
    String message = (sender instanceof EntityPlayer) ? TTT.format((EntityPlayer)sender, this.id, args) : TTT.format(this.id, args);
    AnnouncementUtils.sendPrefixedMessage(sender, new String[] { message });
  }
  
  public void broadcast(List<EntityPlayerMP> players, Object... args) {
    players.forEach(player -> message((ICommandSender)player, args));
  }
  
  public String text(Object... args) {
    return text(null, args);
  }
  
  public String text(ICommandSender sender, Object... args) {
    return (sender instanceof EntityPlayer) ? TTT.format((EntityPlayer)sender, this.id, args) : TTT.format(this.id, args);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\constant\AnnouncementTranslateEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */