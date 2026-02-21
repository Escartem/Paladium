package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.ExplosiveInventoryEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.SandStormEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.events.CorruptedChronoEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.events.FakeCorruptedChestEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;

public class NovemberServerModule extends AbstractMonthlyModule {
  public NovemberServerModule() {
    super(SideType.SERVER);
  }
  
  public void registerTickables() {
    registerTickable((ATickable)ExplosiveInventoryEvent.getInstance());
    registerTickable((ATickable)SandStormEvent.getInstance());
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new CorruptedChronoEventHandler());
    registerEventHandler(new FakeCorruptedChestEventHandler());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\server\NovemberServerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */