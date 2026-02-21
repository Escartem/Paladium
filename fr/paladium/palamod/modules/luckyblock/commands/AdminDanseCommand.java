package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AdminDanseCommand extends CommandBase {
  public String func_71517_b() {
    return "admindanse";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "admindanse <on|off>";
  }
  
  public List func_71514_a() {
    return Collections.emptyList();
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      if (arguments.length == 1)
        if (arguments[0].contentEquals("on")) {
          PlayerLuckyBlockProperties.get((EntityPlayer)player).setAdmindanseAllowed(true);
        } else if (arguments[0].contentEquals("off")) {
          PlayerLuckyBlockProperties.get((EntityPlayer)player).setAdmindanseAllowed(false);
        }  
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\AdminDanseCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */