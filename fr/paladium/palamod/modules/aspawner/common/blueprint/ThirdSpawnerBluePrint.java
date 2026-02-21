package fr.paladium.palamod.modules.aspawner.common.blueprint;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ThirdSpawnerBluePrint extends ASpawnerBluePrint {
  public static final String ID = "third_spawner";
  
  public ThirdSpawnerBluePrint() {
    super("third_spawner", Tier.THREE);
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create()
          .addPatterns(new String[] { 
              "   O   O   ", "           ", "  O SPS O  ", "O  OSOSO  O", "  SSOOOSS  ", "  POOXOOP  ", "  SSOOOSS  ", "O  OSOSO  O", "  O SPS O  ", "           ", 
              "   O   O   " }), Floor.create()
          .addPatterns(new String[] { 
              "   O   O   ", "           ", "  G     G  ", "O         O", "           ", "           ", "           ", "O         O", "  G  P  G  ", "           ", 
              "   O   O   " }), Floor.create()
          .addPatterns(new String[] { 
              "   I   I   ", "           ", "           ", "I         I", "           ", "           ", "           ", "I         I", "     Z     ", "           ", 
              "   I   I   " }), Floor.create()
          .addPatterns(new String[] { 
              "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", 
              "           " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', BlocksRegister.BLOCK_PALADIUM), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER), 
          StructureRecipe.create('G', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO), 
          StructureRecipe.create('I', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\common\blueprint\ThirdSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */