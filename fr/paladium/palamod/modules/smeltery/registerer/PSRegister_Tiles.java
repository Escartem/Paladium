package fr.paladium.palamod.modules.smeltery.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;

public class PSRegister_Tiles {
  public static void init() {
    GameRegistry.registerTileEntity(GrinderLogic.class, "palamod:grinder");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\registerer\PSRegister_Tiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */