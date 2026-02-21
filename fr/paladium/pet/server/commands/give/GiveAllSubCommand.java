package fr.paladium.pet.server.commands.give;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.pet.PetAdditionalData;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class GiveAllSubCommand extends ASubCommand {
  public static final String NAME = "give-all";
  
  public static final String DESCRIPTION = "Give tous les skins à un joueur";
  
  public static final String PERMISSION = "palapet.command.give";
  
  public GiveAllSubCommand() {
    ABaseSubCommand aBaseSubCommand = PlayerSubCommand.create("(player)", "Give tous les skins à un joueur").build(this, (sender, data) -> {
          Optional<EntityPlayerMP> result = data.getTargetedPlayer();
          if (!result.isPresent())
            return false; 
          EntityPlayerMP player = result.get();
          PetPlayer pet = PetPlayer.get((EntityPlayer)player);
          PetSkinShopProvider provider = PetSkinShopProvider.getInstance();
          for (PetAdditionalData skin : PetCommonProxy.getInstance().getPets()) {
            String skinName = provider.getTranslatedSkinName(skin.getName());
            if (pet.hasSkin((EntityPlayer)player, skin.getName())) {
              ChatUtils.sendColoredMessage(sender, new String[] { "§cLe joueur §c" + player.func_70005_c_() + " §epossède déjà le skin §6" + skinName });
              continue;
            } 
            PetSkinShopProvider.getInstance().addSkin((EntityPlayer)player, skin.getName());
            ChatUtils.sendColoredMessage(sender, new String[] { "§eVous avez bien donné le skin §c" + skinName + " §eà §a" + player.func_70005_c_() });
          } 
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\give\GiveAllSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */