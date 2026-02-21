package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockFakeOrePaladium extends Block {
  public BlockFakeOrePaladium() {
    super(Material.field_151576_e);
    func_149658_d("palamod:ores/paladium_ore");
    func_149663_c("paladium_fake_ore");
    func_149711_c(5.0F);
    func_149752_b(5.0F);
    setHarvestLevel("pickaxe", 3);
    func_149672_a(field_149777_j);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
    player.func_71064_a(StatList.field_75934_C[func_149682_b(this)], 1);
    player.func_71020_j(0.025F);
    if (canSilkHarvest(world, player, x, y, z, meta) && 
      EnchantmentHelper.func_77502_d((EntityLivingBase)player)) {
      ArrayList<ItemStack> items = new ArrayList<>();
      ItemStack itemstack = func_149644_j(meta);
      if (itemstack != null)
        items.add(itemstack); 
      ForgeEventFactory.fireBlockHarvesting(items, world, this, x, y, z, meta, 0, 1.0F, true, player);
      for (ItemStack is : items)
        func_149642_a(world, x, y, z, is); 
    } 
  }
  
  protected boolean func_149700_E() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakeOrePaladium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */