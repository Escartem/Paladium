package fr.paladium.palarpg.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import net.minecraft.entity.EntityLivingBase;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    BlocksRegister.register();
    ItemsRegister.register();
    ExtendedUtils.registerExtended(EntityLivingBase.class, RPGPlayer.class, "rpgplayer_eep", new ExtendedProperty[] { ExtendedProperty.PERSISTANT, ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
    initNetwork("palarpg");
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGPlaySound.class);
  }
  
  public boolean isDungeonWorld() {
    return Server.is(new ServerType[] { ServerType.RPG });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */