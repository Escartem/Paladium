package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class StencilState {
  private StencilState() {}
  
  public GlStateManager.StencilFunc field_179078_a = new GlStateManager.StencilFunc(null);
  
  public int field_179076_b = -1;
  
  public int field_179077_c = 7680;
  
  public int field_179074_d = 7680;
  
  public int field_179075_e = 7680;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$StencilState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */