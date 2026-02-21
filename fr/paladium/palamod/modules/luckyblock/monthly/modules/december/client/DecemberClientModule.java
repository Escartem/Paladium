package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.listeners.ItemTooltipHandler;

public class DecemberClientModule extends AbstractMonthlyModule {
  public DecemberClientModule() {
    super(SideType.CLIENT);
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
    registerEventHandler(new ItemTooltipHandler());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\DecemberClientModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */