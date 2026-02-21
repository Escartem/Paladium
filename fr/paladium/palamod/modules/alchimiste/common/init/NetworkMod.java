package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.network.clienttoserver.CSEnchantItem;
import fr.paladium.palamod.modules.alchimiste.common.network.servertoclient.SCExtractorSync;
import fr.paladium.palamod.modules.alchimiste.common.network.servertoclient.SCSyncExperience;

public class NetworkMod {
  public static void register() {
    PAlchimiste.network.registerMessage(SCExtractorSync.Handler.class, SCExtractorSync.class, 0, Side.CLIENT);
    PAlchimiste.network.registerMessage(CSEnchantItem.Handler.class, CSEnchantItem.class, 1, Side.SERVER);
    PAlchimiste.network.registerMessage(SCSyncExperience.Handler.class, SCSyncExperience.class, 2, Side.CLIENT);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\NetworkMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */