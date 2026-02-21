package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockWoodButton extends BlockButtonWood implements ModBlocks.IBurnableBlock, IConfigurable {
  private final int meta;
  
  public BlockWoodButton(int meta) {
    this.meta = meta;
    func_149663_c(Utils.getUnlocalisedName("button_" + BlockWoodDoor.names[meta]));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */