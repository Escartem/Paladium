package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class PolygonOffsetState {
  private PolygonOffsetState() {}
  
  public GlStateManager.BooleanState polygonOffsetFill = new GlStateManager.BooleanState(32823);
  
  public GlStateManager.BooleanState polygonOffsetLine = new GlStateManager.BooleanState(10754);
  
  public float factor = 0.0F;
  
  public float units = 0.0F;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$PolygonOffsetState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */