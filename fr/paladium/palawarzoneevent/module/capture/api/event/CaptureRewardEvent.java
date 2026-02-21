package fr.paladium.palawarzoneevent.module.capture.api.event;

import com.mongodb.lang.NonNull;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class CaptureRewardEvent extends Event {
  private final List<EntityPlayer> players;
  
  private final IFaction faction;
  
  private final CapturePoint capturePoint;
  
  public List<EntityPlayer> getPlayers() {
    return this.players;
  }
  
  public IFaction getFaction() {
    return this.faction;
  }
  
  public CapturePoint getCapturePoint() {
    return this.capturePoint;
  }
  
  public CaptureRewardEvent(@NonNull List<EntityPlayer> players, IFaction faction, @NonNull CapturePoint capturePoint) {
    this.players = players;
    this.faction = faction;
    this.capturePoint = capturePoint;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\api\event\CaptureRewardEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */