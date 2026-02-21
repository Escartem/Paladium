package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.api.BlocksRegister;
import java.util.Collection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityStringTrapOn extends TileEntity {
  private boolean activated = false;
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.func_82737_E() % 20L == 0L && !this.field_145850_b.field_72995_K && this.activated) {
      Collection<Entity> entitys = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 16), (this.field_145848_d - 100), (this.field_145849_e - 16), (this.field_145851_c + 16), (this.field_145848_d + 100), (this.field_145849_e + 16)));
      Entity entity = entitys.stream().findFirst().orElse(null);
      func_70296_d();
      if (entity != null)
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, BlocksRegister.STRING_TRAP_OFF); 
    } 
    super.func_145845_h();
  }
  
  public void setActivated(boolean activated) {
    this.activated = activated;
  }
  
  public boolean isActivated() {
    return this.activated;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74757_a("activated", this.activated);
    super.func_145841_b(compound);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    this.activated = compound.func_74767_n("activated");
    super.func_145839_a(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityStringTrapOn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */