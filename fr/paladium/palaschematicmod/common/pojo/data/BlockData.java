package fr.paladium.palaschematicmod.common.pojo.data;

import fr.paladium.palaforgeutils.lib.itemstack.JsonToNBT;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import io.netty.buffer.ByteBuf;
import java.util.function.BiConsumer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockData implements IBufSerializable {
  private BlockPos pos;
  
  private BlockInfo info;
  
  private transient BiConsumer<BlockData, Block> blockConsumer;
  
  public void setPos(BlockPos pos) {
    this.pos = pos;
  }
  
  public void setInfo(BlockInfo info) {
    this.info = info;
  }
  
  public void setBlockConsumer(BiConsumer<BlockData, Block> blockConsumer) {
    this.blockConsumer = blockConsumer;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof BlockData))
      return false; 
    BlockData other = (BlockData)o;
    if (!other.canEqual(this))
      return false; 
    Object this$pos = getPos(), other$pos = other.getPos();
    if ((this$pos == null) ? (other$pos != null) : !this$pos.equals(other$pos))
      return false; 
    Object this$info = getInfo(), other$info = other.getInfo();
    return !((this$info == null) ? (other$info != null) : !this$info.equals(other$info));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof BlockData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $pos = getPos();
    result = result * 59 + (($pos == null) ? 43 : $pos.hashCode());
    Object $info = getInfo();
    return result * 59 + (($info == null) ? 43 : $info.hashCode());
  }
  
  public String toString() {
    return "BlockData(pos=" + getPos() + ", info=" + getInfo() + ", blockConsumer=" + getBlockConsumer() + ")";
  }
  
  public BlockData() {}
  
  public BlockPos getPos() {
    return this.pos;
  }
  
  public BlockInfo getInfo() {
    return this.info;
  }
  
  public BiConsumer<BlockData, Block> getBlockConsumer() {
    return this.blockConsumer;
  }
  
  public BlockData(BlockPos pos, BlockInfo info) {
    this(pos, info, null);
  }
  
  public BlockData(BlockPos pos, BlockInfo info, BiConsumer<BlockData, Block> blockConsumer) {
    this.pos = pos;
    this.info = info;
    this.blockConsumer = blockConsumer;
  }
  
  public void write(ByteBuf buf) {
    this.pos.write(buf);
    this.info.write(buf);
  }
  
  public void read(ByteBuf buf) {
    this.pos = new BlockPos();
    this.pos.read(buf);
    this.info = new BlockInfo();
    this.info.read(buf);
  }
  
  public void clear() {
    this.pos = null;
    this.info = null;
    this.blockConsumer = null;
  }
  
  public void apply(World world) {
    apply(world, true);
  }
  
  public void apply(World world, boolean pasteData) {
    int worldX = this.pos.getX();
    int worldY = this.pos.getY();
    int worldZ = this.pos.getZ();
    Block block = Block.func_149684_b(this.info.getMaterial());
    if (block == null)
      block = Blocks.field_150348_b; 
    TileEntity existingTE = world.func_147438_o(worldX, worldY, worldZ);
    if (existingTE != null) {
      existingTE.func_145843_s();
      world.func_147475_p(worldX, worldY, worldZ);
    } 
    world.func_147465_d(worldX, worldY, worldZ, block, this.info.getData(), 2);
    if (pasteData && this.info.getNbtTagCompound() != null && block.hasTileEntity(this.info.getData())) {
      TileEntity te = world.func_147438_o(worldX, worldY, worldZ);
      if (te != null)
        try {
          NBTTagCompound tag = JsonToNBT.getTagFromJson(this.info.getNbtTagCompound());
          tag.func_74768_a("x", worldX);
          tag.func_74768_a("y", worldY);
          tag.func_74768_a("z", worldZ);
          te.func_145839_a(tag);
          te.func_70296_d();
          world.func_72921_c(worldX, worldY, worldZ, this.info.getData(), 2);
          world.func_147471_g(worldX, worldY, worldZ);
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } 
    if (this.blockConsumer != null)
      this.blockConsumer.accept(this, block); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\data\BlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */