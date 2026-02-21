package fr.paladium.palamod.modules.ablueprint.blueprints.remover;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import net.minecraft.block.Block;

public class HomeRemoverFiveBluePrint extends ABluePrint {
  public static final String ID = "home_remover_five";
  
  public HomeRemoverFiveBluePrint() {
    super("home_remover_five");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { 
              "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", "QQQQQQQQQQQ", 
              "QQQQQQQQQQQ" }), Floor.create().addPatterns(new String[] { 
              "           ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", " QQQQQQQQQ ", 
              "           " }), Floor.create().addPatterns(new String[] { 
              "           ", "           ", "  QQQQQQQ  ", "  QQQQQQQ  ", "  QQQQQQQ  ", "  QQQQQQQ  ", "  QQQQQQQ  ", "  QQQQQQQ  ", "  QQQQQQQ  ", "           ", 
              "           " }), Floor.create().addPatterns(new String[] { 
              "           ", "           ", "           ", "   QQQQQ   ", "   QQQQQ   ", "   QQQQQ   ", "   QQQQQ   ", "   QQQQQ   ", "           ", "           ", 
              "           " }), Floor.create().addPatterns(new String[] { 
              "           ", "           ", "           ", "           ", "    QQQ    ", "    QQQ    ", "    QQQ    ", "           ", "           ", "           ", 
              "           " }), Floor.create().addPatterns(new String[] { 
              "           ", "           ", "           ", "           ", "           ", "     H     ", "           ", "           ", "           ", "           ", 
              "           " }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('Q', PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          StructureRecipe.create('H', (Block)BlocksRegister.HOME_FINDER) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\remover\HomeRemoverFiveBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */