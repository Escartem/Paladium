package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.chisel.block.BlockChisel;
import java.util.Random;
import net.minecraft.block.material.Material;

public class BlockChiselGlass extends BlockChisel {
  public BlockChiselGlass(Material material, float hardness, String texture) {
    super(material, hardness, texture);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w() {
    return 0;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149745_a(Random random) {
    return 0;
  }
  
  protected boolean func_149700_E() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselGlass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */