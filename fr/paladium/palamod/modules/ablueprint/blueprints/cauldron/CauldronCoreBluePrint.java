package fr.paladium.palamod.modules.ablueprint.blueprints.cauldron;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;

public class CauldronCoreBluePrint extends ABluePrint {
  public static final String ID = "cauldron_core";
  
  public CauldronCoreBluePrint() {
    super("cauldron_core");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "CCCCCCC", "CCCCCCC", "CCCCCCC", "OCCHCCO", "CCCCCCC", "CCCCCCC", "CCCCCCC" }), Floor.create().addPatterns(new String[] { "       ", " CCCCC ", " C   C ", " C   C ", " C   C ", " CCCCC ", "       " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('C', (Block)BlocksRegister.CAULDRON_BLOCK), 
          StructureRecipe.create('H', (Block)BlocksRegister.CAULDRON_CORE), 
          StructureRecipe.create('W', (Block)BlocksRegister.FLUIDS_ANGELICWATER, true), 
          StructureRecipe.create('O', (Block)BlocksRegister.CAULDRON_BLOCK, true) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\cauldron\CauldronCoreBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */