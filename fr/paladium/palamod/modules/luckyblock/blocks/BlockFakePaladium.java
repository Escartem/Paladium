package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockFakePaladium extends Block {
  public BlockFakePaladium(Material mat) {
    super(mat);
    func_149658_d("palamod:paladium/paladium_block");
    func_149663_c("paladium_fake_block");
    func_149711_c(6.0F);
    func_149672_a(field_149777_j);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM);
  }
  
  public int quantityDropped(int meta, int fortune, Random random) {
    return 5;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakePaladium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */