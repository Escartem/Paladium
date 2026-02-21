package fr.paladium.palawarzoneevent.module.capture.client.ui;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palawarzoneevent.module.capture.client.ui.kit.CaptureProgressNode;
import fr.paladium.palawarzoneevent.module.capture.common.manager.CaptureEventManager;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

@UIData(active = true, anchorY = Align.START, background = false)
@UIDataGuiscale(active = true)
@UIDataOverlay(active = true)
public class UIOverlayCapture extends UI {
  private static final TextInfo ZONE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, Color.WHITE).shadow().shadow(0.0F, 2.0F);
  
  private final StringSignal captureZoneSignal;
  
  private double lastProgress = 0.0D;
  
  private final DoubleSignal progressSignal = new DoubleSignal(0.0D);
  
  private final Signal<CaptureProgressDirection> progressDirectionSignal = new Signal(CaptureProgressDirection.DECREASING);
  
  public UIOverlayCapture() {
    this.captureZoneSignal = new StringSignal(null);
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .onInit(container -> {
          if (this.captureZoneSignal.getOrDefault() == null)
            return; 
          TextNode.create(606.0D, 50.0D).text(Text.create("Zone: " + (String)this.captureZoneSignal.getOrDefault(), ZONE_INFO).modifier(TextModifier.UPPER_CASE)).attach(container);
          ((TextNode)TextNode.create(1314.0D, 50.0D).text(Text.create("0%", ZONE_INFO).modifier(TextModifier.UPPER_CASE)).anchorX(Align.END).onDraw(())).attach(container);
          ((CaptureProgressNode)CaptureProgressNode.create(606.0D, 100.0D, 708.0D, 44.0D).onInit(())).watch(this.progressDirectionSignal, new WatchProperty[] { WatchProperty.RELOAD }).watch((Signal)this.progressSignal, new WatchProperty[] { WatchProperty.RELOAD }).attach(container);
          ((TextNode)TextNode.create(1314.0D - ZONE_INFO.getWidth("100%") - 20.0D, 50.0D).text(Text.create("Capture", ZONE_INFO.copy()).modifier(TextModifier.UPPER_CASE)).anchorX(Align.END).onInit(())).watch(this.progressDirectionSignal, new WatchProperty[] { WatchProperty.RELOAD }).watch((Signal)this.progressSignal, new WatchProperty[] { WatchProperty.RELOAD }).attach(container);
        }).watch((Signal)this.captureZoneSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (CaptureEventManager.inst().getPoints().isEmpty()) {
      this.captureZoneSignal.set(null);
      return;
    } 
    boolean isInAnyZone = false;
    for (int i = 0; i < CaptureEventManager.inst().getPoints().size(); i++) {
      CapturePoint point = CaptureEventManager.inst().getPoints().get(i);
      DoubleLocation location = point.getLocation();
      if (location.distance((Entity)(Minecraft.func_71410_x()).field_71439_g) <= 20.0D + point.getRadius()) {
        this.captureZoneSignal.set(point.getName());
        isInAnyZone = true;
        break;
      } 
    } 
    if (!isInAnyZone)
      this.captureZoneSignal.set(null); 
    if (this.captureZoneSignal.getOrDefault() != null) {
      Optional<CapturePoint> optCp = CaptureEventManager.inst().getCapturePointByName((String)this.captureZoneSignal.getOrDefault());
      if (optCp.isPresent()) {
        CapturePoint cp = optCp.get();
        this.progressSignal.set(Double.valueOf(cp.getCapturingProgress()));
        if (cp.isInterrupted()) {
          this.progressDirectionSignal.set(CaptureProgressDirection.INTERUPTED);
          return;
        } 
        if (cp.getCapturingProgress() == 100.0D) {
          this.progressDirectionSignal.set(CaptureProgressDirection.CAPTURED);
        } else {
          if (cp.getCapturingProgress() > this.lastProgress)
            this.progressDirectionSignal.set(CaptureProgressDirection.INCREASING); 
          if (cp.getCapturingProgress() < this.lastProgress)
            this.progressDirectionSignal.set(CaptureProgressDirection.DECREASING); 
        } 
        this.lastProgress = cp.getCapturingProgress();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\clien\\ui\UIOverlayCapture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */