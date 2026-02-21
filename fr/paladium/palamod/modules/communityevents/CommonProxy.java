package fr.paladium.palamod.modules.communityevents;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palamod.modules.communityevents.eep.PlayerCakeEEP;
import net.minecraft.entity.player.EntityPlayer;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent e) {
    ExtendedUtils.registerExtended(EntityPlayer.class, PlayerCakeEEP.class, "ExtPropPalaGaletteCake", new ExtendedProperty[] { ExtendedProperty.PERSISTANT, ExtendedProperty.SELF_CONSTRUCT });
  }
  
  public void registerRenders() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */