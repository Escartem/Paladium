package fr.paladium.palawarzoneevent.module.capture.client.ui.kit;

import fr.paladium.palawarzoneevent.module.capture.client.ui.CaptureProgressDirection;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.util.ResourceLocation;

public class CaptureProgressNode extends Node {
  private static final Resource BAR_BACKGROUND = Resource.of(new ResourceLocation("palawarzoneevent", "textures/gui/capture/capture_background.png"));
  
  private static final Resource CAPTURE_INCREASE = Resource.of(new ResourceLocation("palawarzoneevent", "textures/gui/capture/capture_increase.png"));
  
  private static final Resource CAPTURE_DECREASE = Resource.of(new ResourceLocation("palawarzoneevent", "textures/gui/capture/capture_decrease.png"));
  
  private static final Resource CAPTURE_INTERUPTED = Resource.of(new ResourceLocation("palawarzoneevent", "textures/gui/capture/interrupted.png"));
  
  private static final Resource CAPTURED = Resource.of(new ResourceLocation("palawarzoneevent", "textures/gui/capture/captured.png"));
  
  private CaptureProgressDirection state = CaptureProgressDirection.DECREASING;
  
  private final Resource background = BAR_BACKGROUND;
  
  private Resource foreground = CAPTURE_INCREASE;
  
  private TweenAnimator animator;
  
  private float currentProgress = 0.0F;
  
  protected CaptureProgressNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static CaptureProgressNode create(double x, double y, double width, double height) {
    return new CaptureProgressNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.animator != null)
      this.animator.update(); 
    if (this.state == CaptureProgressDirection.CAPTURED)
      this.foreground = CAPTURED; 
    if (this.state == CaptureProgressDirection.DECREASING)
      this.foreground = CAPTURE_DECREASE; 
    if (this.state == CaptureProgressDirection.INCREASING)
      this.foreground = CAPTURE_INCREASE; 
    if (this.state == CaptureProgressDirection.INTERUPTED)
      this.foreground = CAPTURE_INTERUPTED; 
    DrawUtils.RESOURCE.drawImage(getX(), getY(), w(), h(), this.background);
    getUi().mask(getX(), getY(), w() * ((this.animator == null) ? this.currentProgress : this.animator.getValue()), h(), () -> DrawUtils.RESOURCE.drawImage(getX(), getY(), w(), h(), this.foreground));
  }
  
  public CaptureProgressNode progress(float progress) {
    this.currentProgress = progress;
    return this;
  }
  
  public CaptureProgressNode setState(CaptureProgressDirection progressState) {
    if (this.state == progressState)
      return this; 
    this.state = progressState;
    if ((this.state == CaptureProgressDirection.INTERUPTED || this.state == CaptureProgressDirection.CAPTURED) && 
      this.animator != null)
      this.animator = null; 
    if (this.state == CaptureProgressDirection.DECREASING)
      this.animator = TweenAnimator.create(this.currentProgress).sequence(this.currentProgress * 100000.0F, 0.0F).start(); 
    if (this.state == CaptureProgressDirection.INCREASING)
      this.animator = TweenAnimator.create(this.currentProgress).sequence((1.0F - this.currentProgress) * 100000.0F, 1.0F).start(); 
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\clien\\ui\kit\CaptureProgressNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */