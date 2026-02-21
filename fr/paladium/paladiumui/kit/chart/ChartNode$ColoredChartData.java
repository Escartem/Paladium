package fr.paladium.paladiumui.kit.chart;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.ChartNode;
import java.util.Map;
import lombok.NonNull;

public class ColoredChartData extends ChartNode.ChartData {
  private Color color;
  
  public ColoredChartData() {}
  
  public Color getColor() {
    return this.color;
  }
  
  protected ColoredChartData(Map<String, Number> dataMap) {
    super(dataMap);
  }
  
  @NonNull
  public static ColoredChartData create() {
    return new ColoredChartData();
  }
  
  @NonNull
  public static ColoredChartData create(Map<String, Number> dataMap) {
    return new ColoredChartData(dataMap);
  }
  
  @NonNull
  public final <T extends ColoredChartData> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\chart\ChartNode$ColoredChartData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */