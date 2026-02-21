package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class TextureState {
  private TextureState() {}
  
  public GlStateManager.BooleanState texture2DState = new GlStateManager.BooleanState(3553);
  
  public int textureName = 0;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$TextureState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */