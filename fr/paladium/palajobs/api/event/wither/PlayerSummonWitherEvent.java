package fr.paladium.palajobs.api.event.wither;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palawither.common.wither.base.IWither;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerSummonWitherEvent extends Event {
  private final EntityPlayerMP player;
  
  private final IWither wither;
  
  private final DoubleLocation location;
  
  private PlayerSummonWitherEvent(EntityPlayerMP player, IWither wither, DoubleLocation location) {
    this.player = player;
    this.wither = wither;
    this.location = location;
  }
  
  public static PlayerSummonWitherEvent of(EntityPlayerMP player, IWither wither, DoubleLocation location) {
    return new PlayerSummonWitherEvent(player, wither, location);
  }
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public IWither getWither() {
    return this.wither;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\wither\PlayerSummonWitherEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */