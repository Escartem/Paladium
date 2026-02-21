package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityJawsTrap extends TileEntity {
  public long tick = -1L;
  
  private boolean closed;
  
  public boolean isClosed() {
    return this.closed;
  }
  
  public void setClosed(boolean closed) {
    this.closed = closed;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("closed"))
      this.closed = compound.func_74767_n("closed"); 
    if (compound.func_74764_b("tick"))
      this.tick = compound.func_74763_f("tick"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74757_a("closed", this.closed);
    compound.func_74772_a("tick", this.tick);
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    if (this.tick >= 0L && isClosed()) {
      if (this.tick < 600L)
        this.tick++; 
    } else {
      this.tick = -1L;
    } 
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
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    func_145839_a(nbtData);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityJawsTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */