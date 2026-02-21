package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockMinerMultiFaces extends Block {
  private final String top;
  
  private final String front;
  
  private final String side;
  
  private final IIcon[] icons = new IIcon[3];
  
  public BlockMinerMultiFaces(String name, String top, String front, String side) {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c(name);
    func_149658_d("palamod:miner/" + name);
    this.top = top;
    this.front = front;
    this.side = side;
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:miner/" + this.front);
    this.icons[1] = register.func_94245_a("palamod:miner/" + this.side);
    this.icons[2] = register.func_94245_a("palamod:miner/" + this.top);
    this.field_149761_L = this.icons[1];
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    int meta = ba.func_72805_g(x, y, z);
    if (dir == 0)
      return this.icons[2]; 
    if ((dir == 4 && meta == 1) || (dir == 2 && meta == 2) || (dir == 5 && meta == 3) || (dir == 3 && meta == 0))
      return this.icons[0]; 
    if (dir == 1)
      return this.icons[2]; 
    return this.icons[1];
  }
  
  public IIcon func_149691_a(int dir, int meta) {
    if (dir == 0)
      return this.icons[2]; 
    if ((dir == 4 && meta == 1) || (dir == 2 && meta == 2) || (dir == 5 && meta == 3) || (dir == 3 && meta == 0))
      return this.icons[0]; 
    if (dir == 1)
      return this.icons[2]; 
    return this.icons[1];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockMinerMultiFaces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */