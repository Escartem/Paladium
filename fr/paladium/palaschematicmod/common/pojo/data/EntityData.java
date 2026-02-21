package fr.paladium.palaschematicmod.common.pojo.data;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palaforgeutils.lib.nbt.FastNBT;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityData implements IBufSerializable {
  private String clazzName;
  
  private double x;
  
  private double y;
  
  private double z;
  
  private byte[] nbtBytes;
  
  public void setClazzName(String clazzName) {
    this.clazzName = clazzName;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public void setZ(double z) {
    this.z = z;
  }
  
  public void setNbtBytes(byte[] nbtBytes) {
    this.nbtBytes = nbtBytes;
  }
  
  public String toString() {
    return "EntityData(clazzName=" + getClazzName() + ", x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ", nbtBytes=" + Arrays.toString(getNbtBytes()) + ")";
  }
  
  public String getClazzName() {
    return this.clazzName;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public byte[] getNbtBytes() {
    return this.nbtBytes;
  }
  
  public static EntityData from(Entity entity, BlockPos blockPos) {
    EntityData entityData = new EntityData();
    entityData.setX(entity.field_70165_t - blockPos.getX());
    entityData.setY(entity.field_70163_u - blockPos.getY());
    entityData.setZ(entity.field_70161_v - blockPos.getZ());
    entityData.setClazzName(entity.getClass().getName());
    NBTTagCompound nbt = new NBTTagCompound();
    entity.func_70109_d(nbt);
    try {
      entityData.setNbtBytes(FastNBT.compress(nbt));
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return entityData;
  }
  
  public Entity getEntity(World world) {
    try {
      Class<?> clazz = Class.forName(this.clazzName, false, Thread.currentThread().getContextClassLoader());
      if (Entity.class.isAssignableFrom(clazz)) {
        Entity entity = clazz.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
        entity.func_70020_e(FastNBT.decompress(this.nbtBytes));
        return entity;
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public void read(ByteBuf buf) {
    this.clazzName = ByteBufUtils.readUTF8String(buf);
    this.nbtBytes = new byte[buf.readableBytes()];
    buf.readBytes(this.nbtBytes);
  }
  
  public void write(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.clazzName);
    buf.writeBytes(this.nbtBytes);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\data\EntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */