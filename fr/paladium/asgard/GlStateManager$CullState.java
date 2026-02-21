package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class CullState {
  private CullState() {}
  
  public GlStateManager.BooleanState cullFace = new GlStateManager.BooleanState(2884);
  
  public int mode = 1029;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$CullState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */