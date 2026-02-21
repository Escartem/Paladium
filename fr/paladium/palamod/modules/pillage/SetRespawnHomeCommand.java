package fr.paladium.palamod.modules.pillage;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class SetRespawnHomeCommand extends CommandBase {
  public String func_71517_b() {
    return "setrhome";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "/setrhome";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayer && 
      args.length == 0)
      ConfigManager.writeConfig("homeremover", "pos", new double[] { ((EntityPlayer)sender).field_70165_t, ((EntityPlayer)sender).field_70163_u, ((EntityPlayer)sender).field_70161_v, ((EntityPlayer)sender).field_70177_z, ((EntityPlayer)sender).field_70125_A }); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\SetRespawnHomeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */