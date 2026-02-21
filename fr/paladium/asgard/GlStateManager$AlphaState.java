package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class AlphaState {
  private AlphaState() {}
  
  public GlStateManager.BooleanState alphaTest = new GlStateManager.BooleanState(3008);
  
  public int func = 519;
  
  public float ref = -1.0F;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$AlphaState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */