package fr.paladium.palamod.modules.ablueprint.blueprints.remover;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;

public class TitanePortalBluePrint extends ABluePrint {
  public static final String ID = "titane_portal";
  
  public TitanePortalBluePrint() {
    super("titane_portal");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "TWWWT", "W   W", "W   W", "W   W", "TWPWT" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('P', (Block)BlocksRegister.TITANE_PORTAL_BLOCK), 
          StructureRecipe.create('W', (Block)BlocksRegister.SHINY_JUDEECERCIS_WOOD), 
          StructureRecipe.create('T', BlocksRegister.BLOCK_TITANE) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\remover\TitanePortalBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */