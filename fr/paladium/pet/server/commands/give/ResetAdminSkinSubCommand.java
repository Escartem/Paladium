package fr.paladium.pet.server.commands.give;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ResetAdminSkinSubCommand extends ASubCommand {
  public static final String NAME = "reset-admin";
  
  public static final String DESCRIPTION = "Reset les skins d'un admin";
  
  public static final String PERMISSION = "palapet.command.reset.admin";
  
  public ResetAdminSkinSubCommand() {
    ABaseSubCommand aBaseSubCommand = PlayerSubCommand.create("(player)", "Reset les skins d'un admin").build(this, (sender, data) -> {
          Optional<EntityPlayerMP> result = data.getTargetedPlayer();
          if (!result.isPresent())
            return false; 
          EntityPlayerMP player = result.get();
          if (!BukkitUtils.hasPermission((Entity)player, "palapet.command.reset.admin")) {
            ChatUtils.sendColoredMessage(sender, new String[] { "§cLe joueur " + player.func_70005_c_() + " n'est pas un admin" });
            return true;
          } 
          PetSkinShopProvider provider = PetSkinShopProvider.getInstance();
          provider.clearSkins((EntityPlayer)player);
          ChatUtils.sendColoredMessage(sender, new String[] { "§aLes skins de " + player.func_70005_c_() + " ont été réinitialisés" });
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\give\ResetAdminSkinSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */