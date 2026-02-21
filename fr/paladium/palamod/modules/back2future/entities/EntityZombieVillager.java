package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityZombieVillager extends EntityZombie {
  public EntityZombieVillager(World world) {
    super(world);
  }
  
  public int getType() {
    return func_70096_w().func_75679_c(15);
  }
  
  public void setType(int type) {
    func_70096_w().func_75692_b(15, Integer.valueOf(type));
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    func_70096_w().func_75682_a(15, Integer.valueOf(0));
  }
  
  public boolean func_82231_m() {
    return true;
  }
  
  public void func_82227_f(boolean isChild) {}
  
  public boolean func_70631_g_() {
    return false;
  }
  
  protected void func_82232_p() {
    EntityVillager villager = new EntityVillager(this.field_70170_p);
    villager.func_82149_j((Entity)this);
    villager.func_110161_a((IEntityLivingData)null);
    villager.func_82187_q();
    villager.func_70938_b(getType());
    if (func_70631_g_())
      villager.func_70873_a(-24000); 
    this.field_70170_p.func_72900_e((Entity)this);
    this.field_70170_p.func_72838_d((Entity)villager);
    villager.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
    this.field_70170_p.func_72889_a(null, 1017, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("VillagerType", getType());
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    setType(nbt.func_74762_e("VillagerType"));
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData data) {
    setType(this.field_70170_p.field_73012_v.nextInt(6));
    return super.func_110161_a(data);
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return ModEntityList.getEggFor((Class)getClass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */