package fr.paladium.palamod.modules.ablueprint.blueprints.flower;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class FlowerFarmBluePrint extends ABluePrint {
  public static final String ID = "flower_farm";
  
  public FlowerFarmBluePrint() {
    super("flower_farm");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "GDG", "DGD", "GDG" }), Floor.create().addPatterns(new String[] { "BRY", "XFZ", "LHP" }) });
    addRecipes(new StructureRecipe[] { 
          StructureRecipe.create('G', (Block)Blocks.field_150349_c, 0), 
          StructureRecipe.create('D', Blocks.field_150346_d, 2), 
          
          StructureRecipe.create('B', (Block)BlocksRegister.BUSH_XP_BERRY, 0), 
          StructureRecipe.create('R', (Block)Blocks.field_150328_O, 6), 
          StructureRecipe.create('Y', (Block)Blocks.field_150327_N, 0), 
          StructureRecipe.create('X', (Block)Blocks.field_150328_O, 1), 
          StructureRecipe.create('F', BlocksRegister.FLOWER_FARM, 0), 
          StructureRecipe.create('Z', (Block)Blocks.field_150328_O, 2), 
          StructureRecipe.create('L', (Block)Blocks.field_150328_O, 0), 
          StructureRecipe.create('H', BlocksRegister.FLOWER_MINERAL, 0), 
          StructureRecipe.create('P', (Block)Blocks.field_150438_bZ, 0) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\flower\FlowerFarmBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */