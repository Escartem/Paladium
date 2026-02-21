package fr.paladium.palamod.modules.smeltery.ui.util;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.Node;

public abstract class LoadingNode extends Node {
  protected Color backgroundColor = new Color(63, 63, 76);
  
  protected Color fillColor = new Color(255, 255, 255);
  
  protected double progress;
  
  protected LoadingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public <T extends LoadingNode> T colors(Color backgroundColor, Color fillColor) {
    this.backgroundColor = backgroundColor;
    this.fillColor = fillColor;
    return (T)this;
  }
  
  public <T extends LoadingNode> T backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return (T)this;
  }
  
  public <T extends LoadingNode> T fillColor(Color fillColor) {
    this.fillColor = fillColor;
    return (T)this;
  }
  
  public <T extends LoadingNode> T loadingState(double progress) {
    this.progress = progress;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\u\\util\LoadingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */