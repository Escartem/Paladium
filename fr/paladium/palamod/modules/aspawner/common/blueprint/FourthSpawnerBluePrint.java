package fr.paladium.palamod.modules.aspawner.common.blueprint;

import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class FourthSpawnerBluePrint extends ASpawnerBluePrint {
  public static final String ID = "fourth_spawner";
  
  public FourthSpawnerBluePrint() {
    super("fourth_spawner", Tier.FOUR);
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create()
          .addPatterns(new String[] { 
              "O O       O O", "    O   O    ", "O           O", "   O SPS O   ", " O  OSOSO  O ", "   SSOOOSS   ", "   POOXOOP   ", "   SSOOOSS   ", " O  OSOSO  O ", "   O SPS O   ", 
              "O           O", "    O   O    ", "O O       O O" }), Floor.create()
          .addPatterns(new String[] { 
              "O O       O O", "    O   O    ", "O           O", "   G     G   ", " O         O ", "             ", "             ", "             ", " O         O ", "   G  P  G   ", 
              "O           O", "    O   O    ", "O O       O O" }), Floor.create()
          .addPatterns(new String[] { 
              "O O       O O", "    I   I    ", "O           O", "             ", " I         I ", "             ", "             ", "             ", " I         I ", "      Z      ", 
              "O           O", "    I   I    ", "O O       O O" }), Floor.create()
          .addPatterns(new String[] { 
              "E E       E E", "             ", "E           E", "             ", "             ", "             ", "             ", "             ", "             ", "             ", 
              "E           E", "             ", "E E       E E" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', BlocksRegister.BLOCK_PALADIUM), 
          StructureRecipe.create('O', Blocks.field_150343_Z), 
          StructureRecipe.create('S', Blocks.field_150425_aM), 
          StructureRecipe.create('X', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), 
          StructureRecipe.create('Z', (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER), 
          StructureRecipe.create('G', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO), 
          StructureRecipe.create('I', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE), 
          StructureRecipe.create('E', (Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_FOUR) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\common\blueprint\FourthSpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */