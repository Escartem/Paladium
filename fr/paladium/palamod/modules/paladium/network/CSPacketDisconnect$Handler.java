package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Handler implements IMessageHandler<CSPacketDisconnect, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketDisconnect message, MessageContext ctx) {
    EntityPlayerMP sender = (ctx.getServerHandler()).field_147369_b;
    if (CSPacketDisconnect.access$000(message)) {
      ChatComponentText msg = new ChatComponentText("§8[§cPaladium§8] » §6Votre opposant §e" + sender.func_70005_c_() + " §6à crash" + ((CSPacketDisconnect.access$100(message) == null) ? " §8(§c50%§8)" : ""));
      for (Player player : CombatTag.getInstance().getManager().getCombatList(Bukkit.getPlayer(sender.func_110124_au()))) {
        EntityPlayer p = sender.field_70170_p.func_152378_a(player.getUniqueId());
        if (p == null)
          continue; 
        p.func_145747_a((IChatComponent)msg);
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\CSPacketDisconnect$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */