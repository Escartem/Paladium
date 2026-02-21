package fr.paladium.palawarzoneevent.module.largage.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.lindworm.Lindworm;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palawarzoneevent.module.largage.common.entity.EntityLargage;
import java.awt.Color;

public class LargageCommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    RegistryUtils.entity(EntityLargage.class, Color.BLACK, 100, Lindworm.getInstance());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\common\LargageCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */