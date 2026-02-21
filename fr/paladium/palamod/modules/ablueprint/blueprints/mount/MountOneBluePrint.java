package fr.paladium.palamod.modules.ablueprint.blueprints.mount;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.mount.core.blocks.BlocksRegister;
import net.minecraft.init.Blocks;

public class MountOneBluePrint extends ABluePrint {
  public static final String ID = "mount_one";
  
  public MountOneBluePrint() {
    super("mount_one");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "XXXXX", "XHHHX", "XHHHX", "XHHHX", "XXXXX" }), Floor.create().addPatterns(new String[] { "FFFFF", "F   F", "F C F", "F   F", "FFFFF" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('X', BlocksRegister.BARN_FOUNDATION), 
          StructureRecipe.create('F', BlocksRegister.BARN_FENCE), 
          StructureRecipe.create('C', BlocksRegister.BARN_CONTROLLER), 
          StructureRecipe.create('H', Blocks.field_150407_cf) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\mount\MountOneBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */