package fr.paladium.palamod.modules.luckyblock.commands.easter;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palamod.modules.luckyblock.data.world.EasterEggData;
import fr.paladium.palamod.modules.luckyblock.data.world.EasterEggDataManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EasterEggSubCommand extends ASubCommand {
  public static final String NAME = "easteregg";
  
  public EasterEggSubCommand() {
    ABaseSubCommand aBaseSubCommand1 = StringSubCommand.create("consume").build(this);
    aBaseSubCommand1.getBuilder().sender(new SenderType[] { SenderType.PLAYER });
    FreeSubCommand.create("(code)", "Rentrer un code d'oeuf de pâques").build((ASubCommand)aBaseSubCommand1, (sender, data) -> {
          String code = data.getLastArg();
          EasterEggData worldData = EasterEggDataManager.getInstance().getData();
          if (worldData == null)
            return false; 
          worldData.giveReward((EntityPlayerMP)sender, code);
          return true;
        });
    ABaseSubCommand aBaseSubCommand2 = StringSubCommand.create("setcode").build(this);
    aBaseSubCommand2.getBuilder().permission("paladium.luckyblock.easteregg.setcode");
    aBaseSubCommand2.getBuilder().sender(new SenderType[] { SenderType.PLAYER });
    FreeSubCommand.create("(code)", "Définir un code d'oeuf de pâques").build((ASubCommand)aBaseSubCommand2, (sender, data) -> {
          String code = data.getLastArg();
          EasterEggData worldData = EasterEggDataManager.getInstance().getData();
          if (worldData == null)
            return false; 
          worldData.setCode(code);
          worldData.setUnlockerUsername(null);
          EasterEggDataManager.getInstance().saveConfig();
          MonthlyUtils.sendMessage((EntityPlayer)sender, new String[] { "§aLe code d'oeuf de pâques a été défini à §e" + code + "§a." });
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\easter\EasterEggSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */