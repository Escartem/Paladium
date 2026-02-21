package fr.paladium.palaforgeutils.lib.java.map.player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public abstract class SessionPlayerMap<V> extends PlayerMap<V> {
  public SessionPlayerMap() {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  @SubscribeEvent
  public void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
    remove((Entity)e.player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\map\player\SessionPlayerMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */