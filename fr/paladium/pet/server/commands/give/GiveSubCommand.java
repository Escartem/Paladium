package fr.paladium.pet.server.commands.give;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
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

public class GiveSubCommand extends ASubCommand {
  public static final String NAME = "give";
  
  public static final String DESCRIPTION = "Give un skin à un joueur";
  
  public static final String PERMISSION = "palapet.command.give";
  
  public GiveSubCommand() {
    String[] skins = (String[])PetCommonProxy.getInstance().getPets().stream().map(PetAdditionalData::getName).toArray(x$0 -> new String[x$0]);
    ABaseSubCommand aBaseSubCommand1 = PlayerSubCommand.create("(player)").build(this);
    ABaseSubCommand aBaseSubCommand2 = FreeSubCommand.create("(skinId)", "Give un skin à un joueur", skins).build((ASubCommand)aBaseSubCommand1, (sender, data) -> {
          Optional<EntityPlayerMP> result = data.getTargetedPlayer();
          if (!result.isPresent())
            return false; 
          PetSkinShopProvider provider = PetSkinShopProvider.getInstance();
          PetAdditionalData skin = PetCommonProxy.getInstance().findPet(data.getFreeArg());
          if (skin == null)
            return false; 
          EntityPlayerMP player = result.get();
          PetPlayer pet = PetPlayer.get((EntityPlayer)player);
          String skinName = provider.getTranslatedSkinName(skin.getName());
          if (pet.hasSkin((EntityPlayer)player, skin.getName())) {
            ChatUtils.sendColoredMessage(sender, new String[] { "§cLe joueur §c" + player.func_70005_c_() + " §epossède déjà le skin §6" + skinName });
            return true;
          } 
          provider.addSkin((EntityPlayer)player, skin.getName());
          ChatUtils.sendColoredMessage(sender, new String[] { "§eVous avez bien donné le skin §c" + skinName + " §eà §a" + player.func_70005_c_() });
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\give\GiveSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */