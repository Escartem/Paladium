package fr.paladium.palamod.modules.aspawner.common.blueprint;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
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
          .addPatterns(new String[] { "  SPS  ", " OSOSO ", "SSOOOSS", "POOXOOP", "SSOOOSS", " OSOSO ", "  SPS  " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "   P   " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "   Z   " }), Floor.create()
          .addPatterns(new String[] { "       ", "       ", "       ", "       ", "       ", "       ", "       " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', BlocksRegister.BLOCK_PALADIUM), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\common\blueprint\FirstSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */