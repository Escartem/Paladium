package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagicBell extends TileEntity implements IEntityAdditionalSpawnData {
  private Integer ringings = null;
  
  public Integer getRingings() {
    return this.ringings;
  }
  
  public TileEntityMagicBell(Integer ringings) {
    this.ringings = ringings;
  }
  
  public void setRingings(Integer ringings) {
    this.ringings = ringings;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("ringings"))
      this.ringings = Integer.valueOf(compound.func_74762_e("ringings")); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.ringings != null)
      compound.func_74768_a("ringings", this.ringings.intValue()); 
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.ringings != null)
      dataTag.func_74768_a("ringings", this.ringings.intValue()); 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("ringings"))
      this.ringings = Integer.valueOf(nbtData.func_74762_e("ringings")); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.ringings != null)
      ByteBufUtils.writeVarInt(buffer, this.ringings.intValue(), 4); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.ringings = Integer.valueOf(ByteBufUtils.readVarInt(additionalData, 4)); 
  }
  
  public TileEntityMagicBell() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityMagicBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */