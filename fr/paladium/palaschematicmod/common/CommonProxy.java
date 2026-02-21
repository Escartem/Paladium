package fr.paladium.palaschematicmod.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palaschematicmod.common.block.BlockPalaSchematic;
import fr.paladium.palaschematicmod.common.network.CSPacketExportBlockPalaSchematic;
import fr.paladium.palaschematicmod.common.network.CSPacketUpdateBlockPalaSchematic;
import net.minecraft.block.Block;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    initNetwork("palaschematic");
    RegistryUtils.block(new Block[] { (Block)new BlockPalaSchematic() });
    PacketUtils.registerPacket(getNetwork(), CSPacketExportBlockPalaSchematic.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketUpdateBlockPalaSchematic.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */