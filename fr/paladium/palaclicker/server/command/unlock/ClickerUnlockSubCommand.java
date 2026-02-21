package fr.paladium.palaclicker.server.command.unlock;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.EnumSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ClickerUnlockSubCommand extends ASubCommand {
  public ClickerUnlockSubCommand() {
    ABaseSubCommand playerSub = PlayerSubCommand.create("(player)").build(this);
    ABaseSubCommand typeSub = EnumSubCommand.create("(building | upgrade)", "object type to unlock", ClickerUnlockSubCommandType.class).build((ASubCommand)playerSub);
    StringSubCommand.create("(id)", "unlock building/upgrade to player").build((ASubCommand)typeSub, (sender, data) -> {
          if (!data.getTargetedPlayer().isPresent()) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          EntityPlayerMP player = data.getTargetedPlayer().get();
          ClickerUnlockSubCommandType type = ClickerUnlockSubCommandType.valueOf(data.getEnumValue());
          String id = data.getFreeArg();
          PlayerClickerData playerData = PlayerClickerData.get((Entity)player);
          if (playerData == null) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur n'est pas connecté."));
            return false;
          } 
          if (type == ClickerUnlockSubCommandType.BUILDING) {
            if (PalaClickerMod.getServer().getBuildingConfig().getBuilding(id).isPresent()) {
              playerData.addBuilding(id);
              sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aLe batiment " + id + " a été débloqué pour " + player.getDisplayName() + "."));
            } else {
              sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe batiment n'existe pas."));
              for (ClickerBuilding building : PalaClickerMod.getServer().getBuildingConfig().getBuildingList())
                sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §a" + building.getId() + " §7(" + building.getName() + ")")); 
              return false;
            } 
          } else if (PalaClickerMod.getServer().getUpgradeConfig().getUpgrade(id).isPresent()) {
            playerData.addUpgrade(id);
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §aL'upgrade " + id + " a été débloqué pour " + player.getDisplayName() + "."));
            for (ClickerBuilding building : PalaClickerMod.getServer().getBuildingConfig().getBuildingList())
              sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §a" + building.getId() + " §7(" + building.getName() + ")")); 
          } else {
            sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cL'upgrade n'existe pas."));
            return false;
          } 
          return true;
        });
  }
  
  private enum ClickerUnlockSubCommandType {
    BUILDING, UPGRADE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\comman\\unlock\ClickerUnlockSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */