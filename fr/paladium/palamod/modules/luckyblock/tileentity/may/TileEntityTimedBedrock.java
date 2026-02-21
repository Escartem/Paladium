package fr.paladium.palamod.modules.luckyblock.tileentity.may;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTimedBedrock extends TileEntity {
  private long lifeStart = System.currentTimeMillis();
  
  private long lifeDuration = 300000L;
  
  public void func_145845_h() {
    long time = System.currentTimeMillis() - this.lifeStart;
    if (time >= this.lifeDuration)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("lifeStart"))
      this.lifeStart = compound.func_74763_f("lifeStart"); 
    if (compound.func_74764_b("lifeDuration"))
      this.lifeDuration = compound.func_74763_f("lifeDuration"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74772_a("lifeStart", this.lifeStart);
    compound.func_74772_a("lifeDuration", this.lifeDuration);
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    dataTag.func_74772_a("lifeStart", this.lifeStart);
    dataTag.func_74772_a("lifeDuration", this.lifeDuration);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("lifeStart"))
      this.lifeStart = nbtData.func_74763_f("lifeStart"); 
    if (nbtData.func_74764_b("lifeDuration"))
      this.lifeDuration = nbtData.func_74763_f("lifeDuration"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\may\TileEntityTimedBedrock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */