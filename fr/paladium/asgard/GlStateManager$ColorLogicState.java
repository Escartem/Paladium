package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class ColorLogicState {
  private ColorLogicState() {}
  
  public GlStateManager.BooleanState colorLogicOp = new GlStateManager.BooleanState(3058);
  
  public int opcode = 5379;
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$ColorLogicState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */