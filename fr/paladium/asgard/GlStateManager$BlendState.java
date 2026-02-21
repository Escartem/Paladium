package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class BlendState {
  private BlendState() {}
  
  public GlStateManager.BooleanState blend = new GlStateManager.BooleanState(3042);
  
  public int srcFactor = 1;
  
  public int dstFactor = 0;
  
  public int srcFactorAlpha = 1;
  
  public int dstFactorAlpha = 0;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$BlendState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */