package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityZombieHalloween extends EntityZombie {
  public EntityZombieHalloween(World world) {
    super(world);
  }
  
  public int getType() {
    return func_70096_w().func_75679_c(18);
  }
  
  public void setType(int type) {
    func_70096_w().func_75692_b(18, Integer.valueOf(type));
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    func_70096_w().func_75682_a(18, Integer.valueOf(0));
  }
  
  public void func_82164_bB() {
    func_70062_b(4, new ItemStack(BlocksRegister.PUMKINCUSTOM[this.field_70146_Z
            .nextInt(BlocksRegister.PUMKINCUSTOM.length)]));
  }
  
  public void func_82227_f(boolean isChild) {}
  
  public boolean func_70631_g_() {
    return false;
  }
  
  protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    func_70099_a(new ItemStack(Blocks.field_150423_aK, 64), 0.0F);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityZombieHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */