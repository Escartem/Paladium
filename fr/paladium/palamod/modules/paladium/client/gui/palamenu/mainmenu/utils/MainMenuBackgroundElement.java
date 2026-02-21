package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils;

import net.minecraft.util.ResourceLocation;

public class MainMenuBackgroundElement {
  private final ResourceLocation texture;
  
  private final long end;
  
  public ResourceLocation getTexture() {
    return this.texture;
  }
  
  public long getEnd() {
    return this.end;
  }
  
  public MainMenuBackgroundElement(ResourceLocation texture) {
    this.texture = texture;
    this.end = System.currentTimeMillis() + 300L;
  }
  
  public float getAnimationValue() {
    long duration = this.end - System.currentTimeMillis();
    if (duration <= 0L)
      return 1.0F; 
    return 1.0F - (float)duration / 300.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmen\\utils\MainMenuBackgroundElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */