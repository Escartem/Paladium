package fr.paladium.palamod.modules.alchimiste.common.blocks;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumGlueball;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGlueball extends BlockBase {
  private String name;
  
  public BlockGlueball(EnumGlueball glueball) {
    super(glueball.getName().toLowerCase() + "_glueball_block");
    this.name = glueball.getName().toLowerCase() + "_glueball_block";
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.01F, 1.0F);
    func_149675_a(true);
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return AxisAlignedBB.func_72330_a(p_149668_2_ + this.field_149759_B, p_149668_3_ + this.field_149760_C, p_149668_4_ + this.field_149754_D, p_149668_2_ + this.field_149755_E, p_149668_3_ + 0.01D, p_149668_4_ + this.field_149757_G);
  }
  
  public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return AxisAlignedBB.func_72330_a(p_149668_2_ + this.field_149759_B, p_149668_3_ + this.field_149760_C, p_149668_4_ + this.field_149754_D, p_149668_2_ + this.field_149755_E, p_149668_3_ + 0.01D, p_149668_4_ + this.field_149757_G);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public void func_149674_a(World w, int x, int y, int z, Random r) {
    w.func_147449_b(x, y, z, Blocks.field_150350_a);
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity e) {
    if (world.field_72995_K && e instanceof net.minecraft.client.particle.EntityRainFX)
      return; 
    if (this == BlocksRegister.GREEN_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase)
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 40, 1)); 
    } else if (this == BlocksRegister.BLUE_GLUEBALL_BLOCK) {
      e.func_70024_g(0.0D, 1.1D, 0.0D);
    } else if (this == BlocksRegister.YELLOW_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase) {
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 0));
      } 
    } else if (this == BlocksRegister.RED_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase) {
        ((EntityLivingBase)e).func_70691_i(2.0F);
        world.func_147449_b(x, y, z, Blocks.field_150350_a);
      } 
    } else if (this == BlocksRegister.ORANGE_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase) {
        e.func_70015_d(5);
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 60, 1));
      } 
    } else if (this == BlocksRegister.GRAY_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase)
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 6000, 0)); 
    } else if (this == BlocksRegister.PURPLE_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase)
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 120, 0)); 
    } else if (this == BlocksRegister.GREEN_FLASH_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase)
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 40, 1)); 
    } else if (this == BlocksRegister.GREEN_DARK_GLUEBALL_BLOCK) {
      if (e instanceof EntityLivingBase)
        ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 60, 0)); 
    } else if (this == BlocksRegister.CYAN_GLUEBALL_BLOCK && 
      e instanceof EntityLivingBase) {
      ((EntityLivingBase)e).func_70674_bp();
    } 
    world.func_147449_b(x, y, z, Blocks.field_150350_a);
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return null;
  }
  
  public String getName() {
    return this.name;
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {}
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockGlueball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */