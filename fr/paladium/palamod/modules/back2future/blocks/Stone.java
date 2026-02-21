package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Stone extends BlockGeneric implements IConfigurable {
  public static final int GRANITE = 1;
  
  public static final int POLISHED_GRANITE = 2;
  
  public static final int DIORITE = 3;
  
  public static final int POLISHED_DIORITE = 4;
  
  public static final int ANDESITE = 5;
  
  public static final int POLISHED_ANDESITE = 6;
  
  public Stone() {
    super(Material.field_151576_e, new String[] { "", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth" });
    this.startMeta = 1;
    func_149711_c(1.5F);
    func_149752_b(10.0F);
    func_149658_d("stone");
    func_149672_a(field_149780_i);
    func_149663_c(Utils.getUnlocalisedName("stone"));
    func_149647_a(Back2Future.enableStones ? Back2Future.creativeTab : null);
  }
  
  public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
    return (this == target || target == Blocks.field_150348_b);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableStones;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\Stone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */