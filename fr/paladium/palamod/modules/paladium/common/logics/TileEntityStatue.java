package fr.paladium.palamod.modules.paladium.common.logics;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStatue extends TileEntity {
  private String playerUUID;
  
  private int angle;
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public void setPlayerUUID(String playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public int getAngle() {
    return this.angle;
  }
  
  public void setAngle(int angle) {
    this.angle = angle;
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    if (this.playerUUID != null)
      tag.func_74778_a("playerUUID", this.playerUUID); 
    tag.func_74768_a("angle", this.angle);
    super.func_145841_b(tag);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    this.playerUUID = tag.func_74779_i("playerUUID");
    this.angle = tag.func_74762_e("angle");
    super.func_145839_a(tag);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.playerUUID != null)
      dataTag.func_74778_a("playerUUID", this.playerUUID); 
    dataTag.func_74768_a("angle", this.angle);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    NBTTagCompound nbtData = pkt.func_148857_g();
    this.playerUUID = nbtData.func_74779_i("playerUUID");
    this.angle = nbtData.func_74762_e("angle");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileEntityStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */