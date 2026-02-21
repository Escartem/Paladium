package fr.paladium.palaforgeutils.lib.java.map.player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public abstract class PlayerMap<V> extends AbstractPlayerMap<V> {
  public PlayerMap() {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public abstract V getDefaultValue();
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    V defaultValue = getDefaultValue();
    if (defaultValue == null)
      return; 
    put((Entity)e.player, defaultValue);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\map\player\PlayerMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */