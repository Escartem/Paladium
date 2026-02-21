package fr.paladium.palamod.modules.ablueprint.blueprints.mount;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.recipe.StructureRecipe;
import fr.paladium.mount.core.blocks.BlocksRegister;
import net.minecraft.init.Blocks;

public class MountTwoBluePrint extends ABluePrint {
  public static final String ID = "mount_two";
  
  public MountTwoBluePrint() {
    super("mount_two");
  }
  
  protected void init() {
    addFloors(new Floor[] { Floor.create().addPatterns(new String[] { "XXXXXX", "XHHHHX", "XHHHHX", "XHHHHX", "XHHHHX", "XXXXXX" }), Floor.create().addPatterns(new String[] { "FFFFFF", "F    F", "F C  F", "F    F", "F    F", "FFFFFF" }) });
    addRecipes(new StructureRecipe[] { StructureRecipe.create('X', BlocksRegister.BARN_FOUNDATION), 
          StructureRecipe.create('F', BlocksRegister.BARN_FENCE), 
          StructureRecipe.create('C', BlocksRegister.BARN_CONTROLLER), 
          StructureRecipe.create('H', Blocks.field_150407_cf) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\blueprints\mount\MountTwoBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */