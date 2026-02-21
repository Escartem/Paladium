package fr.paladium.palamod.modules.ablueprint.blueprints.golem;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.welsymc.guardiangolem.common.init.BlockInit;
import net.minecraft.block.Block;

public class GolemBoxBlueprint extends ABluePrint {
  public static final String ID = "golem_box";
  
  public GolemBoxBlueprint() {
    super("golem_box");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "FFF", "FFF", "FFF" }), Floor.create().addPatterns(new String[] { "FFF", "FAF", "FBF" }), Floor.create().addPatterns(new String[] { "FFF", "FFF", "FFF" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('F', BlockInit.GUARDIAN_BOX_FRAME), 
          StructureRecipe.create('A', (Block)BlocksRegister.FLUIDS_ANGELICWATER), 
          StructureRecipe.create('B', BlockInit.GUARDIAN_BOX_BLOCK) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\golem\GolemBoxBlueprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */