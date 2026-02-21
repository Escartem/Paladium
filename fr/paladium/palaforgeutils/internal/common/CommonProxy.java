package fr.paladium.palaforgeutils.internal.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.command.annotated.packet.CSPacketCommandTabCompleteCallback;
import fr.paladium.palaforgeutils.lib.command.annotated.packet.SCPacketCommandTabComplete;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.item.ItemGiveGift;
import fr.paladium.palaforgeutils.lib.extended.listener.PalaForgeUtilsExtendedListener;
import fr.paladium.palaforgeutils.lib.extended.network.SCSyncPalaForgeUtilsExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.potion.entity.CustomEntityPotion;
import fr.paladium.palaforgeutils.lib.potion.listener.CommonPotionListener;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palaforgeutils.lib.server.internal.network.SCPacketServerInformations;
import fr.paladium.palaforgeutils.lib.sidedaction.network.client.CSExecuteClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.network.client.SCExecuteClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.network.server.CSExecuteServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.network.server.SCExecuteServerAction;
import fr.paladium.palaforgeutils.lib.sound.SCPacketPlaySoundRecord;
import net.minecraft.item.Item;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("palaforge-utils");
    getNetwork().registerMessage(SCSyncPalaForgeUtilsExtendedEntityProperties.Handler.class, SCSyncPalaForgeUtilsExtendedEntityProperties.class, 0, Side.CLIENT);
    getNetwork().registerMessage(CSExecuteServerAction.Handler.class, CSExecuteServerAction.class, 1, Side.SERVER);
    getNetwork().registerMessage(SCExecuteServerAction.Handler.class, SCExecuteServerAction.class, 2, Side.CLIENT);
    getNetwork().registerMessage(CSExecuteClientAction.Handler.class, CSExecuteClientAction.class, 3, Side.SERVER);
    getNetwork().registerMessage(SCExecuteClientAction.Handler.class, SCExecuteClientAction.class, 4, Side.CLIENT);
    getNetwork().registerMessage(SCPacketServerInformations.Handler.class, SCPacketServerInformations.class, 5, Side.CLIENT);
    getNetwork().registerMessage(SCPacketPlaySoundRecord.Handler.class, SCPacketPlaySoundRecord.class, 6, Side.CLIENT);
    getNetwork().registerMessage(SCPacketCommandTabComplete.Handler.class, SCPacketCommandTabComplete.class, 7, Side.CLIENT);
    getNetwork().registerMessage(CSPacketCommandTabCompleteCallback.Handler.class, CSPacketCommandTabCompleteCallback.class, 8, Side.SERVER);
    addListener(new Class[] { PalaForgeUtilsExtendedListener.class });
    addListener(new Class[] { CommonPotionListener.class });
    RegistryUtils.entity(CustomEntityPotion.class, null, 100, PalaForgeUtilsMod.getInstance());
    RegistryUtils.item(new Item[] { (Item)new ItemGiveGift() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\internal\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */