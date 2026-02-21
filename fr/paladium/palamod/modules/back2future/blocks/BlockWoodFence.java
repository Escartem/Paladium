package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWoodFence extends BlockFence implements ModBlocks.IBurnableBlock, IConfigurable {
  private final int meta;
  
  public BlockWoodFence(int meta) {
    super(null, Material.field_151575_d);
    this.meta = meta;
    func_149711_c(2.0F);
    func_149752_b(5.0F);
    func_149672_a(field_149766_f);
    func_149663_c(Utils.getUnlocalisedName("fence_" + BlockWoodDoor.names[meta]));
    func_149647_a(Back2Future.enableFences ? Back2Future.creativeTab : null);
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return (side == ForgeDirection.UP);
  }
  
  public boolean func_149826_e(IBlockAccess world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return (super.func_149826_e(world, x, y, z) || block instanceof BlockWoodFence || block instanceof BlockWoodFenceGate);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {}
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return Blocks.field_150344_f.func_149691_a(side, this.meta);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableFences;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */