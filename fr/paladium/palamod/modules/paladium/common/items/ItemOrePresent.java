package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOrePresent extends Item {
  private static Block[] ores;
  
  public ItemOrePresent() {
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b("ore_present");
    func_111206_d("palamod:ore_present");
    func_77625_d(64);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      ores = new Block[] { 
          Blocks.field_150365_q, Blocks.field_150369_x, Blocks.field_150482_ag, Blocks.field_150450_ax, Blocks.field_150412_bA, Blocks.field_150366_p, Blocks.field_150352_o, PWorld.AMETHYST_ORE, PWorld.TITANE_ORE, PWorld.PALADIUM_ORE, 
          PWorld.TRIXIUM_ORE, PWorld.PALADIUM_GREEN_ORE, PWorld.FINDIUM_ORE };
      int randomNumber = world.field_73012_v.nextInt(ores.length);
      PlayerUtil.addItemStackToInventory(new ItemStack(ores[randomNumber], 32), player.field_71071_by);
      itemStack.field_77994_a--;
    } 
    return super.func_77659_a(itemStack, world, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemOrePresent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */