package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLingeringPotion extends EntityPotion implements IEntityAdditionalSpawnData {
  private ItemStack stack;
  
  public EntityLingeringPotion(World world) {
    super(world);
  }
  
  public EntityLingeringPotion(World world, EntityLivingBase thrower, ItemStack stack) {
    super(world, thrower, stack);
    this.stack = stack;
  }
  
  public EntityLingeringPotion(World world, double x, double y, double z, ItemStack stack) {
    super(world, x, y, z, stack);
    this.stack = stack;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  protected void func_70184_a(MovingObjectPosition mop) {
    if (this.field_70170_p.field_72995_K)
      return; 
    this.field_70170_p.func_72838_d(new EntityLingeringEffect(this.field_70170_p, this));
    this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), 
        (int)Math.round(this.field_70161_v), this.stack.func_77960_j());
    func_70106_y();
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    ByteBufUtils.writeItemStack(buffer, this.stack);
  }
  
  public void readSpawnData(ByteBuf buffer) {
    this.stack = ByteBufUtils.readItemStack(buffer);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.stack = ItemStack.func_77949_a(nbt.func_74775_l("Potion"));
    if (this.stack == null)
      func_70106_y(); 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    if (this.stack != null)
      nbt.func_74782_a("Potion", (NBTBase)this.stack.func_77955_b(new NBTTagCompound())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityLingeringPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */