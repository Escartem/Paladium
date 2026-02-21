package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockWoodPressurePlate extends BlockPressurePlate implements IConfigurable {
  private final int meta;
  
  public BlockWoodPressurePlate(int meta) {
    super("planks_oak", Material.field_151575_d, BlockPressurePlate.Sensitivity.everything);
    this.meta = meta;
    func_149649_H();
    func_149711_c(0.5F);
    func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName("pressure_plate_" + BlockWoodDoor.names[meta]));
    func_149647_a(Back2Future.creativeTab);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return Blocks.field_150344_f.func_149691_a(side, this.meta);
  }
  
  public boolean isEnabled() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodPressurePlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */