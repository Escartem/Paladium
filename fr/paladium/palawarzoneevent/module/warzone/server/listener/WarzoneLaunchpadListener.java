package fr.paladium.palawarzoneevent.module.warzone.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WarzoneLaunchpadListener {
  private static final long COOLDOWN_MS = 1000L;
  
  private final Map<UUID, Long> lastLaunchTime = new HashMap<>();
  
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.END || !Server.is(new ServerType[] { ServerType.WARZONE }))
      return; 
    MinecraftServer server = MinecraftServer.func_71276_C();
    if (server == null)
      return; 
    for (WorldServer worldServer : server.field_71305_c) {
      for (Object playerObj : ((World)worldServer).field_73010_i) {
        EntityPlayer player = (EntityPlayer)playerObj;
        if (player == null)
          continue; 
        int playerX = MathHelper.func_76128_c(player.field_70165_t);
        int playerY = MathHelper.func_76128_c(player.field_70163_u);
        int playerZ = MathHelper.func_76128_c(player.field_70161_v);
        Block blockBelow = worldServer.func_147439_a(playerX, playerY - 1, playerZ);
        Block blockAt = worldServer.func_147439_a(playerX, playerY, playerZ);
        if (blockBelow == Blocks.field_150445_bS || blockAt == Blocks.field_150445_bS)
          launchPlayer(player); 
      } 
    } 
  }
  
  private void launchPlayer(EntityPlayer player) {
    UUID playerId = player.func_110124_au();
    long currentTime = System.currentTimeMillis();
    if (this.lastLaunchTime.containsKey(playerId)) {
      long lastLaunch = ((Long)this.lastLaunchTime.get(playerId)).longValue();
      if (currentTime - lastLaunch < 1000L)
        return; 
    } 
    this.lastLaunchTime.put(playerId, Long.valueOf(currentTime));
    double yaw = Math.toRadians(player.field_70177_z);
    double motionX = -Math.sin(yaw) * 7.0D;
    double motionZ = Math.cos(yaw) * 7.0D;
    double motionY = 1.5D;
    player.field_70159_w = motionX;
    player.field_70181_x = 1.5D;
    player.field_70179_y = motionZ;
    player.field_70133_I = true;
    player.field_70170_p.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\listener\WarzoneLaunchpadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */