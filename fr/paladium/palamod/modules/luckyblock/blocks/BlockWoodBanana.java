package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockWoodBanana extends Block {
  public BlockWoodBanana() {
    super(Material.field_151575_d);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("banana_wood");
    func_149658_d("palamod:banana_wood");
    func_149711_c(2.0F);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.WOOD_BANANIER);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockWoodBanana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */