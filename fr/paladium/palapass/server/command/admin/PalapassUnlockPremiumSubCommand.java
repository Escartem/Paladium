package fr.paladium.palapass.server.command.admin;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalapassUnlockPremiumSubCommand extends ASubCommand {
  public static final String NAME = "unlock";
  
  public static final String PERMISSION = "palapass.admin";
  
  public PalapassUnlockPremiumSubCommand() {
    ABaseSubCommand aBaseSubCommand = PlayerSubCommand.create("(player)").build(this);
    StringSubCommand.create("step", "Débloqué le prochain palier palapass")
      .build((ASubCommand)aBaseSubCommand, step());
    NumberSubCommand.create("(count)", "débloquer le premium")
      .build(
        
        (ASubCommand)StringSubCommand.create("premium")
        .build((ASubCommand)aBaseSubCommand), 
        premium());
  }
  
  private ISubCallback premium() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent()) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur spécifié n'est pas connecté."));
          return false;
        } 
        EntityPlayerMP target = result.get();
        PalapassPlayer targetData = PalapassPlayer.get((EntityPlayer)target);
        targetData.addPremiumMonth(data.getInteger());
        targetData.sync();
        (new Notification(Notification.NotificationType.SUCCESS, "Vous venez de débloquer le pass premium", "palapass")).send(target);
        return true;
      };
  }
  
  private ISubCallback step() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent()) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur spécifié n'est pas connecté."));
          return false;
        } 
        EntityPlayerMP target = result.get();
        PalapassPlayer targetData = PalapassPlayer.get((EntityPlayer)target);
        targetData.sync();
        (new Notification(Notification.NotificationType.SUCCESS, "Vous venez de débloquer un nouveau palier", "palapass")).send(target);
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\admin\PalapassUnlockPremiumSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */