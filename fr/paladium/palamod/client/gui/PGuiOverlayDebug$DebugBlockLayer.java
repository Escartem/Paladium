package fr.paladium.palamod.client.gui;

import net.minecraft.block.Block;

public class DebugBlockLayer {
  private final Block block;
  
  private String prefix = "";
  
  private int[] dimensions = new int[] { 0 };
  
  private int[] biomes = null;
  
  private int minY = 2;
  
  private int maxY = 64;
  
  public DebugBlockLayer(Block block) {
    this.block = block;
  }
  
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
  
  public void setDimensions(int... dimensions) {
    this.dimensions = dimensions;
  }
  
  public void setBiome(int... biomes) {
    this.biomes = biomes;
  }
  
  public void setLayer(int minY, int maxY) {
    this.minY = minY;
    this.maxY = maxY;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\gui\PGuiOverlayDebug$DebugBlockLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */