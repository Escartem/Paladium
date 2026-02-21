package fr.paladium.palawither.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palawither.api.BlocksRegister;
import fr.paladium.palawither.api.CraftsRegister;
import fr.paladium.palawither.api.EntitiesRegister;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.network.SCPacketWitherData;
import fr.paladium.palawither.common.network.SCPacketWitherSpawn;
import fr.paladium.palawither.common.network.entity.SCPacketSupremeWitherExplode;
import fr.paladium.palawither.common.network.entity.SCPacketSupremeWitherSpawn;
import fr.paladium.palawither.common.network.item.CSPacketWitherTargetLaserSystem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public abstract class CommonProxy extends AModProxy {
  public static CreativeTabs CREATIVE_TAB = new CreativeTabs("palawither") {
      public Item func_78016_d() {
        return Items.field_151156_bN;
      }
    };
  
  public void onPreInit(FMLPreInitializationEvent event) {
    ItemsRegister.register();
    BlocksRegister.register();
    EntitiesRegister.register();
    initNetwork("palawither");
    PacketUtils.registerPacket(getNetwork(), SCPacketWitherData.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketWitherSpawn.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketSupremeWitherSpawn.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketSupremeWitherExplode.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketWitherTargetLaserSystem.class);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    CraftsRegister.register();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */