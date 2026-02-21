package fr.paladium.palahologram.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.zephyrui.lib.utils.clipboard.ClipboardUtils;
import net.minecraft.nbt.NBTTagCompound;

public class SCPacketCopyHologram extends ForgePacket {
  @PacketData
  private NBTTagCompound hologramNbt;
  
  public SCPacketCopyHologram() {}
  
  public SCPacketCopyHologram(NBTTagCompound hologramNbt) {
    this.hologramNbt = hologramNbt;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ClipboardUtils.setClipboard(this.hologramNbt.toString());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\network\SCPacketCopyHologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */