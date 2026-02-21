package fr.paladium.palamod.modules.smeltery.registerer;

public class PSRegisterer {
  public static void init() {
    PSRegister_Blocks.init();
    PSRegister_Items.init();
    PSRegister_Network.init();
    PSRegister_Tiles.init();
  }
  
  public static void postInit() {
    PSRegister_Craft.init();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\registerer\PSRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */