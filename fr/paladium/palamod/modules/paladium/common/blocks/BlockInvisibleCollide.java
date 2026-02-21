package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlock;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockInvisibleCollide extends BaseBlock {
  private static final Material inviGlass = new Material(MapColor.field_151660_b);
  
  public BlockInvisibleCollide(String unlocalizedName) {
    super(inviGlass, 6000000.0F, unlocalizedName);
    func_149658_d("palamod:invisible_block");
    func_149752_b(6000000.0F);
    func_149713_g(0);
    func_149722_s();
    func_149649_H();
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c(unlocalizedName);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public int func_149745_a(Random r) {
    return 0;
  }
  
  public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockInvisibleCollide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */