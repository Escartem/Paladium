package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client;

import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener.BlackScreenRenderListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener.StarRenderListener;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;

public class ClientMarch extends AbstractMonthlyModule {
  public ClientMarch() {
    super(SideType.CLIENT, LuckyType.MARCH);
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
    registerEventHandler(new Class[] { BlackScreenRenderListener.class, StarRenderListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\ClientMarch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */