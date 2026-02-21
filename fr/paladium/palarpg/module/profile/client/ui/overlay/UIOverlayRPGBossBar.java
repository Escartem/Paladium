package fr.paladium.palarpg.module.profile.client.ui.overlay;

import com.google.common.util.concurrent.AtomicDouble;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@UIData(background = false, anchorX = Align.CENTER, anchorY = Align.START)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, type = RenderGameOverlayEvent.ElementType.BOSSHEALTH))
@UIDataGuiscale(active = true)
public class UIOverlayRPGBossBar extends UI {
  private final ListSignal<RPGMobEntity> bosses = new ListSignal(new ArrayList());
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 400.0D)
      .body(node -> {
          if (this.bosses.isEmpty())
            return; 
          TextNode.create(node.dw(2.0D), 10.0D).text(Text.create("boss", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 36.0F, Color.WHITE)).modifier(TextModifier.CAPITALIZE)).anchorX(Align.CENTER).attach(node);
        }).watch((Signal)this.bosses, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    AtomicDouble yOffset = new AtomicDouble(90.0D);
    double barWidth = 639.0D;
    double barHeight = 13.0D;
    double shadow = 4.0D;
    ((List)this.bosses.getOrDefault()).forEach(boss -> {
          float mobHealth = boss.func_110143_aJ() / boss.func_110138_aP();
          DrawUtils.SHAPE.drawRect(636.5D, yOffset.get(), 647.0D, 21.0D, Color.decode("#0E0E0E"));
          DrawUtils.SHAPE.drawRect(640.5D, yOffset.get() + 4.0D, 639.0D, 13.0D, Color.decode("#282828"));
          mask(640.5D, yOffset.get() + 4.0D, 639.0D * mobHealth, 13.0D, ());
          yOffset.addAndGet(31.0D);
        });
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    List<Entity> loadedEntities = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_72996_f;
    loadedEntities.forEach(entity -> {
          if (entity instanceof RPGMobEntity) {
            RPGMobEntity boss = (RPGMobEntity)entity;
            if (boss.getRpgData().isBossBar() && !((List)this.bosses.getOrDefault()).contains(boss))
              this.bosses.add(boss); 
          } 
        });
    if (((List)this.bosses.getOrDefault()).removeIf(entity -> !entity.func_70089_S()))
      this.bosses.publish(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\overlay\UIOverlayRPGBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */