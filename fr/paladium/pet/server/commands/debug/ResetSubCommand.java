package fr.paladium.pet.server.commands.debug;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ResetSubCommand extends ASubCommand {
  public static final String NAME = "reset";
  
  public static final String DESCRIPTION = "Reset soi-même";
  
  public static final String PERMISSION = "palapet.command.reset";
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    EntityPlayerMP player = (EntityPlayerMP)sender;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null)
      return true; 
    pet.reset();
    ChatUtils.sendColoredMessage(sender, new String[] { "§eVous avez reset votre pet !" });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\debug\ResetSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */