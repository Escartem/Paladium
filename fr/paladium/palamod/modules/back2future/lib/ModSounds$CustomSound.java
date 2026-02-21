package fr.paladium.palamod.modules.back2future.lib;

import net.minecraft.block.Block;

final class CustomSound extends Block.SoundType {
  private final boolean useDefaults;
  
  public CustomSound(String name, float volume, float pitch, boolean useDefaults) {
    super(name, volume, pitch);
    this.useDefaults = useDefaults;
  }
  
  public CustomSound(String name) {
    this(name, 1.0F, 1.0F, false);
  }
  
  public String func_150495_a() {
    return this.useDefaults ? super.func_150495_a() : this.field_150501_a;
  }
  
  public String func_150498_e() {
    return this.useDefaults ? super.func_150498_e() : this.field_150501_a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\lib\ModSounds$CustomSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */