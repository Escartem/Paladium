package fr.paladium.palamod.modules.pillage.common.blocks.tree;

import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenBirchWater extends WorldGenAbstractTree {
  private final boolean useExtraRandomHeightIn;
  
  public WorldGenBirchWater(boolean notify, boolean useExtraRandomHeightIn) {
    super(notify);
    this.useExtraRandomHeightIn = useExtraRandomHeightIn;
  }
  
  public boolean func_76484_a(World world, Random rand, int posX, int posY, int posZ) {
    int randHeight = rand.nextInt(3) + 5;
    if (this.useExtraRandomHeightIn)
      randHeight += rand.nextInt(7); 
    boolean flag = true;
    if (posY < 1 || posY + randHeight + 1 > 256)
      return false; 
    for (int i1 = posY; i1 <= posY + 1 + randHeight; i1++) {
      byte b0 = 1;
      if (i1 == posY)
        b0 = 0; 
      if (i1 >= posY + 1 + randHeight - 2)
        b0 = 2; 
      for (int j1 = posX - b0; j1 <= posX + b0 && flag; j1++) {
        for (int k1 = posZ - b0; k1 <= posZ + b0 && flag; k1++) {
          if (i1 >= 0 && i1 < 256) {
            Block block = world.func_147439_a(j1, i1, k1);
            if (!isReplaceable(world, j1, i1, k1) && !(block instanceof fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockAngelicWater))
              flag = false; 
          } else {
            flag = false;
          } 
        } 
      } 
    } 
    if (!flag)
      return false; 
    Block block2 = world.func_147439_a(posX, posY - 1, posZ);
    boolean isSoil = block2 instanceof net.minecraft.block.BlockClay;
    if (!isSoil || posY >= 256 - randHeight - 1)
      return false; 
    block2.onPlantGrow(world, posX, posY - 1, posZ, posX, posY, posZ);
    int k2;
    for (k2 = posY - 3 + randHeight; k2 <= posY + randHeight; k2++) {
      int j1 = k2 - posY + randHeight;
      int k1 = 1 - j1 / 2;
      for (int l2 = posX - k1; l2 <= posX + k1; l2++) {
        int l1 = l2 - posX;
        for (int i2 = posZ - k1; i2 <= posZ + k1; i2++) {
          int j2 = i2 - posZ;
          if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || (rand.nextInt(2) != 0 && j1 != 0)) {
            Block block1 = world.func_147439_a(l2, k2, i2);
            if (block1 instanceof fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockAngelicWater || block1.isLeaves((IBlockAccess)world, l2, k2, i2))
              func_150516_a(world, l2, k2, i2, PPRegisterer.PPBlocks.WATER_LEAVES, 0); 
          } 
        } 
      } 
    } 
    for (k2 = 1; k2 < randHeight; k2++) {
      Block block3 = world.func_147439_a(posX, posY + k2, posZ);
      if (block3 instanceof fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockAngelicWater || block3.isLeaves((IBlockAccess)world, posX, posY + k2, posZ)) {
        func_150516_a(world, posX, posY + k2, posZ, PPRegisterer.PPBlocks.WATER_LOG, 0);
        if (k2 == 1 && world.func_147437_c(posX, posY, posZ))
          func_150516_a(world, posX, posY, posZ, PPRegisterer.PPBlocks.WATER_LOG, 0); 
      } 
    } 
    return true;
  }
  
  protected boolean func_150523_a(Block block) {
    return (super.func_150523_a(block) || block == PPRegisterer.PPBlocks.WATER_SAPLING);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tree\WorldGenBirchWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */