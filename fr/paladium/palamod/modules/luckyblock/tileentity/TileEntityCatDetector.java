package fr.paladium.palamod.modules.luckyblock.tileentity;

import java.util.Collection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCatDetector extends TileEntity {
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.func_82737_E() % 20L == 0L && !this.field_145850_b.field_72995_K) {
      int meta;
      Collection<Entity> entitys = this.field_145850_b.func_72872_a(EntityOcelot.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 5), (this.field_145848_d - 5), (this.field_145849_e - 5), (this.field_145851_c + 5), (this.field_145848_d + 5), (this.field_145849_e + 5)));
      Entity entity = entitys.stream().findFirst().orElse(null);
      func_70296_d();
      if (entity != null) {
        meta = 1;
      } else {
        meta = 0;
      } 
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 3);
    } 
    super.func_145845_h();
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityCatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */