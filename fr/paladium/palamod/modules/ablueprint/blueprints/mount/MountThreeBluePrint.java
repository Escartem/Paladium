package fr.paladium.palamod.modules.ablueprint.blueprints.mount;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.mount.core.blocks.BlocksRegister;
import net.minecraft.init.Blocks;

public class MountThreeBluePrint extends ABluePrint {
  public static final String ID = "mount_three";
  
  public MountThreeBluePrint() {
    super("mount_three");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "XXXXXXX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XHHHHHX", "XXXXXXX" }), Floor.create().addPatterns(new String[] { "FFFFFFF", "F     F", "F     F", "F  C  F", "F     F", "F     F", "FFFFFFF" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('X', BlocksRegister.BARN_FOUNDATION), 
          StructureRecipe.create('F', BlocksRegister.BARN_FENCE), 
          StructureRecipe.create('C', BlocksRegister.BARN_CONTROLLER), 
          StructureRecipe.create('H', Blocks.field_150407_cf) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\mount\MountThreeBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */