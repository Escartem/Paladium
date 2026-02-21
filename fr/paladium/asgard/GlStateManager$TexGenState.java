package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class TexGenState {
  private TexGenState() {}
  
  public GlStateManager.TexGenCoord s = new GlStateManager.TexGenCoord(8192, 3168);
  
  public GlStateManager.TexGenCoord t = new GlStateManager.TexGenCoord(8193, 3169);
  
  public GlStateManager.TexGenCoord r = new GlStateManager.TexGenCoord(8194, 3170);
  
  public GlStateManager.TexGenCoord q = new GlStateManager.TexGenCoord(8195, 3171);
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$TexGenState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */