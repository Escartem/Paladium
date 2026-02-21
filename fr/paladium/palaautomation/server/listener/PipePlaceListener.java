package fr.paladium.palaautomation.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PipePlaceListener {
  private static final HashMap<UUID, Integer> CACHE = new HashMap<>();
  
  @SubscribeEvent
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
      return; 
    EntityPlayer player = event.entityPlayer;
    World world = player.field_70170_p;
    ItemStack heldItem = event.entityPlayer.func_70694_bm();
    if (heldItem == null)
      return; 
    Block block = Block.func_149634_a(heldItem.func_77973_b());
    if (!(block instanceof fr.paladium.palaautomation.common.block.pipe.impl.ABlockPipe))
      return; 
    CACHE.put(player.func_110124_au(), Integer.valueOf(event.face));
  }
  
  @SubscribeEvent
  public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
    UUID uuid = event.player.func_110124_au();
    CACHE.remove(uuid);
  }
  
  public static Integer consume(UUID uuid) {
    if (!CACHE.containsKey(uuid))
      return null; 
    int value = ((Integer)CACHE.get(uuid)).intValue();
    CACHE.remove(uuid);
    return Integer.valueOf(value);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\server\listener\PipePlaceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */