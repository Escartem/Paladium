package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWood extends TileEntity {
  private String seveType = "null";
  
  public String getSeveType() {
    return this.seveType;
  }
  
  public void setSeveType(String seveType) {
    this.seveType = seveType;
  }
  
  private int seveLevel = 36;
  
  public int getSeveLevel() {
    return this.seveLevel;
  }
  
  public void setSeveLevel(int seveLevel) {
    this.seveLevel = seveLevel;
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74768_a("seveLevel", this.seveLevel);
    tag.func_74778_a("seveType", this.seveType);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.seveLevel = tag.func_74762_e("seveLevel");
    this.seveType = tag.func_74779_i("seveType");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */