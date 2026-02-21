package fr.paladium.palaspawner.common.spawner.blueprint.output;

import fr.paladium.blueprint.common.utils.BlockPos;
import fr.paladium.blueprint.common.utils.BluePrintOutput;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class SpawnerBluePrintOutput extends BluePrintOutput {
  public static final SpawnerBluePrintOutput INVALID = of((ASpawnerBluePrint)null, false, (List<BlockPos>)null);
  
  private final ASpawnerBluePrint bluePrint;
  
  public ASpawnerBluePrint getBluePrint() {
    return this.bluePrint;
  }
  
  protected SpawnerBluePrintOutput(ASpawnerBluePrint bluePrint, boolean valid, List<BlockPos> blocks) {
    super(valid, blocks);
    this.bluePrint = bluePrint;
  }
  
  public static SpawnerBluePrintOutput of(ASpawnerBluePrint bluePrint, boolean valid, List<BlockPos> blocks) {
    return new SpawnerBluePrintOutput(bluePrint, valid, blocks);
  }
  
  public static SpawnerBluePrintOutput of(World world, ASpawnerBluePrint bluePrint, BluePrintOutput output) {
    List<BlockPos> controllerBlocks = output.getPosFromBlock((Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER);
    if (controllerBlocks.size() != 1)
      return INVALID; 
    return of(bluePrint, output.isValid(), output.getBlocks());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\blueprint\output\SpawnerBluePrintOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */