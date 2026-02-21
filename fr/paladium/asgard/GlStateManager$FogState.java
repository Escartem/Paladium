package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class FogState {
  private FogState() {}
  
  public GlStateManager.BooleanState fog = new GlStateManager.BooleanState(2912);
  
  public int mode = 2048;
  
  public float density = 1.0F;
  
  public float start = 0.0F;
  
  public float end = 1.0F;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$FogState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */