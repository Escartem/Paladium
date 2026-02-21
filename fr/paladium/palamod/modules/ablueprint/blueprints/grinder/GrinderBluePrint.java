package fr.paladium.palamod.modules.ablueprint.blueprints.grinder;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Blocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class GrinderBluePrint extends ABluePrint {
  public static final String ID = "grinder";
  
  public GrinderBluePrint() {
    super("grinder");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "FFF", "FCF", "FFF" }), Floor.create().addPatterns(new String[] { "FCF", "CLC", "FBF" }), Floor.create().addPatterns(new String[] { "FFF", "FCF", "FFF" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('F', (Block)PSRegister_Blocks.GRINDER_FRAME_BLOCK), 
          StructureRecipe.create('C', (Block)PSRegister_Blocks.GRINDER_CASING_BLOCK), 
          StructureRecipe.create('B', (Block)PSRegister_Blocks.GRINDER_BLOCK), 
          StructureRecipe.create('L', Blocks.field_150353_l) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\grinder\GrinderBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */