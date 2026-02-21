package fr.paladium.palahologram.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palahologram.common.worlddata.HologramWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class SCPacketSyncHolograms extends ForgePacket {
  private NBTTagCompound hologramData;
  
  public SCPacketSyncHolograms() {}
  
  public SCPacketSyncHolograms(NBTTagCompound hologramData) {
    this.hologramData = hologramData;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    HologramWorldData.get().sync(this.hologramData);
  }
  
  public void read(ByteBuf buf) {
    this.hologramData = ByteBufUtils.readTag(buf);
  }
  
  public void write(ByteBuf buf) {
    ByteBufUtils.writeTag(buf, this.hologramData);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\network\SCPacketSyncHolograms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */