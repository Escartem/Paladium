package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class ClearState {
  private ClearState() {}
  
  public double depth = 1.0D;
  
  public GlStateManager.Color color = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
  
  public int field_179204_c = 0;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$ClearState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */