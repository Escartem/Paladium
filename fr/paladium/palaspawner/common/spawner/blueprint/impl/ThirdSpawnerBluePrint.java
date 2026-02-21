package fr.paladium.palaspawner.common.spawner.blueprint.impl;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
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
              "   O   O   ", "           ", "  O SPS O  ", "O  OSOSO  O", "  SSOOOSS  ", "  POOXOOP  ", "  SSOOOSS  ", "O  OSOSO  O", "  O S S O  ", "           ", 
              "   O   O   " }), Floor.create()
          .addPatterns(new String[] { 
              "   O   O   ", "           ", "  G     G  ", "O         O", "           ", "           ", "           ", "O         O", "  G     G  ", "           ", 
              "   O   O   " }), Floor.create()
          .addPatterns(new String[] { 
              "   I   I   ", "           ", "           ", "I         I", "           ", "           ", "           ", "I         I", "     Z     ", "           ", 
              "   I   I   " }), Floor.create()
          .addPatterns(new String[] { 
              "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", "           ", 
              "           " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', Blocks.field_150402_ci), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', Blocks.field_150451_bX), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER), 
          StructureRecipe.create('G', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), 
          StructureRecipe.create('I', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\blueprint\impl\ThirdSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */