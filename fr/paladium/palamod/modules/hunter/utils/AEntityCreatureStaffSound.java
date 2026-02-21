package fr.paladium.palamod.modules.hunter.utils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class AEntityCreatureStaffSound extends EntityCreature {
  public AEntityCreatureStaffSound(World world) {
    super(world);
  }
  
  public String getSoundName() {
    return "";
  }
  
  protected String func_70673_aS() {
    if (func_94056_bM())
      return "palamod:mob." + getSoundName() + "." + 
        func_70005_c_().toLowerCase() + ".death"; 
    return super.func_70673_aS();
  }
  
  protected String func_70621_aR() {
    if (func_94056_bM())
      return "palamod:mob." + getSoundName() + "." + 
        func_70005_c_().toLowerCase() + ".hurt"; 
    return super.func_70621_aR();
  }
  
  protected String func_70639_aQ() {
    if (func_94056_bM())
      return "palamod:mob." + getSoundName() + "." + 
        func_70005_c_().toLowerCase() + ".living"; 
    return super.func_70639_aQ();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunte\\utils\AEntityCreatureStaffSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */