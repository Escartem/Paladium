package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.listeners.JobPotionListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.listeners.WreathListener;

public class DecemberServerModule extends AbstractMonthlyModule {
  public DecemberServerModule() {
    super(SideType.SERVER);
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new JobPotionListener());
    registerEventHandler(new WreathListener());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\server\DecemberServerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */