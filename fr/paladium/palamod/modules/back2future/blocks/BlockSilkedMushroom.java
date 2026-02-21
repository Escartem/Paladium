package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockSilkedMushroom extends Block implements IConfigurable {
  private final Block block;
  
  public BlockSilkedMushroom(Block block, String str) {
    super(Material.field_151575_d);
    this.block = block;
    func_149711_c(0.2F);
    func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName(str + "_mushroom"));
    func_149647_a(Back2Future.enableSilkTouchingMushrooms ? Back2Future.creativeTab : null);
  }
  
  protected boolean func_149700_E() {
    return true;
  }
  
  public int func_149745_a(Random rand) {
    return Math.max(0, rand.nextInt(10) - 7);
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return this.block.func_149650_a(meta, rand, fortune);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.block.func_149691_a(side, 14);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {}
  
  public boolean isEnabled() {
    return Back2Future.enableSilkTouchingMushrooms;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockSilkedMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */