package fr.paladium.palapass.server.command.admin;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
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

public class PalapassSetPointsSubCommand extends ASubCommand {
  public static final String NAME = "points";
  
  public static final String PERMISSION = "palapass.admin";
  
  public PalapassSetPointsSubCommand() {
    NumberSubCommand.create("(value)", "définir le nombre de points")
      .build(
        
        (ASubCommand)StringSubCommand.create("set")
        .build(
          
          (ASubCommand)PlayerSubCommand.create("(player)")
          .build(this)), 
        
        set());
  }
  
  private ISubCallback set() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent()) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur spécifié n'est pas connecté."));
          return false;
        } 
        EntityPlayerMP target = result.get();
        PalapassPlayer targetData = PalapassPlayer.get((EntityPlayer)target);
        targetData.setPoints(data.getInteger());
        targetData.sync();
        sender.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez bien défini le nombre de point palapass de " + target.func_70005_c_() + "."));
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\admin\PalapassSetPointsSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */