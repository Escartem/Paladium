package fr.paladium.palawarzoneevent.module.warzone.common.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityWarzoneSign extends TileEntity {
  private float rotation;
  
  public void setRotation(float rotation) {
    this.rotation = rotation;
  }
  
  public TileEntityWarzoneSign() {
    this.rotation = 0.0F;
  }
  
  public float getRotation() {
    return this.rotation;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74776_a("rotation", this.rotation);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.rotation = compound.func_74760_g("rotation");
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public AxisAlignedBB getRenderBoundingBox() {
    return super.getRenderBoundingBox().func_72314_b(3.0D, 3.0D, 3.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\block\tileentity\TileEntityWarzoneSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */