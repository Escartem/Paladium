package fr.paladium.palamod.modules.paladium.cheat.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketNoClip, IMessage> {
  public IMessage onMessage(PacketNoClip message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.field_70145_X = message.noClip;
    if (message.noClip) {
      (Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75100_b = true;
      (Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75101_c = true;
      (Minecraft.func_71410_x()).field_71439_g.func_71016_p();
      (Minecraft.func_71410_x()).field_71474_y.field_74333_Y = 1000.0F;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\cheat\network\PacketNoClip$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */