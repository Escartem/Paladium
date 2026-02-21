package fr.paladium.palamod.modules.pillage.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.pillage.client.render.RenderBolt;
import io.netty.buffer.ByteBuf;
import javax.vecmath.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class PacketCreateEffect implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private byte id;
  
  private Object additionalData;
  
  private byte additionalDataTag;
  
  public PacketCreateEffect() {}
  
  public PacketCreateEffect(int x, int y, int z, byte id, Object additionalData) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.id = id;
    this.additionalData = additionalData;
  }
  
  public PacketCreateEffect(int x, int y, int z, byte id) {
    this(x, y, z, id, null);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeByte(this.id);
    if (this.additionalData instanceof Entity) {
      buf.writeByte(0);
      buf.writeInt(((Entity)this.additionalData).func_145782_y());
    } else if (this.additionalData instanceof Byte) {
      buf.writeByte(1);
      buf.writeByte(((Byte)this.additionalData).byteValue());
    } else if (this.additionalData instanceof Vector3d) {
      buf.writeByte(2);
      buf.writeDouble(((Vector3d)this.additionalData).x);
      buf.writeDouble(((Vector3d)this.additionalData).y);
      buf.writeDouble(((Vector3d)this.additionalData).z);
    } else {
      buf.writeByte(-1);
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.id = buf.readByte();
    this.additionalDataTag = buf.readByte();
    switch (this.additionalDataTag) {
      case 0:
        this.additionalData = Integer.valueOf(buf.readInt());
        break;
      case 1:
        this.additionalData = Byte.valueOf(buf.readByte());
        break;
      case 2:
        this.additionalData = new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        break;
    } 
  }
  
  public static class ClientHandler implements IMessageHandler<PacketCreateEffect, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketCreateEffect message, MessageContext ctx) {
      int width, height, i;
      TileEntityHomeFinder teHomeFinder;
      int x1, samples;
      double maxRadius;
      float speed;
      double phi, d1;
      WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
      byte level = (message.additionalDataTag == 1) ? ((Byte)message.additionalData).byteValue() : -1;
      switch (message.id) {
        case 0:
          width = 3;
          height = 5;
          for (i = -3; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
              for (int k = -3; k < 3; k++) {
                if (worldClient.func_147437_c(message.x + i, message.y + j, message.z + k))
                  worldClient.func_72869_a("smoke", (message.x + i), (message.y + j), (message.z + k), 0.0D, 0.0D, 0.0D); 
              } 
            } 
          } 
          break;
        case 1:
          teHomeFinder = (TileEntityHomeFinder)worldClient.func_147438_o(message.x, message.y, message.z);
          for (x1 = 0; x1 <= 4; x1++) {
            for (int z1 = 0; z1 <= 4; z1++)
              worldClient.func_72869_a("largesmoke", (x1 + teHomeFinder.field_145851_c), teHomeFinder.field_145848_d, (z1 + teHomeFinder.field_145849_e), 0.0D, -0.3D, 0.0D); 
          } 
          break;
        case 2:
          samples = 1000;
          maxRadius = (level - 1);
          speed = 0.3F;
          phi = Math.PI * (3.0D - Math.sqrt(5.0D));
          for (d1 = 0.0D; d1 < 1000.0D; d1++) {
            double y = maxRadius - d1 / (float)(1000.0D - maxRadius) * maxRadius * 2.0D;
            double radius = Math.sqrt(maxRadius - y * y);
            double theta = phi * d1;
            double cosTheta = Math.cos(theta), sinTheta = Math.sin(theta);
            double x = cosTheta * radius;
            double vx = cosTheta * 0.30000001192092896D;
            double z = sinTheta * radius;
            double vz = sinTheta * 0.30000001192092896D;
            worldClient.func_72869_a("smoke", x + message.x + 0.5D, y + message.y + 1.0D, z + message
                .z + 0.5D, vx, -0.1D, vz);
          } 
          break;
        case 3:
          Minecraft.func_71410_x().func_152344_a(() -> {
                for (int i = 0; i < 100; i++) {
                  Vector3d pos1 = new Vector3d(message.x, message.z, message.z);
                  pos1.scale(0.0625D);
                  Vector3d pos2 = (message.additionalDataTag == 2) ? (Vector3d)message.additionalData : new Vector3d();
                  pos2.scale(0.0625D);
                  RenderBolt.renderBoltBetween(pos1, pos2, 1.0D, 1.0D, 5, world.field_73012_v.nextLong());
                } 
              });
          break;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\packets\PacketCreateEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */