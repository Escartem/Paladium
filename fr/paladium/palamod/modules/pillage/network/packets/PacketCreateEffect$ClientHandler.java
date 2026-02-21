package fr.paladium.palamod.modules.pillage.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.pillage.client.render.RenderBolt;
import javax.vecmath.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;

public class ClientHandler implements IMessageHandler<PacketCreateEffect, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketCreateEffect message, MessageContext ctx) {
    int width, height, i;
    TileEntityHomeFinder teHomeFinder;
    int x1, samples;
    double maxRadius;
    float speed;
    double phi, d1;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    byte level = (PacketCreateEffect.access$000(message) == 1) ? ((Byte)PacketCreateEffect.access$100(message)).byteValue() : -1;
    switch (PacketCreateEffect.access$200(message)) {
      case 0:
        width = 3;
        height = 5;
        for (i = -3; i < 3; i++) {
          for (int j = 0; j < 5; j++) {
            for (int k = -3; k < 3; k++) {
              if (worldClient.func_147437_c(PacketCreateEffect.access$300(message) + i, PacketCreateEffect.access$400(message) + j, PacketCreateEffect.access$500(message) + k))
                worldClient.func_72869_a("smoke", (PacketCreateEffect.access$300(message) + i), (PacketCreateEffect.access$400(message) + j), (PacketCreateEffect.access$500(message) + k), 0.0D, 0.0D, 0.0D); 
            } 
          } 
        } 
        break;
      case 1:
        teHomeFinder = (TileEntityHomeFinder)worldClient.func_147438_o(PacketCreateEffect.access$300(message), PacketCreateEffect.access$400(message), PacketCreateEffect.access$500(message));
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
          worldClient.func_72869_a("smoke", x + PacketCreateEffect.access$300(message) + 0.5D, y + PacketCreateEffect.access$400(message) + 1.0D, z + 
              PacketCreateEffect.access$500(message) + 0.5D, vx, -0.1D, vz);
        } 
        break;
      case 3:
        Minecraft.func_71410_x().func_152344_a(() -> {
              for (int i = 0; i < 100; i++) {
                Vector3d pos1 = new Vector3d(PacketCreateEffect.access$300(message), PacketCreateEffect.access$500(message), PacketCreateEffect.access$500(message));
                pos1.scale(0.0625D);
                Vector3d pos2 = (PacketCreateEffect.access$000(message) == 2) ? (Vector3d)PacketCreateEffect.access$100(message) : new Vector3d();
                pos2.scale(0.0625D);
                RenderBolt.renderBoltBetween(pos1, pos2, 1.0D, 1.0D, 5, world.field_73012_v.nextLong());
              } 
            });
        break;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\packets\PacketCreateEffect$ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */