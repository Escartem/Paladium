package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.SandmanEvent;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class SandCastleCommand extends CommandBase {
  public static final String COMMAND_NAME = "sandcastle";
  
  public String func_71517_b() {
    return "sandcastle";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "sandcastle";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof net.minecraft.entity.player.EntityPlayer))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    UUID uniqueId = player.func_110124_au();
    SandmanEvent instance = SandmanEvent.INSTANCE;
    if (!instance.isWaiting(player, uniqueId))
      return; 
    instance.handle(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\commands\SandCastleCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */