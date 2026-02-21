package fr.paladium.palamod.modules.aspawner.common.blueprint;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class SecondSpawnerBluePrint extends ASpawnerBluePrint {
  public static final String ID = "second_spawner";
  
  public SecondSpawnerBluePrint() {
    super("second_spawner", Tier.TWO);
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create()
          .addPatterns(new String[] { " O SPS O ", "  OSOSO  ", " SSOOOSS ", " POOXOOP ", " SSOOOSS ", "  OSOSO  ", " O SPS O " }), Floor.create()
          .addPatterns(new String[] { " G     G ", "         ", "         ", "         ", "         ", "         ", " G  P  G " }), Floor.create()
          .addPatterns(new String[] { "         ", "         ", "         ", "         ", "         ", "         ", "    Z    " }), Floor.create()
          .addPatterns(new String[] { "         ", "         ", "         ", "         ", "         ", "         ", "         " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', BlocksRegister.BLOCK_PALADIUM), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER), 
          StructureRecipe.create('G', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\common\blueprint\SecondSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */