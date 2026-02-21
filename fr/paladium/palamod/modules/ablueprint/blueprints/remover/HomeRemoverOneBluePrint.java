package fr.paladium.palamod.modules.ablueprint.blueprints.remover;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import net.minecraft.block.Block;

public class HomeRemoverOneBluePrint extends ABluePrint {
  public static final String ID = "home_remover_one";
  
  public HomeRemoverOneBluePrint() {
    super("home_remover_one");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "QQQ", "QQQ", "QQQ" }), Floor.create().addPatterns(new String[] { "   ", " H ", "   " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('Q', PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          StructureRecipe.create('H', (Block)BlocksRegister.HOME_FINDER) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\remover\HomeRemoverOneBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */