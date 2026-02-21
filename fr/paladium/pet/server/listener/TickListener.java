package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.pet.common.event.PlayerMoveEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class TickListener {
  private final SessionPlayerMap<DoubleLocation> locationMap = new SessionPlayerMap<DoubleLocation>() {
      public DoubleLocation getDefaultValue() {
        return null;
      }
    };
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || event.side.isClient())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    DoubleLocation lastLocation = (DoubleLocation)this.locationMap.get((Entity)player);
    if (lastLocation == null) {
      lastLocation = new DoubleLocation((Entity)player);
      this.locationMap.put((Entity)player, lastLocation);
    } 
    DoubleLocation currentLocation = new DoubleLocation((Entity)player);
    if (lastLocation.getX() != currentLocation.getX() || lastLocation.getY() != currentLocation.getY() || lastLocation
      .getZ() != currentLocation.getZ() || lastLocation.getYaw() != currentLocation.getYaw() || lastLocation
      .getPitch() != currentLocation.getPitch()) {
      PlayerMoveEvent moveEvent = new PlayerMoveEvent(player, lastLocation, currentLocation);
      if (MinecraftForge.EVENT_BUS.post((Event)moveEvent)) {
        lastLocation.teleportServer((EntityPlayer)player);
        return;
      } 
      this.locationMap.put((Entity)player, currentLocation);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\TickListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */