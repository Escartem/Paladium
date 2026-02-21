package fr.paladium.palavote.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palavote.common.data.PalaVotePlayer;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    ExtendedUtils.registerExtended(EntityPlayer.class, PalaVotePlayer.class, "palavote_DATA", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */