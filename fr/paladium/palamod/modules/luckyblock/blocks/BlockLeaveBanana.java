package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.world.blocks.BlockLeaveBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLeaveBanana extends BlockLeaveBase {
  public BlockLeaveBanana() {
    super("banana_leave", 50, null);
    func_149647_a(PLuckyBlock.TAB);
    func_149658_d("palamod:banana_leave");
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    super.func_149749_a(world, x, y, z, block, meta);
    int random = (new Random()).nextInt(10);
    if (random >= 7) {
      PlayerUtils.dropItemStack(world, x, y, z, new ItemStack((Item)ItemsRegister.BANANA));
    } else {
      PlayerUtils.dropItemStack(world, x, y, z, new ItemStack(Items.field_151055_y));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockLeaveBanana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */