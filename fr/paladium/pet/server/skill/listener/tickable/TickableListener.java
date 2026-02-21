package fr.paladium.pet.server.skill.listener.tickable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.pet.server.skill.handler.impl.active.data.BlockTickable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class TickableListener {
  public static final int MAX_BLOCKS_PER_MS = 10;
  
  public static final long MS_INCREMENT_PER_BLOCKS_COUNTS = 2L;
  
  public static final Set<BlockTickable> XRAY_TICKABLES = ConcurrentHashMap.newKeySet();
  
  public static final Set<BlockTickable> AXE_TICKABLES = ConcurrentHashMap.newKeySet();
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.START)
      return; 
    long now = System.currentTimeMillis();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    XRAY_TICKABLES.removeIf(blockTickable -> blockTickable.paste(world, now));
    AXE_TICKABLES.removeIf(blockTickable -> blockTickable.paste(world, now));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\tickable\TickableListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */