package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.HydrationRenderEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.MessRenderEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.SunBurnRenderEventHandler;

public class AugustClientModule extends AbstractMonthlyModule {
  private static AugustClientModule instance;
  
  public AugustClientModule() {
    super(SideType.CLIENT);
    instance = this;
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new HydrationRenderEventHandler());
    registerEventHandler(new SunBurnRenderEventHandler());
    registerEventHandler(new MessRenderEventHandler());
  }
  
  public void registerRenderers() {}
  
  public static AugustClientModule getInstance() {
    if (instance == null)
      instance = new AugustClientModule(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\AugustClientModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */