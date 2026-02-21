package fr.paladium.palamixins;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamixins.internal.util.ForgeEnv;

@Mod(modid = "palamixins", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaMixins {
  @Instance("palamixins")
  private static PalaMixins instance;
  
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    if (!ForgeEnv.isIDE() || 
      event.getSide() == Side.CLIENT);
  }
  
  public static PalaMixins getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\PalaMixins.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */