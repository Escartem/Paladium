package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerHolidaysEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.managers.AugustTreasureChestManager;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class AugustChestEventHandler {
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent event) {
    if (event.world.field_72995_K)
      return; 
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return; 
    if (!properties.isSummerTeleportation())
      return; 
    SummerHolidaysEvent.INSTANCE.teleport(player);
  }
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    PlayerInteractEvent.Action action = event.action;
    if (action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
      return; 
    DoubleLocation location = new DoubleLocation(event.x, event.y, event.z);
    if (AugustTreasureChestManager.getInstance().openChest((EntityPlayerMP)player, location))
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\events\AugustChestEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */