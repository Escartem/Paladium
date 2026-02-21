package fr.paladium.palarpg.module.dungeon.common.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDungeonHub extends TileEntity {
  private ForgeDirection direction;
  
  public void setDirection(ForgeDirection direction) {
    this.direction = direction;
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("direction"))
      this.direction = ForgeDirection.getOrientation(nbt.func_74762_e("direction")); 
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    if (this.direction != null)
      nbt.func_74768_a("direction", this.direction.ordinal()); 
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
  
  public float getRotation() {
    if (this.direction == null)
      this.direction = ForgeDirection.NORTH; 
    switch (this.direction) {
      case NORTH:
        return 180.0F;
      case EAST:
        return 270.0F;
      case SOUTH:
        return 0.0F;
      case WEST:
        return 90.0F;
    } 
    return 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\tileentity\TileEntityDungeonHub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */