package fr.paladium.palamod.modules.luckyblock.utils;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EventUtils {
  public static final String CANT_INTERACT_MESSAGE = "&cVous ne pouvez pas faire cela ici !";
  
  public static final String _ab = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[7];
        this.t = 974309371;
        buf[0] = (byte)(this.t >>> 9);
        this.t = 928080088;
        buf[1] = (byte)(this.t >>> 1);
        this.t = -2004153909;
        buf[2] = (byte)(this.t >>> 11);
        this.t = 1086595533;
        buf[3] = (byte)(this.t >>> 2);
        this.t = 407227431;
        buf[4] = (byte)(this.t >>> 10);
        this.t = -607987356;
        buf[5] = (byte)(this.t >>> 6);
        this.t = -1321700375;
        buf[6] = (byte)(this.t >>> 8);
        return new String(buf);
      }
    }).toString();
  
  public static final String _q = "​​";
  
  private static Random random;
  
  public static void spawnStructure(World world, int x, int y, int z, int width, int height, int depth, Block block, EntityPlayerMP player) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (canInteract((EntityPlayer)player, ox, oy, oz))
            world.func_147449_b(ox, oy, oz, block); 
        } 
      } 
    } 
  }
  
  public static boolean canUseItem(EntityPlayerMP player, boolean message) {
    int blockX = (int)Math.floor(player.field_70165_t);
    int blockY = (int)Math.floor(player.field_70163_u);
    int blockZ = (int)Math.floor(player.field_70161_v);
    return canUseItem(player, blockX, blockY, blockZ, message);
  }
  
  public static boolean canUseItem(EntityPlayerMP player, int x, int y, int z, boolean message) {
    boolean ret = canInteract((EntityPlayer)player, x, y, z);
    if (!ret && message) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne pouvez pas faire cela ici !" });
      return false;
    } 
    return true;
  }
  
  public static void spawnStructure(World world, int x, int y, int z, int width, int height, int depth, Block block, EntityPlayerMP player, List<Location> locations) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (canInteract((EntityPlayer)player, ox, oy, oz)) {
            world.func_147449_b(ox, oy, oz, block);
            locations.add(new Location(world, ox, oy, oz));
          } 
        } 
      } 
    } 
  }
  
  public static boolean spawnStructureOnAir(World world, int x, int y, int z, int width, int height, int depth, Block block, EntityPlayerMP player) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (!canInteract((EntityPlayer)player, ox, oy, oz))
            return false; 
          if (!isAir(world, ox, oy, oz))
            return false; 
        } 
      } 
    } 
    spawnStructure(world, x, y, z, width, height, depth, block, player);
    return true;
  }
  
  public static boolean spawnStructureOnAir(World world, int x, int y, int z, int width, int height, int depth, Block block, EntityPlayerMP player, List<Location> locations) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (!canInteract((EntityPlayer)player, ox, oy, oz))
            return false; 
          if (!isAir(world, ox, oy, oz))
            return false; 
        } 
      } 
    } 
    spawnStructure(world, x, y, z, width, height, depth, block, player, locations);
    return true;
  }
  
  public static void spawnStructureWithParticle(World world, int x, int y, int z, int width, int height, int depth, Block block, String particle, EntityPlayerMP player) {
    for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
      for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
        for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
          if (canInteract((EntityPlayer)player, ox, oy, oz)) {
            world.func_147449_b(ox, oy, oz, block);
            spawnParticle(world, particle, ox, oy, oz, 50, 0.1D);
          } 
        } 
      } 
    } 
  }
  
  public static void spawnAnimatedStructure(final World world, final int x, final int y, final int z, final int width, final int height, final int depth, final Block block, final String particle, final int delay, final EntityPlayerMP player) {
    (new Thread(new Runnable() {
          public void run() {
            for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
              for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
                for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
                  if (EventUtils.canInteract((EntityPlayer)player, ox, oy, oz)) {
                    world.func_147449_b(ox, oy, oz, block);
                    if (block == BlocksRegister.BLOCK_PALADIUM)
                      PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 9.0D); 
                    EventUtils.spawnParticle(world, particle, ox, oy, oz, 50, 0.1D);
                    try {
                      Thread.sleep(delay);
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    } 
                  } 
                } 
              } 
            } 
          }
        })).start();
  }
  
  public static void spawnProtectedAnimatedStructure(final World world, final int x, final int y, final int z, final int width, final int height, final int depth, final Block block, final String particle, final int delay, final Entity entity, final EntityPlayerMP player) {
    (new Thread(new Runnable() {
          public void run() {
            for (int ox = x - width / 2; ox < x + Math.ceil((width / 2.0F)); ox++) {
              for (int oy = y - height / 2; oy < y + Math.ceil((height / 2.0F)); oy++) {
                for (int oz = z - depth / 2; oz < z + Math.ceil((depth / 2.0F)); oz++) {
                  if ((int)entity.field_70165_t == ox && (int)entity.field_70163_u == oy && (int)entity.field_70161_v == oz)
                    entity.func_70107_b(ox, (world.func_72976_f(ox, oz) + 2), oz); 
                  if (EventUtils.canInteract((EntityPlayer)player, ox, oy, oz)) {
                    world.func_147449_b(ox, oy, oz, block);
                    EventUtils.spawnParticle(world, particle, ox, oy, oz, 50, 0.1D);
                    try {
                      Thread.sleep(delay);
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    } 
                  } 
                } 
              } 
            } 
          }
        })).start();
  }
  
  public static void spawnParticle(World world, String particle, double x, double y, double z, int count, double vel) {
    if (world instanceof WorldServer) {
      WorldServer worldServer = (WorldServer)world;
      worldServer.func_147487_a(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, vel);
    } 
  }
  
  public static void playSound(EntityPlayerMP player, String soundName, double x, double y, double z, float pich, float volume) {
    S29PacketSoundEffect packet = new S29PacketSoundEffect(soundName, x, y, z, pich, volume);
    player.field_71135_a.func_147359_a((Packet)packet);
  }
  
  public static int random(int min, int max) {
    if (random == null)
      random = new Random(); 
    return random.nextInt(Math.abs(max) + Math.abs(min)) - Math.abs(min);
  }
  
  public static boolean canInteract(EntityPlayer player, DoubleLocation location) {
    return canInteract(player, location.getBlockX(), location.getBlockY(), location.getBlockZ());
  }
  
  public static boolean canInteract(EntityPlayer player, Location location) {
    return canInteract(player, location.getBlockX(), location.getBlockY(), location.getBlockZ());
  }
  
  public static boolean canInteractBlock(EntityPlayer player, Location location) {
    return canInteract(player, location.getBlockX(), location.getBlockY(), location.getBlockZ());
  }
  
  public static boolean canInteract(EntityPlayer player, int x, int y, int z) {
    return canInteract(player, x, y, z, Blocks.field_150350_a, 0);
  }
  
  public static boolean canInteract(EntityPlayer player, int x, int y, int z, Block block, int meta) {
    if (y < 0 || y > 256)
      return false; 
    if (player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150357_h)
      return false; 
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, player.field_70170_p, Blocks.field_150350_a, 0, player);
    event.setResult(Event.Result.DENY);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return !event.isCanceled();
  }
  
  @Deprecated
  public static boolean canInteractUnsafe(EntityPlayer player, Location location) {
    return canInteractUnsafe(player, (int)location.getX(), (int)location.getY(), (int)location.getZ());
  }
  
  @Deprecated
  public static boolean canInteractUnsafe(EntityPlayer player, int x, int y, int z) {
    return canInteractUnsafe(player, x, y, z, Blocks.field_150350_a, 0);
  }
  
  @Deprecated
  public static boolean canInteractUnsafe(EntityPlayer player, int x, int y, int z, Block block, int meta) {
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, player.field_70170_p, Blocks.field_150350_a, 0, player);
    event.setResult(Event.Result.DENY);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return !event.isCanceled();
  }
  
  public static boolean canInteract(EntityPlayer player, double x, double y, double z) {
    BlockPos pos = new BlockPos(x, y, z);
    return canInteract(player, pos.getX(), pos.getY(), pos.getZ());
  }
  
  public static boolean isBedrock(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z) == Blocks.field_150357_h);
  }
  
  public static boolean isAir(World world, int x, int y, int z) {
    return world.func_147437_c(x, y, z);
  }
  
  public static BlockPos getRandomCoordsWithinRadius(int x, int y, int z, int radius) {
    if (random == null)
      random = new Random(); 
    int newX = x + random.nextInt(radius * 2) - radius;
    int newZ = z + random.nextInt(radius * 2) - radius;
    return new BlockPos(newX, y, newZ);
  }
  
  public static Vec3 getBlockInFront(EntityPlayer player, double distance) {
    double px = player.field_70165_t;
    double py = player.field_70163_u + player.func_70047_e();
    double pz = player.field_70161_v;
    double yaw = Math.toRadians(player.field_70177_z);
    double pitch = Math.toRadians(player.field_70125_A);
    double dx = -Math.sin(yaw) * Math.cos(pitch);
    double dy = -Math.sin(pitch);
    double dz = Math.cos(yaw) * Math.cos(pitch);
    Vec3 vec = Vec3.func_72443_a(px, py, pz);
    Vec3 lookVec = vec.func_72441_c(dx * distance, dy * distance, dz * distance);
    return lookVec;
  }
  
  public static void teleportPlayer(EntityPlayerMP player, double x, double y, double z) {
    teleportPlayer(player, Vec3.func_72443_a(x, y, z));
  }
  
  public static void teleportPlayer(EntityPlayerMP player, Vec3 pos) {
    try {
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      World bukkitWorld = pl.getWorld();
      Location location = new Location(bukkitWorld, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
      pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    } catch (Exception e) {
      player.func_70634_a(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
    } 
  }
  
  public static void teleportPlayer(EntityPlayerMP player, Location forgeLocation) {
    try {
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      World bukkitWorld = pl.getWorld();
      Location location = new Location(bukkitWorld, forgeLocation.getX(), forgeLocation.getY(), forgeLocation.getZ());
      location.setYaw(forgeLocation.getYaw());
      location.setPitch(forgeLocation.getPitch());
      pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    } catch (Exception e) {
      player.func_70634_a(forgeLocation.getX(), forgeLocation.getY(), forgeLocation.getZ());
      player.field_70177_z = forgeLocation.getYaw();
      player.field_70125_A = forgeLocation.getPitch();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\EventUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */