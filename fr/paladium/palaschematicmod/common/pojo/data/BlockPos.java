package fr.paladium.palaschematicmod.common.pojo.data;

import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class BlockPos implements IBufSerializable {
  private int x;
  
  private int y;
  
  private int z;
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void setZ(int z) {
    this.z = z;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof BlockPos))
      return false; 
    BlockPos other = (BlockPos)o;
    return !other.canEqual(this) ? false : ((getX() != other.getX()) ? false : ((getY() != other.getY()) ? false : (!(getZ() != other.getZ()))));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof BlockPos;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getX();
    result = result * 59 + getY();
    return result * 59 + getZ();
  }
  
  public String toString() {
    return "BlockPos(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ")";
  }
  
  public BlockPos() {}
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public BlockPos(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public BlockPos(Entity entity) {
    this((int)Math.floor(entity.field_70165_t), (int)Math.floor(entity.field_70163_u), (int)Math.floor(entity.field_70161_v));
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public void read(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public String getFormatted() {
    return this.x + " " + this.y + " " + this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\data\BlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */