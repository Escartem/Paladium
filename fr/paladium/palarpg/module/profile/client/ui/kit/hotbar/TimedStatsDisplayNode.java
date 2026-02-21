package fr.paladium.palarpg.module.profile.client.ui.kit.hotbar;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palarpg.module.stat.client.constant.RPGStatConstant;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class TimedStatsDisplayNode extends Node {
  private EnumStats type;
  
  private List<TimedStatCapabilityMutator<?>> timedStatsMutator;
  
  private double percent;
  
  private TimedStatsDisplayNode(double x, double y) {
    super(x, y, 36.2D, 41.8D);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.timedStatsMutator == null || this.timedStatsMutator.isEmpty() || this.type == null)
      return; 
    for (int i = 0; i < 3 && 
      i < this.timedStatsMutator.size(); i++) {
      TimedStatCapabilityMutator<?> timedStatMutator = this.timedStatsMutator.get(i);
      boolean isLast = (i == ((this.timedStatsMutator.size() > 2) ? 2 : (this.timedStatsMutator.size() - 1)));
      double offsetY = i * -5.0D;
      DrawUtils.RESOURCE.drawImage(getX(), getY() + offsetY, w(), h(), Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/timed_mutator_empty.png")).linear());
      if (isLast) {
        this.percent = (timedStatMutator.getTick() / timedStatMutator.getTotalTick());
        double percentRight = (Math.max(this.percent, 0.5D) - 0.5D) * 2.0D * 0.8700000047683716D + 0.07000000029802322D;
        double percentLeft = Math.min(this.percent, 0.5D) * 2.0D * 0.8700000047683716D + 0.07000000029802322D;
        getUi().mask(getX() + dw(2.0D), getY() + offsetY + mh(1.0D - percentRight), dw(2.0D), mh(percentRight), () -> {
              GLHelper.color(RPGStatConstant.getColor(this.type));
              DrawUtils.RESOURCE.drawImage(getX(), getY() + offsetY, w(), h(), Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/timed_mutator_full.png")).linear());
              GLHelper.popColor();
            });
        getUi().mask(getX(), getY() + offsetY, dw(2.0D), h() * percentLeft, () -> {
              GLHelper.color(RPGStatConstant.getColor(this.type));
              DrawUtils.RESOURCE.drawImage(getX(), getY() + offsetY, w(), h(), Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/timed_mutator_full.png")).linear());
              GLHelper.popColor();
            });
      } else {
        GLHelper.color(RPGStatConstant.getColor(this.type));
        DrawUtils.RESOURCE.drawImage(getX(), getY() + offsetY, w(), h(), Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/timed_mutator_full.png")).linear());
        GLHelper.popColor();
      } 
      if (isLast)
        DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - 7.5D, getY() + offsetY + dh(2.0D) - 7.5D + 1.2D, 15.0D, 15.0D, RPGStatConstant.getIcon(this.type)); 
    } 
    if (this.timedStatsMutator.size() > 3) {
      DrawUtils.RESOURCE.drawImage(getX() + 15.0D, getY() - 10.0D, 16.0D, 16.0D, Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/timed_mutator_stack.png")).linear());
      DrawUtils.TEXT.drawText(getX() + 15.0D + 7.5D, getY() - 10.0D + 8.0D, Text.create("+" + (this.timedStatsMutator.size() - 3), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 6.0F, Color.WHITE), Align.CENTER, Align.CENTER));
    } 
  }
  
  public static TimedStatsDisplayNode create(double x, double y) {
    return new TimedStatsDisplayNode(x, y);
  }
  
  public TimedStatsDisplayNode type(EnumStats type) {
    this.type = type;
    return this;
  }
  
  public TimedStatsDisplayNode timedStatsMutator(List<TimedStatCapabilityMutator<?>> timedStatsMutator) {
    this.timedStatsMutator = new ArrayList<>(timedStatsMutator);
    Collections.sort(this.timedStatsMutator, (o1, o2) -> Integer.compare(o2.getTick(), o1.getTick()));
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\hotbar\TimedStatsDisplayNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */