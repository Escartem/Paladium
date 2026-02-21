package fr.paladium.palamod.modules.egghunt.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Vec3;

public class SCPacketShowEggPos implements IMessage {
  private int eggX;
  
  private int eggY;
  
  private int eggZ;
  
  public SCPacketShowEggPos() {}
  
  public SCPacketShowEggPos(int eggX, int eggY, int eggZ) {
    this.eggX = eggX;
    this.eggY = eggY;
    this.eggZ = eggZ;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.eggX = buf.readInt();
    this.eggY = buf.readInt();
    this.eggZ = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.eggX);
    buf.writeInt(this.eggY);
    buf.writeInt(this.eggZ);
  }
  
  public static class Handler implements IMessageHandler<SCPacketShowEggPos, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketShowEggPos message, MessageContext ctx) {
      double posX = message.eggX + 0.5D;
      double posY = message.eggY;
      double posZ = message.eggZ + 0.5D;
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\network\SCPacketShowEggPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */