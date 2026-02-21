package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HolidayPayEvent;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class SummerIslandCommand extends CommandBase {
  public static final String COMMAND_NAME = "summerisland";
  
  public static final String WARNING_MESSAGE = "&aUtilisez la commande &6/summerisland &apour faire apparaître l'île des vacances !";
  
  public String func_71517_b() {
    return "summerisland";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "summerisland";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof net.minecraft.entity.player.EntityPlayer))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    UUID uniqueId = player.func_110124_au();
    HolidayPayEvent instance = HolidayPayEvent.INSTANCE;
    if (!instance.isWaiting(player, uniqueId))
      return; 
    instance.handle(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\commands\SummerIslandCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */