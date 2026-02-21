package fr.paladium.palamod.modules.world.blocks;

import fr.paladium.palamod.api.ItemsRegister;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockTrixiumOre extends BlockOre {
  public BlockTrixiumOre(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    func_149675_a(true);
  }
  
  public int func_149738_a(World worldIn) {
    return 30;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return ItemsRegister.TRIXIUM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\BlockTrixiumOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */