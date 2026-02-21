package fr.paladium.palamod.modules.paladium.client;

import net.minecraft.block.Block;

public class StepSoundSlime extends Block.SoundType {
  public StepSoundSlime(String par1Str, float par2, float par3) {
    super(par1Str, par2, par3);
  }
  
  public String func_150495_a() {
    return this.field_150501_a + ".big";
  }
  
  public String func_150498_e() {
    return this.field_150501_a + ".small";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\StepSoundSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */