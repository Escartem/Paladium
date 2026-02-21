package fr.paladium.palamod.modules.pillage;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ObsidianDebugCommand extends CommandBase {
  public String func_71517_b() {
    return "obsidiandebug";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "/obsidiandebug";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    PPillage.debugMode = !PPillage.debugMode;
    sender.func_145747_a((IChatComponent)new ChatComponentText("§eObsidian Debug: " + (PPillage.debugMode ? "§aON" : "§cOFF")));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\ObsidianDebugCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */