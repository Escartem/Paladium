package fr.paladium.palamod.modules.palaboss.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Handler implements IMessageHandler<PacketSendBossNotification, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketSendBossNotification message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(" "));
    (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bUn boss va apparaitre dans 5 minutes sur §e" + PacketSendBossNotification.access$000(message) + " §ben §e" + PacketSendBossNotification.access$100(message).getX() + ", " + PacketSendBossNotification.access$100(message).getY() + ", " + PacketSendBossNotification.access$100(message).getZ()));
    (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(" "));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\network\PacketSendBossNotification$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */