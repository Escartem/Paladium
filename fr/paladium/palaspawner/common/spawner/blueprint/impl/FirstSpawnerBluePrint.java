package fr.paladium.palaspawner.common.spawner.blueprint.impl;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class FirstSpawnerBluePrint extends ASpawnerBluePrint {
  public static final String ID = "first_spawner";
  
  public FirstSpawnerBluePrint() {
    super("first_spawner", Tier.ONE);
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create()
          .addPatterns(new String[] { "  SPS  ", " OSOSO ", "SSOOOSS", "POOXOOP", "SSOOOSS", " OSOSO ", "  S S  " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "       " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "   Z   " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "       " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', Blocks.field_150402_ci), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', Blocks.field_150451_bX), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\blueprint\impl\FirstSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */