package fr.paladium.palaautomation.common.blueprint;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palaautomation.common.registry.AutomationBlockRegistry;

public class AutoCrafterBlueprint extends ABluePrint {
  public static final String ID = "auto_crafter";
  
  public AutoCrafterBlueprint() {
    super("auto_crafter");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "RRR", "III", "III" }), Floor.create().addPatterns(new String[] { "RRR", "III", "IAI" }), Floor.create().addPatterns(new String[] { "RRR", "III", "III" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('R', AutomationBlockRegistry.RECEIVER), 
          StructureRecipe.create('A', AutomationBlockRegistry.CRAFTER), 
          StructureRecipe.create('I', AutomationBlockRegistry.INJECTOR) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\blueprint\AutoCrafterBlueprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */