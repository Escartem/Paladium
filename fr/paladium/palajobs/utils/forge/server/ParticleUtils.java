package fr.paladium.palajobs.utils.forge.server;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ParticleUtils {
  public static void spawnParticle(World world, String particle, double x, double y, double z, int count, double vel) {
    if (world instanceof WorldServer) {
      WorldServer worldServer = (WorldServer)world;
      worldServer.func_147487_a(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, vel);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\server\ParticleUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */