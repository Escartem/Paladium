package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySeptemberTrophy extends TileEntity implements IEntityAdditionalSpawnData {
  public static final String TILE_ENTITY_ID = "tileEntitySeptemberTrophy";
  
  private String owner;
  
  private String ownerName;
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
  
  public String getOwner() {
    return this.owner;
  }
  
  public String getOwnerName() {
    return this.ownerName;
  }
  
  public TileEntitySeptemberTrophy() {
    this.owner = null;
    this.ownerName = null;
  }
  
  public TileEntitySeptemberTrophy(String owner) {
    this.owner = owner;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    if (compound.func_74764_b("ownerName"))
      this.ownerName = compound.func_74779_i("ownerName"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", this.owner); 
    if (this.ownerName != null)
      compound.func_74778_a("ownerName", this.ownerName); 
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.owner != null)
      dataTag.func_74778_a("owner", this.owner); 
    if (this.ownerName != null)
      dataTag.func_74778_a("ownerName", this.ownerName); 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("owner"))
      this.owner = nbtData.func_74779_i("owner"); 
    if (nbtData.func_74764_b("ownerName"))
      this.ownerName = nbtData.func_74779_i("ownerName"); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null)
      ByteBufUtils.writeUTF8String(buffer, this.owner); 
    if (this.ownerName != null)
      ByteBufUtils.writeUTF8String(buffer, this.ownerName); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      this.owner = ByteBufUtils.readUTF8String(additionalData);
      this.ownerName = ByteBufUtils.readUTF8String(additionalData);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\tiles\TileEntitySeptemberTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */