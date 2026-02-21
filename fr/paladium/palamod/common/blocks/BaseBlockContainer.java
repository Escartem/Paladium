package fr.paladium.palamod.common.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BaseBlockContainer extends BlockContainer {
  protected String texture;
  
  public boolean drop = true;
  
  public BaseBlockContainer(Material material, float hardness, String texture) {
    super(material);
    this.texture = texture;
    func_149711_c(hardness);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    if (material == Material.field_151576_e) {
      func_149672_a(Block.field_149769_e);
    } else if (material == Material.field_151573_f) {
      func_149672_a(Block.field_149777_j);
    } else if (material == Material.field_151575_d) {
      func_149672_a(Block.field_149766_f);
    } else if (material == Material.field_151592_s) {
      func_149672_a(Block.field_149778_k);
    } 
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return null;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_() && this.drop; i1++) {
        ItemStack itemstack = inv.func_70301_a(i1);
        if (itemstack != null) {
          float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world.func_72838_d((Entity)entityitem)) {
            int j1 = world.field_73012_v.nextInt(21) + 10;
            if (j1 > itemstack.field_77994_a)
              j1 = itemstack.field_77994_a; 
            itemstack.field_77994_a -= j1;
            EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
            float f3 = 0.05F;
            entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
            entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            if (itemstack.func_77942_o())
              entityitem.func_92059_d()
                .func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
          } 
        } 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseBlockContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */