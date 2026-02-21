package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class TexGenCoord {
  public GlStateManager.BooleanState textureGen;
  
  public int coord;
  
  public int param = -1;
  
  public TexGenCoord(int p_i46254_1_, int p_i46254_2_) {
    this.coord = p_i46254_1_;
    this.textureGen = new GlStateManager.BooleanState(p_i46254_2_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$TexGenCoord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */