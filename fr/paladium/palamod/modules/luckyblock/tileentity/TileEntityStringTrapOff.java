package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityStringTrapOff extends TileEntity implements IEntityAdditionalSpawnData {
  public TileEntityStringTrapOff(String owner) {
    this.owner = owner;
  }
  
  private String owner = "";
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  private String whitelist = "";
  
  public String getWhitelist() {
    return this.whitelist;
  }
  
  public void setWhitelist(String whitelist) {
    this.whitelist = whitelist;
  }
  
  private boolean on = false;
  
  public boolean isOn() {
    return this.on;
  }
  
  public void setOn(boolean on) {
    this.on = on;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    if (compound.func_74764_b("whitelist"))
      this.whitelist = compound.func_74779_i("whitelist"); 
    if (compound.func_74764_b("on"))
      this.on = compound.func_74767_n("on"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", this.owner); 
    if (this.whitelist != null)
      compound.func_74778_a("whitelist", this.whitelist); 
    compound.func_74757_a("on", this.on);
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.func_82737_E() % 20L == 0L)
      for (Object e : func_145831_w().func_72872_a(EntityPlayer.class, 
          AxisAlignedBB.func_72330_a((this.field_145851_c - 16), (this.field_145848_d - 16), (this.field_145849_e - 16), (this.field_145851_c + 16), (this.field_145848_d + 16), (this.field_145849_e + 16)))) {
        if (e instanceof EntityPlayer) {
          EntityPlayer p = (EntityPlayer)e;
          if (!getOwner().equals(p.func_70005_c_()) && 
            !getWhitelist().contains(p.func_70005_c_()) && 
            isOn())
            setOn(false); 
        } 
      }  
    super.func_145845_h();
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.owner != null)
      dataTag.func_74778_a("owner", this.owner); 
    if (this.whitelist != null)
      dataTag.func_74778_a("whitelist", this.whitelist); 
    dataTag.func_74757_a("on", this.on);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("owner"))
      this.owner = nbtData.func_74779_i("owner"); 
    if (nbtData.func_74764_b("whitelist"))
      this.whitelist = nbtData.func_74779_i("whitelist"); 
    this.on = nbtData.func_74767_n("on");
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null)
      ByteBufUtils.writeUTF8String(buffer, this.owner); 
    if (this.whitelist != null)
      ByteBufUtils.writeUTF8String(buffer, this.whitelist); 
    buffer.writeBoolean(this.on);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.owner = ByteBufUtils.readUTF8String(additionalData); 
    if (additionalData.isReadable())
      this.whitelist = ByteBufUtils.readUTF8String(additionalData); 
    this.on = additionalData.readBoolean();
  }
  
  public TileEntityStringTrapOff() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityStringTrapOff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */