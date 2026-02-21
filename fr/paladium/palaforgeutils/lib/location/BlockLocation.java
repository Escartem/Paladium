package fr.paladium.palaforgeutils.lib.location;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import io.netty.buffer.ByteBuf;
import java.util.Objects;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockLocation implements IBufSerializable {
  private World world;
  
  private int x;
  
  private int y;
  
  private int z;
  
  public World getWorld() {
    return this.world;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public BlockLocation(@NonNull World world, int x, int y, int z) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public BlockLocation(@NonNull Entity entity) {
    this(entity.field_70170_p, MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v));
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
  }
  
  @SideOnly(Side.SERVER)
  public void teleportServer(@NonNull EntityPlayer entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    TeleportUtils.teleport(entity, this.x + 0.5D, this.y, this.z + 0.5D);
  }
  
  @SideOnly(Side.CLIENT)
  public void moveClient(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    entity.func_70107_b(this.x + 0.5D, this.y, this.z + 0.5D);
  }
  
  public double distance(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return entity.func_70011_f(this.x + 0.5D, this.y, this.z + 0.5D);
  }
  
  public double distance(double x, double y, double z) {
    double d3 = this.x + 0.5D - x;
    double d4 = this.y + 0.5D - y;
    double d5 = this.z + 0.5D - z;
    return MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
  }
  
  public double distance(@NonNull BlockLocation location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return distance(location.getX(), location.getY(), location.getZ());
  }
  
  public BlockLocation add(int x, int y, int z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }
  
  public BlockLocation mult(int x, int y, int z) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    return this;
  }
  
  public boolean isPresent() {
    return this.world.func_72899_e(this.x, this.y, this.z);
  }
  
  public Block getBlock() {
    return this.world.func_147439_a(this.x, this.y, this.z);
  }
  
  public int getBlockMetadata() {
    return this.world.func_72805_g(this.x, this.y, this.z);
  }
  
  public TileEntity getTileEntity() {
    return this.world.func_147438_o(this.x, this.y, this.z);
  }
  
  public Vec3 getVec3() {
    return Vec3.func_72443_a(this.x, this.y, this.z);
  }
  
  public BlockLocation clone() {
    return new BlockLocation(this.world, this.x, this.y, this.z);
  }
  
  public void setBlock(Block block) {
    this.world.func_147449_b(this.x, this.y, this.z, block);
  }
  
  public void setBlockMetadata(int metadata) {
    this.world.func_72921_c(this.x, this.y, this.z, metadata, 3);
  }
  
  public void setBlockToAir() {
    this.world.func_147468_f(this.x, this.y, this.z);
  }
  
  public void read(ByteBuf buf) {
    String worldName = ByteBufUtils.readUTF8String(buf);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
      World clientWorld = (Minecraft.func_71410_x()).field_71439_g.field_70170_p;
      if (clientWorld.func_72912_H().func_76065_j().equals(worldName)) {
        this.world = clientWorld;
      } else {
        this.world = null;
      } 
    } else {
      for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
        if (worldServer.func_72912_H().func_76065_j().equals(worldName)) {
          this.world = (World)worldServer;
          break;
        } 
      } 
    } 
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void write(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.world.func_72912_H().func_76065_j());
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public String toString() {
    return this.world.func_72912_H().func_76065_j() + " " + this.x + " " + this.y + " " + this.z + " (block=" + getBlock() + ", metadata=" + getBlockMetadata() + ", tileEntity=" + getTileEntity() + ")";
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.world, Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof BlockLocation))
      return false; 
    BlockLocation other = (BlockLocation)obj;
    return (Objects.equals(this.world, other.world) && this.x == other.x && this.y == other.y && this.z == other.z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\location\BlockLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */