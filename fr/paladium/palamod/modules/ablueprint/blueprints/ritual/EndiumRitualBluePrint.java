package fr.paladium.palamod.modules.ablueprint.blueprints.ritual;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.init.Blocks;

public class EndiumRitualBluePrint extends ABluePrint {
  public static final String ID = "endium_ritual";
  
  public EndiumRitualBluePrint() {
    super("endium_ritual");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { 
              "           ", "           ", "           ", "           ", "           ", "     T     ", "           ", "           ", "           ", "           ", 
              "           " }), Floor.create().addPatterns(new String[] { 
              "   RRRRR   ", "   R   R   ", "   R S R   ", "RRRR   RRRR", "R         R", "R S     S R", "R         R", "RRRR   RRRR", "   R S R   ", "   R   R   ", 
              "   RRRRR   " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('A', Blocks.field_150350_a), 
          StructureRecipe.create('R', BlocksRegister.PERLINPINPIN_POWER), 
          StructureRecipe.create('T', BlocksRegister.ENDIUM_TOTEM), 
          StructureRecipe.create('S', BlocksRegister.SACRIFICE_HOTEL) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\ritual\EndiumRitualBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */