package fr.paladium.palaschematicmod.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.world.TimedSchematicWorldSavedData;
import fr.paladium.palaschematicmod.server.manager.PalaSchematicManager;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PalaSchematicListener {
  private final PalaSchematicManager manager = PalaSchematicManager.getInstance();
  
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.START)
      return; 
    if (PalaSchematicManager.SCHEMATIC_QUEUE.hasTasks())
      PalaSchematicManager.SCHEMATIC_QUEUE.executeNext(); 
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    TimedSchematicWorldSavedData wsd = TimedSchematicWorldSavedData.get(world);
    Iterator<TimedSchematic> iterator = wsd.getTimedSchematics().iterator();
    while (iterator.hasNext()) {
      TimedSchematic timedSchematic = iterator.next();
      if (timedSchematic.getOriginalBlocks().size() == 0) {
        iterator.remove();
        wsd.save(world);
        continue;
      } 
      if (timedSchematic.getExpiration() <= System.currentTimeMillis())
        timedSchematic.revert(world); 
    } 
  }
  
  @SubscribeEvent
  public void onPalaschematicWand(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player == null || player.field_70170_p.field_72995_K || !BukkitUtils.hasPermission((Entity)player, "palaschematic.command.copy"))
      return; 
    ItemStack itemInHand = player.field_71071_by.func_70448_g();
    if (itemInHand == null || itemInHand.func_77973_b() != Items.field_151056_x || (event.action != PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
      return; 
    if (this.manager.setSchematicSession(player, event, event.action)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6PalaSchematic§8] §aPosition §e" + ((event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) ? "1" : "2") + "§a définie."));
      event.setCanceled(true);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\listener\PalaSchematicListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */