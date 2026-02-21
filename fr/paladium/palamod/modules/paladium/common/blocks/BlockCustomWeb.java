package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.palamod.common.Registry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCustomWeb extends Block {
  IIcon icon1;
  
  IIcon icon2;
  
  IIcon icon3;
  
  IIcon icon4;
  
  public BlockCustomWeb() {
    super(Material.field_151569_G);
    func_149647_a((CreativeTabs)Registry.POTION_TAB);
    func_149672_a(field_149769_e);
    setHarvestLevel("sword", 1);
    func_149663_c("customweb");
    func_149658_d("palamod:web/CustomWeb");
    func_149752_b(4.0F);
    func_149711_c(4.0F);
  }
  
  public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
    p_149670_5_.func_70110_aj();
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, this, func_149738_a(world));
  }
  
  public int func_149738_a(World p_149738_1_) {
    return 40 + p_149738_1_.field_73012_v.nextInt(20);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149651_a(IIconRegister icon) {
    this.icon1 = icon.func_94245_a("palamod:web/Web1");
    this.icon2 = icon.func_94245_a("palamod:web/Web2");
    this.icon3 = icon.func_94245_a("palamod:web/Web3");
    this.icon4 = icon.func_94245_a("palamod:web/Web4");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    switch (meta) {
      case 0:
        return this.icon1;
      case 1:
        return this.icon2;
      case 2:
        return this.icon3;
      case 3:
        return this.icon4;
      case 4:
        return this.icon4;
    } 
    return this.icon1;
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
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockCustomWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */