package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class ColorMaterialState {
  private ColorMaterialState() {}
  
  public GlStateManager.BooleanState colorMaterial = new GlStateManager.BooleanState(2903);
  
  public int face = 1032;
  
  public int mode = 5634;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$ColorMaterialState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */