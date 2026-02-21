package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.BlacklistedTooltipHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.DarkRenderEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.EnchantTooltipHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.LanguageCheckEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.LavaRenderEventHandler;

public class SeptemberClientModule extends AbstractMonthlyModule {
  private static SeptemberClientModule instance;
  
  public SeptemberClientModule() {
    super(SideType.CLIENT);
    instance = this;
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new DarkRenderEventHandler());
    registerEventHandler(new LanguageCheckEventHandler());
    registerEventHandler(new EnchantTooltipHandler());
    registerEventHandler(new LavaRenderEventHandler());
    registerEventHandler(new BlacklistedTooltipHandler());
  }
  
  public void registerRenderers() {}
  
  public static SeptemberClientModule getInstance() {
    if (instance == null)
      instance = new SeptemberClientModule(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\SeptemberClientModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */