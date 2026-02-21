package fr.paladium.palaschematicmod.common.pojo.data;

import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;

public class BlockInfo implements IBufSerializable {
  private String material;
  
  private byte data;
  
  private String nbtTagCompound;
  
  public void setMaterial(String material) {
    this.material = material;
  }
  
  public void setData(byte data) {
    this.data = data;
  }
  
  public void setNbtTagCompound(String nbtTagCompound) {
    this.nbtTagCompound = nbtTagCompound;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof BlockInfo))
      return false; 
    BlockInfo other = (BlockInfo)o;
    if (!other.canEqual(this))
      return false; 
    if (getData() != other.getData())
      return false; 
    Object this$material = getMaterial(), other$material = other.getMaterial();
    if ((this$material == null) ? (other$material != null) : !this$material.equals(other$material))
      return false; 
    Object this$nbtTagCompound = getNbtTagCompound(), other$nbtTagCompound = other.getNbtTagCompound();
    return !((this$nbtTagCompound == null) ? (other$nbtTagCompound != null) : !this$nbtTagCompound.equals(other$nbtTagCompound));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof BlockInfo;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getData();
    Object $material = getMaterial();
    result = result * 59 + (($material == null) ? 43 : $material.hashCode());
    Object $nbtTagCompound = getNbtTagCompound();
    return result * 59 + (($nbtTagCompound == null) ? 43 : $nbtTagCompound.hashCode());
  }
  
  public String toString() {
    return "BlockInfo(material=" + getMaterial() + ", data=" + getData() + ", nbtTagCompound=" + getNbtTagCompound() + ")";
  }
  
  public BlockInfo() {}
  
  public String getMaterial() {
    return this.material;
  }
  
  public byte getData() {
    return this.data;
  }
  
  public String getNbtTagCompound() {
    return this.nbtTagCompound;
  }
  
  public BlockInfo(String material, byte data) {
    this.material = material;
    this.data = data;
  }
  
  public BlockInfo(Block block, byte data) {
    this.material = Block.field_149771_c.func_148750_c(block);
    this.data = data;
  }
  
  public void write(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.material);
    buf.writeByte(this.data);
    PacketSerialUtils.writeString(buf, this.nbtTagCompound);
  }
  
  public void read(ByteBuf buf) {
    this.material = PacketSerialUtils.readString(buf);
    this.data = buf.readByte();
    this.nbtTagCompound = PacketSerialUtils.readString(buf);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\data\BlockInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */