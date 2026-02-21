package fr.paladium.palamod.modules.palaboss.common.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockPoisonousWeb extends Block implements IShearable {
  private static final String __OBFID = "CL_00000333";
  
  public BlockPoisonousWeb() {
    super(Material.field_151569_G);
    func_149647_a(CreativeTabs.field_78031_c);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, this, func_149738_a(world));
    super.func_149726_b(world, x, y, z);
  }
  
  public int func_149738_a(World p_149738_1_) {
    return 80 + p_149738_1_.field_73012_v.nextInt(40);
  }
  
  public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
    if (p_149670_5_ instanceof EntityLivingBase && !(p_149670_5_ instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase)) {
      EntityLivingBase entityLivingBase = (EntityLivingBase)p_149670_5_;
      p_149670_5_.func_70110_aj();
      if (entityLivingBase.func_70660_b(Potion.field_76436_u) == null)
        entityLivingBase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100, 1)); 
    } 
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random1) {
    if (world.field_72995_K)
      return; 
    int meta = world.func_72805_g(x, y, z);
    if (meta < 4)
      world.func_147465_d(x, y, z, this, meta + 2, 3); 
    if (meta >= 4)
      world.func_147468_f(x, y, z); 
    world.func_147464_a(x, y, z, this, func_149738_a(world));
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> stack = new ArrayList<>();
    stack.add(item);
    return stack;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return null;
  }
  
  public int func_149645_b() {
    return 1;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Items.field_151007_F;
  }
  
  protected boolean func_149700_E() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\block\BlockPoisonousWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */