package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class Color {
  public float red = 1.0F;
  
  public float green = 1.0F;
  
  public float blue = 1.0F;
  
  public float alpha = 1.0F;
  
  public Color() {}
  
  public Color(float redIn, float greenIn, float blueIn, float alphaIn) {
    this.red = redIn;
    this.green = greenIn;
    this.blue = blueIn;
    this.alpha = alphaIn;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$Color.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */