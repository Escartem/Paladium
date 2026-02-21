package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Handler implements IMessageHandler<PacketClientUsePerception, IMessage> {
  public IMessage onMessage(PacketClientUsePerception message, MessageContext ctx) {
    double x = (Minecraft.func_71410_x()).field_71439_g.field_70165_t;
    double y = (Minecraft.func_71410_x()).field_71439_g.field_70163_u;
    double z = (Minecraft.func_71410_x()).field_71439_g.field_70161_v;
    int offset = (message.tier == 1) ? 12 : ((message.tier == 2) ? 24 : 32);
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - offset, 0.0D, z - offset, x + offset, 256.0D, z + offset);
    List players = (Minecraft.func_71410_x()).field_71439_g.func_130014_f_().func_72872_a(EntityPlayer.class, scanAbove);
    players.remove((Minecraft.func_71410_x()).field_71439_g);
    (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§cIl y a §e" + players
          .size() + " §cjoueurs autour de vous."));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUsePerception$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */