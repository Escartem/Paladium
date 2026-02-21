package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySleepingBag extends TileEntity {
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    func_145841_b(dataTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntitySleepingBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */