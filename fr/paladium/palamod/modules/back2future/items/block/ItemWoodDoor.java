package fr.paladium.palamod.modules.back2future.items.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemWoodDoor extends ItemBlock {
  public ItemWoodDoor(Block block) {
    super(block);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (side != 1)
      return false; 
    y++;
    if (player.func_82247_a(x, y, z, side, stack) && player
      .func_82247_a(x, y + 1, z, side, stack)) {
      if (!this.field_150939_a.func_149742_c(world, x, y, z))
        return false; 
      ItemDoor.func_150924_a(world, x, y, z, 
          MathHelper.func_76128_c(((player.field_70177_z + 180.0F) * 4.0F / 360.0F) - 0.5D) & 0x3, this.field_150939_a);
      stack.field_77994_a--;
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemWoodDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */