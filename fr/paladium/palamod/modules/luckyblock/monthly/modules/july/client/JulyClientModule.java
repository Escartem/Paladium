package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events.ParrotEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events.RenderEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events.TelescopeEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events.TickEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events.WoodenLegEventHandler;

public class JulyClientModule extends AbstractMonthlyModule {
  private static JulyClientModule instance;
  
  public JulyClientModule() {
    super(SideType.CLIENT);
    instance = this;
  }
  
  public static JulyClientModule getInstance() {
    if (instance == null)
      instance = new JulyClientModule(); 
    return instance;
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new RenderEventHandler());
    registerEventHandler(new TickEventHandler());
    registerEventHandler(new TelescopeEventHandler());
    registerEventHandler(new WoodenLegEventHandler());
    registerEventHandler(new ParrotEventHandler());
  }
  
  public void registerRenderers() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\JulyClientModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */