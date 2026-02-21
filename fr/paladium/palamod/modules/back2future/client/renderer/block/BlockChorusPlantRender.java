package fr.paladium.palamod.modules.back2future.client.renderer.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

@SideOnly(Side.CLIENT)
public class BlockChorusPlantRender extends BlockChorusFlowerRender {
  private final Random rand = new Random();
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    renderer.field_147837_f = true;
    long seed = x * 3129871L ^ y * 116129781L ^ z;
    seed = seed * seed * 42317861L + seed * 11L;
    this.rand.setSeed(seed);
    int noConUp = this.rand.nextInt(5);
    int noConDown = this.rand.nextInt(5);
    int noConWest = this.rand.nextInt(5);
    int noConEast = this.rand.nextInt(5);
    int noConNorth = this.rand.nextInt(5);
    int noConSouth = this.rand.nextInt(5);
    Block neighbourUp = world.func_147439_a(x + ForgeDirection.UP.offsetX, y + ForgeDirection.UP.offsetY, z + ForgeDirection.UP.offsetZ);
    Block neighbourDown = world.func_147439_a(x + ForgeDirection.DOWN.offsetX, y + ForgeDirection.DOWN.offsetY, z + ForgeDirection.DOWN.offsetZ);
    Block neighbourWest = world.func_147439_a(x + ForgeDirection.WEST.offsetX, y + ForgeDirection.WEST.offsetY, z + ForgeDirection.WEST.offsetZ);
    Block neighbourEast = world.func_147439_a(x + ForgeDirection.EAST.offsetX, y + ForgeDirection.EAST.offsetY, z + ForgeDirection.EAST.offsetZ);
    Block neighbourNorth = world.func_147439_a(x + ForgeDirection.NORTH.offsetX, y + ForgeDirection.NORTH.offsetY, z + ForgeDirection.NORTH.offsetZ);
    Block neighbourSouth = world.func_147439_a(x + ForgeDirection.SOUTH.offsetX, y + ForgeDirection.SOUTH.offsetY, z + ForgeDirection.SOUTH.offsetZ);
    float conWidth = 0.25F;
    if (neighbourUp == ModBlocks.chorus_flower || neighbourUp == Blocks.field_150377_bs || neighbourUp == block) {
      renderer.func_147782_a(conWidth, (1.0F - conWidth), conWidth, (1.0F - conWidth), 1.0D, (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConUp == 2 || noConUp == 3) {
      renderer.func_147782_a(conWidth, (1.0F - conWidth), conWidth, (1.0F - conWidth), 0.8125D, (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConUp == 4) {
      renderer.func_147782_a(0.3125D, (1.0F - conWidth), 0.3125D, 0.6875D, 0.875D, 0.6875D);
      renderer.func_147784_q(block, x, y, z);
    } 
    if (neighbourDown == ModBlocks.chorus_flower || neighbourDown == Blocks.field_150377_bs || neighbourDown == block) {
      renderer.func_147782_a(conWidth, 0.0D, conWidth, (1.0F - conWidth), conWidth, (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConDown == 2 || noConDown == 3) {
      renderer.func_147782_a(conWidth, 0.1875D, conWidth, (1.0F - conWidth), conWidth, (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConDown == 4) {
      renderer.func_147782_a(0.3125D, 0.125D, 0.3125D, 0.6875D, conWidth, 0.6875D);
      renderer.func_147784_q(block, x, y, z);
    } 
    if (neighbourWest == ModBlocks.chorus_flower || neighbourWest == Blocks.field_150377_bs || neighbourWest == block) {
      renderer.func_147782_a(0.0D, conWidth, conWidth, conWidth, (1.0F - conWidth), (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConWest == 2 || noConWest == 3) {
      renderer.func_147782_a(0.1875D, conWidth, conWidth, conWidth, (1.0F - conWidth), (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConWest == 4) {
      renderer.func_147782_a(0.125D, 0.3125D, 0.3125D, conWidth, 0.6875D, 0.6875D);
      renderer.func_147784_q(block, x, y, z);
    } 
    if (neighbourEast == ModBlocks.chorus_flower || neighbourEast == Blocks.field_150377_bs || neighbourEast == block) {
      renderer.func_147782_a((1.0F - conWidth), conWidth, conWidth, 1.0D, (1.0F - conWidth), (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConEast == 2 || noConEast == 3) {
      renderer.func_147782_a((1.0F - conWidth), conWidth, conWidth, 0.8125D, (1.0F - conWidth), (1.0F - conWidth));
      renderer.func_147784_q(block, x, y, z);
    } else if (noConEast == 4) {
      renderer.func_147782_a((1.0F - conWidth), 0.3125D, 0.3125D, 0.875D, 0.6875D, 0.6875D);
      renderer.func_147784_q(block, x, y, z);
    } 
    if (neighbourNorth == ModBlocks.chorus_flower || neighbourNorth == Blocks.field_150377_bs || neighbourNorth == block) {
      renderer.func_147782_a(conWidth, conWidth, 0.0D, (1.0F - conWidth), (1.0F - conWidth), conWidth);
      renderer.func_147784_q(block, x, y, z);
    } else if (noConNorth == 2 || noConNorth == 3) {
      renderer.func_147782_a(conWidth, conWidth, 0.1875D, (1.0F - conWidth), (1.0F - conWidth), conWidth);
      renderer.func_147784_q(block, x, y, z);
    } else if (noConNorth == 4) {
      renderer.func_147782_a(0.3125D, 0.3125D, 0.125D, 0.6875D, 0.6875D, conWidth);
      renderer.func_147784_q(block, x, y, z);
    } 
    if (neighbourSouth == ModBlocks.chorus_flower || neighbourSouth == Blocks.field_150377_bs || neighbourSouth == block) {
      renderer.func_147782_a(conWidth, conWidth, (1.0F - conWidth), (1.0F - conWidth), (1.0F - conWidth), 1.0D);
      renderer.func_147784_q(block, x, y, z);
    } else if (noConSouth == 2 || noConSouth == 3) {
      renderer.func_147782_a(conWidth, conWidth, (1.0F - conWidth), (1.0F - conWidth), (1.0F - conWidth), 0.8125D);
      renderer.func_147784_q(block, x, y, z);
    } else if (noConSouth == 4) {
      renderer.func_147782_a(0.3125D, 0.3125D, (1.0F - conWidth), 0.6875D, 0.6875D, 0.875D);
      renderer.func_147784_q(block, x, y, z);
    } 
    renderer.func_147782_a(conWidth, conWidth, conWidth, (1.0F - conWidth), (1.0F - conWidth), (1.0F - conWidth));
    renderer.func_147784_q(block, x, y, z);
    return true;
  }
  
  public int getRenderId() {
    return RenderIDs.CHORUS_PLANT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\block\BlockChorusPlantRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */