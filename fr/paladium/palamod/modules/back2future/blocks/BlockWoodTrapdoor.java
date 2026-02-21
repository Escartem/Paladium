package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWoodTrapdoor extends BlockTrapDoor implements IConfigurable {
  private final int meta;
  
  public IIcon[] icon;
  
  public BlockWoodTrapdoor(int meta) {
    super(Material.field_151575_d);
    this.icon = new IIcon[6];
    this.meta = meta;
    func_149649_H();
    func_149711_c(3.0F);
    func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName("trapdoor_" + BlockWoodDoor.names[meta]));
    func_149647_a(Back2Future.creativeTab);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister ir) {
    this.icon[1] = ir.func_94245_a("minecraft:spruce_trapdoor");
    this.icon[2] = ir.func_94245_a("minecraft:birch_trapdoor");
    this.icon[3] = ir.func_94245_a("minecraft:jungle_trapdoor");
    this.icon[4] = ir.func_94245_a("minecraft:acacia_trapdoor");
    this.icon[5] = ir.func_94245_a("minecraft:dark_oak_trapdoor");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.icon[this.meta];
  }
  
  public boolean isEnabled() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodTrapdoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */