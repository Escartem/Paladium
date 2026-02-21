package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class DepthState {
  private DepthState() {}
  
  public GlStateManager.BooleanState depthTest = new GlStateManager.BooleanState(2929);
  
  public boolean maskEnabled = true;
  
  public int depthFunc = 513;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$DepthState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */