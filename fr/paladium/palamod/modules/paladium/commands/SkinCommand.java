package fr.paladium.palamod.modules.paladium.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SkinCommand extends CommandBase {
  public String func_71517_b() {
    return "skin";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "skin";
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    try {
      EntityPlayerMP player = CommandBase.func_71521_c(sender);
      try {
        if (BukkitUtils.hasPermission(player.func_110124_au(), "palamod.cmd.skin")) {
          PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_PALADIUM_ARMOR, true), player);
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez pas la permission d'executer cette commande."));
        } 
      } catch (Exception e) {
        PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_PALADIUM_ARMOR, true), player);
      } 
    } catch (Exception e) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("Vous devez être un joueur."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\SkinCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */