package fr.paladium.palamod.modules.luckyblock.commands.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class LuckyCommand extends CommandBase {
  public String func_71517_b() {
    return "lucky";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "lucky";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP)
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_LUCKY_PASS, true), (EntityPlayerMP)sender); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\luckypass\LuckyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */