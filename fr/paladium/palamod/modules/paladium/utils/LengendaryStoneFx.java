package fr.paladium.palamod.modules.paladium.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class LengendaryStoneFx {
  public static void execute(float red, float green, float blue) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    execute(((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u, ((EntityPlayer)entityClientPlayerMP).field_70161_v, red, green, blue);
  }
  
  public static void execute(double playerX, double playerY, double playerZ, float red, float green, float blue) {
    spawnHelixEffect(playerX, playerY, playerZ, red, green, blue);
    spawnRingEffect(playerX, playerY, playerZ, red, green, blue);
    spawnStarburstEffect(playerX, playerY, playerZ, red, green, blue);
    spawnOrbitEffect(playerX, playerY, playerZ, red, green, blue);
  }
  
  private static void spawnHelixEffect(double playerX, double playerY, double playerZ, float red, float green, float blue) {
    int particles = 200;
    double height = 3.0D;
    double radius = 1.2D;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    for (int i = 0; i < 200; i++) {
      double angle1 = i / 200.0D * Math.PI * 6.0D;
      double angle2 = angle1 + Math.PI;
      double y = i / 200.0D * 3.0D - 1.0D;
      double x1 = Math.cos(angle1) * 1.2D;
      double z1 = Math.sin(angle1) * 1.2D;
      worldClient.func_72869_a("reddust", playerX + x1, playerY + y, playerZ + z1, red, green, blue);
      double x2 = Math.cos(angle2) * 1.2D;
      double z2 = Math.sin(angle2) * 1.2D;
      worldClient.func_72869_a("reddust", playerX + x2, playerY + y, playerZ + z2, (red * 0.8F), (green * 0.8F), (blue * 0.8F));
    } 
  }
  
  private static void spawnRingEffect(double playerX, double playerY, double playerZ, float red, float green, float blue) {
    int rings = 5;
    int particlesPerRing = 40;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    for (int ring = 0; ring < 5; ring++) {
      double ringHeight = 0.2D + ring * 0.5D - 1.0D;
      double ringRadius = 0.8D + ring * 0.3D;
      for (int i = 0; i < 40; i++) {
        double angle = i / 40.0D * Math.PI * 2.0D;
        double x = Math.cos(angle) * ringRadius;
        double z = Math.sin(angle) * ringRadius;
        float colorMult = 1.0F - ring * 0.15F;
        worldClient.func_72869_a("reddust", playerX + x, playerY + ringHeight, playerZ + z, (red * colorMult), (green * colorMult), (blue * colorMult));
        if (i % 3 == 0)
          worldClient.func_72869_a("fireworksSpark", playerX + x, playerY + ringHeight, playerZ + z, 0.0D, 0.02D, 0.0D); 
      } 
    } 
  }
  
  private static void spawnStarburstEffect(double playerX, double playerY, double playerZ, float red, float green, float blue) {
    int rays = 16;
    int particlesPerRay = 20;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    for (int ray = 0; ray < 16; ray++) {
      double horizontalAngle = ray / 16.0D * Math.PI * 2.0D;
      for (int i = 0; i < 20; i++) {
        double distance = i / 20.0D * 2.5D;
        double verticalAngle = i / 20.0D * Math.PI - 1.5707963267948966D;
        double x = Math.cos(horizontalAngle) * Math.cos(verticalAngle) * distance;
        double y = Math.sin(verticalAngle) * distance - 1.0D;
        double z = Math.sin(horizontalAngle) * Math.cos(verticalAngle) * distance;
        float intensity = 1.0F - i / 20.0F * 0.6F;
        worldClient.func_72869_a("reddust", playerX + x, playerY + y, playerZ + z, (red * intensity), (green * intensity), (blue * intensity));
        if (i % 4 == 0)
          worldClient.func_72869_a("enchantmenttable", playerX + x, playerY + y, playerZ + z, 0.0D, 0.05D, 0.0D); 
      } 
    } 
  }
  
  private static void spawnOrbitEffect(double playerX, double playerY, double playerZ, float red, float green, float blue) {
    int orbitingParticles = 30;
    double orbitRadius = 1.5D;
    double orbitHeight = 1.5D;
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    for (int i = 0; i < 30; i++) {
      double angle = i / 30.0D * Math.PI * 2.0D;
      double x = Math.cos(angle) * 1.5D;
      double z = Math.sin(angle) * 1.5D;
      double y = Math.sin(angle * 3.0D) * 0.5D + 1.5D - 1.0D;
      worldClient.func_72869_a("reddust", playerX + x, playerY + y, playerZ + z, red, green, blue);
      worldClient.func_72869_a("portal", playerX + x, playerY + y, playerZ + z, 0.0D, 0.0D, 0.0D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\LengendaryStoneFx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */