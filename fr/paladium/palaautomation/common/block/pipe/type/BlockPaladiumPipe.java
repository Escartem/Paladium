package fr.paladium.palaautomation.common.block.pipe.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.client.ClientProxy;
import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.block.pipe.impl.ABlockPipe;
import fr.paladium.palaautomation.common.tile.pipe.type.TileEntityPaladiumPipe;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPaladiumPipe extends ABlockPipe implements ITileEntityProvider {
  public BlockPaladiumPipe() {
    super(PipeType.PALADIUM);
  }
  
  public TileEntity func_149915_a(World worldIn, int meta) {
    return (TileEntity)new TileEntityPaladiumPipe();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderPipePaladiumId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\pipe\type\BlockPaladiumPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */