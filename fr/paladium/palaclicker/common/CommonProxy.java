package fr.paladium.palaclicker.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.common.network.packet.building.BBPacketClickerBuildingData;
import fr.paladium.palaclicker.common.network.packet.building.CSPacketClickerBuildingBuy;
import fr.paladium.palaclicker.common.network.packet.click.BBPacketClickerClick;
import fr.paladium.palaclicker.common.network.packet.shop.BBPacketClickerShopData;
import fr.paladium.palaclicker.common.network.packet.shop.CSPacketClickerShopBuy;
import fr.paladium.palaclicker.common.network.packet.upgrade.BBPacketClickerUpgradeData;
import fr.paladium.palaclicker.common.network.packet.upgrade.CSPacketClickerUpgradeBuy;
import fr.paladium.palaclicker.common.profile.ModuleClicker;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.profile.common.module.ProfileModules;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    ExtendedUtils.registerExtended(EntityPlayer.class, PlayerClickerData.class, "clicker_data", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
    initPacket();
    ProfileModules.getInstance().registerModule(ModuleClicker.class);
  }
  
  private void initPacket() {
    initNetwork("palaclicker");
    PacketUtils.registerPacket(getNetwork(), BBPacketClickerBuildingData.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketClickerShopData.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketClickerUpgradeData.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketClickerClick.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketClickerBuildingBuy.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketClickerShopBuy.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketClickerUpgradeBuy.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */