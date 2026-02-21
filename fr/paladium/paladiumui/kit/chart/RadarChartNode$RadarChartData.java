package fr.paladium.paladiumui.kit.chart;

import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.RadarChartNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class RadarChartData extends RadarChartNode.RadarChartData {
  private Resource icon;
  
  public Resource getIcon() {
    return this.icon;
  }
  
  protected RadarChartData(@NonNull String label, Resource icon) {
    this(label, icon, null);
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
  }
  
  protected RadarChartData(@NonNull String label, Resource icon, Number value) {
    super(label, value);
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    this.icon = icon;
  }
  
  @NonNull
  public static RadarChartData create(@NonNull String label, ResourceLocation icon) {
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    return new RadarChartData(label, (icon == null) ? null : Resource.of(icon));
  }
  
  @NonNull
  public static RadarChartData create(@NonNull String label, ResourceLocation icon, Number value) {
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    return new RadarChartData(label, (icon == null) ? null : Resource.of(icon), value);
  }
  
  @NonNull
  public static RadarChartData create(@NonNull String label, Resource icon) {
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    return new RadarChartData(label, icon);
  }
  
  @NonNull
  public static RadarChartData create(@NonNull String label, Resource icon, Number value) {
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    return new RadarChartData(label, icon, value);
  }
  
  public final <T extends RadarChartData> T icon(ResourceLocation icon) {
    this.icon = Resource.of(icon);
    return (T)this;
  }
  
  public final <T extends RadarChartData> T icon(Resource icon) {
    this.icon = icon;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\chart\RadarChartNode$RadarChartData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */