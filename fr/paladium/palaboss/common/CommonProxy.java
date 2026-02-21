package fr.paladium.palaboss.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.lindworm.Lindworm;
import fr.paladium.palaboss.common.entity.impl.DemoEntity;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import java.awt.Color;

public abstract class CommonProxy extends AModProxy {
  private static CommonProxy INSTANCE;
  
  public CommonProxy() {
    INSTANCE = this;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    if (ForgeEnv.isDev())
      RegistryUtils.entity(DemoEntity.class, Color.BLACK, 100, Lindworm.getInstance()); 
  }
  
  public static CommonProxy inst() {
    return INSTANCE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */