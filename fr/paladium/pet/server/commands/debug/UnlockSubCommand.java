package fr.paladium.pet.server.commands.debug;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.event.capture.PetCaptureEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class UnlockSubCommand extends ASubCommand {
  public static final String NAME = "unlock";
  
  public static final String DESCRIPTION = "Unlock soi-même";
  
  public static final String PERMISSION = "palapet.command.unlock";
  
  private static final Gson GSON = (new GsonBuilder())
    .excludeFieldsWithoutExposeAnnotation()
    .setPrettyPrinting()
    .create();
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    EntityPlayerMP player = (EntityPlayerMP)sender;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null)
      return true; 
    if (pet.has()) {
      ChatUtils.sendColoredMessage(sender, new String[] { "§cVous avez déjà un pet !" });
      return true;
    } 
    String skin = PetCommonProxy.getInstance().findRandomDefaultPet();
    ChatUtils.sendColoredMessage(sender, new String[] { "§eVous avez débloqué le pet §6" + skin + "§e !" });
    pet.unlock(player, skin);
    PetCaptureEvent event = new PetCaptureEvent((EntityPlayer)player, pet, new DoubleLocation(player.field_70165_t, player.field_70163_u, player.field_70161_v));
    MinecraftForge.EVENT_BUS.post((Event)event);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\debug\UnlockSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */