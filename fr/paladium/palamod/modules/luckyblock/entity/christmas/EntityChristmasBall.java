package fr.paladium.palamod.modules.luckyblock.entity.christmas;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChristmasBall extends Entity implements IEntityAdditionalSpawnData {
  private Entity owner;
  
  public Entity getOwner() {
    return this.owner;
  }
  
  public void setOwner(Entity owner) {
    this.owner = owner;
  }
  
  public EntityChristmasBall(World world) {
    super(world);
  }
  
  public EntityChristmasBall(World world, double x, double y, double z) {
    super(world);
    func_70107_b(x, y, z);
  }
  
  protected void func_70088_a() {}
  
  protected void func_70037_a(NBTTagCompound nbt) {
    if (nbt.func_74764_b("owner"))
      this.owner = (Entity)this.field_70170_p.func_152378_a(UUID.fromString(nbt.func_74779_i("owner"))); 
  }
  
  protected void func_70014_b(NBTTagCompound nbt) {
    if (this.owner != null)
      nbt.func_74778_a("owner", this.owner.func_110124_au().toString()); 
  }
  
  public void func_70030_z() {
    if (this.owner != null) {
      if (this.owner.field_70128_L) {
        func_70106_y();
      } else {
        double d0 = this.owner.field_70165_t + Math.cos((this.field_70173_aa * 5 % 360) * Math.PI / 180.0D) * 1.0D;
        double d1 = this.owner.field_70161_v + Math.sin((this.field_70173_aa * 5 % 360) * Math.PI / 180.0D) * 1.0D;
        func_70107_b(d0, this.field_70163_u, d1);
      } 
    } else {
      func_70106_y();
    } 
    if (this.field_70173_aa > 1200)
      func_70106_y(); 
    super.func_70030_z();
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null)
      ByteBufUtils.writeUTF8String(buffer, this.owner.func_110124_au().toString()); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.owner = (Entity)this.field_70170_p.func_152378_a(UUID.fromString(ByteBufUtils.readUTF8String(additionalData))); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\christmas\EntityChristmasBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */