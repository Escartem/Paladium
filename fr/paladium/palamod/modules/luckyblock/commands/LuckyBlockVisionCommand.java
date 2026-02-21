package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palamod.modules.luckyblock.LuckyBlockManager;
import fr.paladium.palamod.modules.luckyblock.hud.vision.VisionnedPlayers;
import java.util.Iterator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class LuckyBlockVisionCommand extends CommandBase {
  public String func_71517_b() {
    return "vision";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "vision accept";
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    if (arguments.length == 1 && arguments[0].equals("accept")) {
      if (!(sender instanceof EntityPlayer))
        return; 
      EntityPlayer p = (EntityPlayer)sender;
      Iterator<VisionnedPlayers> i = LuckyBlockManager.getInstance().getVisionHelmetData().iterator();
      boolean accepted = false;
      while (i.hasNext()) {
        VisionnedPlayers v = i.next();
        if (v.getVisionned().equalsIgnoreCase(p.func_110124_au().toString()) && !v.isLinked())
          accepted = true; 
      } 
      Iterator<VisionnedPlayers> i2 = LuckyBlockManager.getInstance().getVisionHelmetData().iterator();
      if (accepted)
        while (i2.hasNext()) {
          VisionnedPlayers v = i2.next();
          if (v.getVisionned().equalsIgnoreCase(p.func_110124_au().toString()) && !v.isLinked()) {
            v.setLinked(true);
            continue;
          } 
          if ((v.getVisionned().equalsIgnoreCase(p.func_110124_au().toString()) && v.isLinked()) || (v.getVisionned().equalsIgnoreCase(p.func_110124_au().toString()) && v.isLinked()))
            i2.remove(); 
        }  
    } else if (arguments.length == 1 && arguments[0].equals("stop")) {
      if (!(sender instanceof EntityPlayer))
        return; 
      EntityPlayer p = (EntityPlayer)sender;
      Iterator<VisionnedPlayers> i = LuckyBlockManager.getInstance().getVisionHelmetData().iterator();
      boolean accepted = false;
      while (i.hasNext()) {
        VisionnedPlayers v = i.next();
        if (v.getVisionned().equalsIgnoreCase(p.func_110124_au().toString()) || v.getSender().equalsIgnoreCase(p.func_110124_au().toString()))
          i.remove(); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\LuckyBlockVisionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */