package fr.paladium.palamod.modules.pillage.common.entities;

import fr.paladium.palamod.modules.back2future.ModBlocks;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntitySpongeSheep extends EntitySheep {
  public EntitySpongeSheep(World world) {
    super(world);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)1));
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (!this.field_70170_p.field_72995_K && 
      getLevel() < 3)
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          for (int k = 0; k < 5; k++) {
            Block block = this.field_70170_p.func_147439_a((int)this.field_70165_t + i, (int)this.field_70163_u + j, (int)this.field_70161_v + k);
            int metadata = this.field_70170_p.func_72805_g((int)this.field_70165_t + i, (int)this.field_70163_u + j, (int)this.field_70161_v + k);
            if (block.func_149688_o().equals(Material.field_151586_h) && block instanceof net.minecraft.block.BlockStaticLiquid && metadata == 0) {
              levelUp();
              this.field_70170_p.func_147468_f((int)this.field_70165_t + i, (int)this.field_70163_u + j, (int)this.field_70161_v + k);
            } 
          } 
        } 
      }  
  }
  
  public void func_70014_b(NBTTagCompound tag) {
    super.func_70014_b(tag);
    tag.func_74774_a("SheepLevel", getLevel());
  }
  
  public void func_70037_a(NBTTagCompound tag) {
    super.func_70037_a(tag);
    setLevel(tag.func_74771_c("SheepLevel"));
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return (!func_70631_g_() && !func_70892_o());
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    func_70893_e(true);
    int i = 2 + this.field_70170_p.field_73012_v.nextInt(3);
    for (int j = 0; j < i; j++)
      ret.add(new ItemStack(ModBlocks.sponge, 1)); 
    func_85030_a("mob.sheep.shear", 1.0F, 1.0F);
    resetLevels();
    return ret;
  }
  
  public byte getLevel() {
    return this.field_70180_af.func_75683_a(17);
  }
  
  public void setLevel(byte level) {
    this.field_70180_af.func_75692_b(17, Byte.valueOf(level));
  }
  
  public void levelUp() {
    byte currentLevel = getLevel();
    if (currentLevel <= 3) {
      currentLevel = (byte)(currentLevel + 1);
      setLevel(currentLevel);
    } 
  }
  
  public void resetLevels() {
    setLevel((byte)0);
  }
  
  public boolean isMaxLevel() {
    return (getLevel() == 3);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\entities\EntitySpongeSheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */