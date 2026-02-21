package fr.paladium.palamod.modules.paladium.cheat.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.cheat.network.PacketNoClip;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class NoClipCommand extends CommandBase {
  public String func_71517_b() {
    return "noclip";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/noclip";
  }
  
  public void func_71515_b(ICommandSender sender, String[] label) {
    if (sender instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)sender;
      player.field_70145_X = !player.field_70145_X;
      ((EntityPlayerMP)player).field_71075_bZ.field_75100_b = true;
      ((EntityPlayerMP)player).field_71075_bZ.field_75101_c = true;
      player.func_71016_p();
      PalaMod.getNetwork().sendTo((IMessage)new PacketNoClip(player.field_70145_X), (EntityPlayerMP)player);
      player.func_145747_a((IChatComponent)new ChatComponentText("§eNoClip : " + (player.field_70145_X ? "§aactivé" : "§cdésactivé")));
      player.func_145747_a((IChatComponent)new ChatComponentText("  §7Attention, le noclip ne vous rend pas invisible, n'oubliez-pas votre vanish ^^"));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\cheat\commands\NoClipCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */