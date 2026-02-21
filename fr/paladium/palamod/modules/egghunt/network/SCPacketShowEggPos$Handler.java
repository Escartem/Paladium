package fr.paladium.palamod.modules.egghunt.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Vec3;

public class Handler implements IMessageHandler<SCPacketShowEggPos, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketShowEggPos message, MessageContext ctx) {
    double posX = SCPacketShowEggPos.access$000(message) + 0.5D;
    double posY = SCPacketShowEggPos.access$100(message);
    double posZ = SCPacketShowEggPos.access$200(message) + 0.5D;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    Vec3 dir = Vec3.func_72443_a(-(Minecraft.func_71410_x()).field_71439_g.field_70165_t + posX, -(Minecraft.func_71410_x()).field_71439_g.field_70163_u + 0.8D + posY, -(Minecraft.func_71410_x()).field_71439_g.field_70161_v + posZ);
    double dist = dir.func_72433_c();
    dir = dir.func_72432_b();
    float r = 1.0E-5F, g = 1.0E-5F, b = 1.0E-5F;
    if (dist > 10000.0D) {
      r = g = b = 1.0F;
    } else if (dist > 5000.0D) {
      r = 1.0F;
      g = 0.82F;
    } else if (dist > 1000.0D) {
      r = 1.0F;
      g = 0.5F;
    } else if (dist > 500.0D) {
      r = 1.0F;
    } else if (dist > 100.0D) {
      r = b = 1.0F;
    } 
    for (int i = 2; i < 12; i++)
      worldClient.func_72869_a("reddust", (Minecraft.func_71410_x()).field_71439_g.field_70165_t + dir.field_72450_a * i, 
          (Minecraft.func_71410_x()).field_71439_g.field_70163_u + 1.0D + dir.field_72448_b * i, 
          (Minecraft.func_71410_x()).field_71439_g.field_70161_v + dir.field_72449_c * i, r, g, b); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\network\SCPacketShowEggPos$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */