package fr.paladium.palamod.modules.back2future.items.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGenericSlab extends ItemBlock {
  public ItemGenericSlab(Block block) {
    super(block);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (stack.field_77994_a == 0)
      return false; 
    if (!player.func_82247_a(x, y, z, side, stack))
      return false; 
    Block block = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    boolean flag = (meta == 1);
    if (block == this.field_150939_a && ((side == 1 && !flag) || (side == 0 && flag))) {
      if (world
        .func_72855_b(this.field_150939_a.func_149668_a(world, x, y, z)) && world
        .func_147465_d(x, y, z, this.field_150939_a, 2, 3)) {
        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), this.field_150939_a.field_149762_H
            .func_150496_b(), (this.field_150939_a.field_149762_H
            .func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H
            .func_150494_d() * 0.8F);
        stack.field_77994_a--;
      } 
      return true;
    } 
    return func_150946_a(stack, player, world, x, y, z, side) ? true : super
      .func_77648_a(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
    int i1 = x;
    int j1 = y;
    int k1 = z;
    Block block = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    if (block == this.field_150939_a && meta == 2)
      return true; 
    if (side == 0)
      y--; 
    if (side == 1)
      y++; 
    if (side == 2)
      z--; 
    if (side == 3)
      z++; 
    if (side == 4)
      x--; 
    if (side == 5)
      x++; 
    Block block1 = world.func_147439_a(x, y, z);
    return (block1 == this.field_150939_a) ? true : super
      .func_150936_a(world, i1, j1, k1, side, player, stack);
  }
  
  private boolean func_150946_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
    if (side == 0)
      y--; 
    if (side == 1)
      y++; 
    if (side == 2)
      z--; 
    if (side == 3)
      z++; 
    if (side == 4)
      x--; 
    if (side == 5)
      x++; 
    Block block = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    if (block == this.field_150939_a && meta != 2) {
      if (world
        .func_72855_b(this.field_150939_a.func_149668_a(world, x, y, z)) && world
        .func_72921_c(x, y, z, 2, 3)) {
        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), this.field_150939_a.field_149762_H
            .func_150496_b(), (this.field_150939_a.field_149762_H
            .func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H
            .func_150494_d() * 0.8F);
        stack.field_77994_a--;
      } 
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemGenericSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */