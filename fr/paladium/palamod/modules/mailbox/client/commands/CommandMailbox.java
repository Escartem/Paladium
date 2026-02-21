package fr.paladium.palamod.modules.mailbox.client.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandMailbox extends CommandBase {
  public String func_71517_b() {
    return "mailbox";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    EntityPlayer p = (EntityPlayer)sender;
    if (args.length == 0 && sender instanceof EntityPlayer) {
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_MAILB, true), (EntityPlayerMP)sender);
    } else if (args.length == 1 && "write".equalsIgnoreCase(args[0]) && 
      BukkitUtils.hasPermission(p.func_110124_au(), "palamod.mailbox.admin")) {
      p.openGui(PalaMod.instance, PGuiRegistry.GUI_MAILB_WRITE_ADMIN, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
    } 
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender p_71519_1_) {
    return p_71519_1_ instanceof EntityPlayer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\commands\CommandMailbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */