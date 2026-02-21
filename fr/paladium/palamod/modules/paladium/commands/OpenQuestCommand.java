package fr.paladium.palamod.modules.paladium.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.network.SCOpenQuestGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class OpenQuestCommand extends CommandBase {
  public String func_71517_b() {
    return "openquest";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/openquest";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP)
      PalaMod.getNetwork().sendTo((IMessage)new SCOpenQuestGui(), (EntityPlayerMP)sender); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\OpenQuestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */