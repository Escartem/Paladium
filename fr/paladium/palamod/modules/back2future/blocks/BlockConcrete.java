package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.lib.EnumDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockConcrete extends Block {
  private EnumDyeColor color;
  
  public BlockConcrete(EnumDyeColor color) {
    super(Material.field_151576_e);
    this.color = color;
    func_149663_c("concrete_" + this.color);
    func_149647_a(Back2Future.creativeTab);
    func_149711_c(1.8F);
    setHarvestLevel("pickaxe", 0);
    func_149752_b(9.0F);
    func_149658_d("concrete_" + this.color.getName());
  }
  
  public EnumDyeColor getColor() {
    return this.color;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockConcrete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */