package fr.paladium.palavanillagui.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(ItemStack p_75214_1_) {
    return false;
  }
  
  public boolean func_82869_a(EntityPlayer player) {
    return ((player.field_71075_bZ.field_75098_d || player.field_71068_ca >= ContainerAnvil.access$000(ContainerAnvil.this)) && ContainerAnvil.access$000(ContainerAnvil.this) > 0 && func_75216_d());
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack p_82870_2_) {
    if (!player.field_71075_bZ.field_75098_d)
      player.func_82242_a(-ContainerAnvil.access$000(ContainerAnvil.this)); 
    float breakChance = ForgeHooks.onAnvilRepair(player, p_82870_2_, ContainerAnvil.access$100(ContainerAnvil.this).func_70301_a(0), ContainerAnvil.access$100(ContainerAnvil.this).func_70301_a(1));
    ContainerAnvil.access$100(ContainerAnvil.this).func_70299_a(0, (ItemStack)null);
    if (ContainerAnvil.access$200(ContainerAnvil.this) > 0) {
      ItemStack itemstack1 = ContainerAnvil.access$100(ContainerAnvil.this).func_70301_a(1);
      if (itemstack1 != null && itemstack1.field_77994_a > ContainerAnvil.access$200(ContainerAnvil.this)) {
        itemstack1.field_77994_a -= ContainerAnvil.access$200(ContainerAnvil.this);
        ContainerAnvil.access$100(ContainerAnvil.this).func_70299_a(1, itemstack1);
      } else {
        ContainerAnvil.access$100(ContainerAnvil.this).func_70299_a(1, (ItemStack)null);
      } 
    } else {
      ContainerAnvil.access$100(ContainerAnvil.this).func_70299_a(1, (ItemStack)null);
    } 
    ContainerAnvil.access$002(ContainerAnvil.this, 0);
    if (!player.field_71075_bZ.field_75098_d && !world.field_72995_K && world.func_147439_a(x, y, z) == Blocks.field_150467_bQ && player.func_70681_au().nextFloat() < breakChance) {
      int i1 = world.func_72805_g(x, y, z);
      int k = i1 & 0x3;
      int l = i1 >> 2;
      l++;
      if (l > 2) {
        world.func_147468_f(x, y, z);
        world.func_72926_e(1020, x, y, z, 0);
      } else {
        world.func_72921_c(x, y, z, k | l << 2, 2);
        world.func_72926_e(1021, x, y, z, 0);
      } 
    } else if (!world.field_72995_K) {
      world.func_72926_e(1021, x, y, z, 0);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\container\ContainerAnvil$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */