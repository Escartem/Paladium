package fr.paladium.asgard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
class BooleanState {
  private final int capability;
  
  private boolean currentState = false;
  
  public BooleanState(int capabilityIn) {
    this.capability = capabilityIn;
  }
  
  public void setDisabled() {
    setState(false);
  }
  
  public void setEnabled() {
    setState(true);
  }
  
  public void setState(boolean state) {
    if (state != this.currentState) {
      this.currentState = state;
      if (state) {
        GL11.glEnable(this.capability);
      } else {
        GL11.glDisable(this.capability);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\GlStateManager$BooleanState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */