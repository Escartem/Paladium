package fr.paladium.palaforgeutils.lib.potion.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palaforgeutils.lib.potion.item.CustomSplashItemPotion;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CustomEntityPotion extends EntityThrowable implements IEntityAdditionalSpawnData {
  private ItemStack item;
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public CustomEntityPotion(World world) {
    super(world);
  }
  
  public CustomEntityPotion(World world, EntityLivingBase thrower, ItemStack item) {
    super(world, thrower);
    this.item = item;
  }
  
  public float func_70185_h() {
    return 0.05F;
  }
  
  public float func_70182_d() {
    return 0.5F;
  }
  
  public float func_70183_g() {
    return -20.0F;
  }
  
  public void func_70184_a(MovingObjectPosition hitInfo) {
    if (this.field_70170_p.field_72995_K)
      return; 
    AxisAlignedBB axisAlignedBB = this.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D);
    List<EntityLivingBase> entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisAlignedBB);
    if (!entities.isEmpty()) {
      Iterator<EntityLivingBase> iterator = entities.iterator();
      while (iterator.hasNext()) {
        EntityLivingBase entity = iterator.next();
        double distance = func_70068_e((Entity)entity);
        if (distance < 16.0D) {
          double mappedDistance = 1.0D - Math.sqrt(distance) / 4.0D;
          if (entity == hitInfo.field_72308_g)
            mappedDistance = 1.0D; 
          PotionEffect potioneffect = ((CustomSplashItemPotion)this.item.func_77973_b()).getEffect();
          int potionId = potioneffect.func_76456_a();
          int duration = (int)(mappedDistance * potioneffect.func_76459_b() + 0.5D);
          if (duration > 20)
            entity.func_70690_d(new PotionEffect(potionId, duration, potioneffect.func_76458_c())); 
        } 
      } 
    } 
    this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), ((CustomSplashItemPotion)this.item.func_77973_b()).getEffect().func_76456_a());
    func_70106_y();
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
    if (compound.func_150297_b("Potion", 10))
      this.item = ItemStack.func_77949_a(compound.func_74775_l("Potion")); 
    if (this.item == null)
      func_70106_y(); 
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    super.func_70014_b(compound);
    if (this.item != null)
      compound.func_74782_a("Potion", (NBTBase)this.item.func_77955_b(new NBTTagCompound())); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.item == null)
      return; 
    ByteBufUtils.writeItemStack(buffer, this.item);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (!additionalData.isReadable())
      return; 
    this.item = ByteBufUtils.readItemStack(additionalData);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\entity\CustomEntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */