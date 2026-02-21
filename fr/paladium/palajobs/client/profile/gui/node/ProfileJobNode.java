package fr.paladium.palajobs.client.profile.gui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Vector2d;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ProfileJobNode extends Node {
  private static final Map<JobType, Resource> TEXTURES = new HashMap<>();
  
  static {
    TEXTURES.put(JobType.ALCHEMIST, Resource.of(new ResourceLocation("palajobs", "textures/gui/icons/alchimiste.png")));
    TEXTURES.put(JobType.FARMER, Resource.of(new ResourceLocation("palajobs", "textures/gui/icons/farmer.png")));
    TEXTURES.put(JobType.HUNTER, Resource.of(new ResourceLocation("palajobs", "textures/gui/icons/hunter.png")));
    TEXTURES.put(JobType.MINER, Resource.of(new ResourceLocation("palajobs", "textures/gui/icons/miner.png")));
  }
  
  private static final Map<JobType, Color> COLORS = new HashMap<>();
  
  private final TweenAnimator levelAnimator;
  
  private final TweenAnimator experienceAnimator;
  
  private Resource texture;
  
  private Color color;
  
  static {
    COLORS.put(JobType.ALCHEMIST, new Color(252, 100, 201));
    COLORS.put(JobType.FARMER, new Color(255, 209, 1));
    COLORS.put(JobType.HUNTER, new Color(47, 103, 255));
    COLORS.put(JobType.MINER, new Color(255, 47, 47));
  }
  
  protected ProfileJobNode(double x, double y, double size) {
    super(x, y, size, size * 1.041825095057034D);
    this.levelAnimator = TweenAnimator.create();
    this.experienceAnimator = TweenAnimator.create();
  }
  
  public static ProfileJobNode create(double x, double y, double size) {
    return new ProfileJobNode(x, y, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.levelAnimator.update();
    this.experienceAnimator.update();
    DrawUtils.SHAPE.drawPolygon(new Color(50, 50, 50), new Vector2d[] { new Vector2d(
            getX() + getWidth() * 0.5D, getY() + getHeight() * 0.15000000596046448D), new Vector2d(
            getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.3499999940395355D), new Vector2d(
            getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.699999988079071D), new Vector2d(
            getX() + getWidth() * 0.5D, getY() + getHeight() * 0.8999999761581421D), new Vector2d(
            getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.699999988079071D), new Vector2d(
            getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.3499999940395355D) });
    float p = this.experienceAnimator.getValue();
    float p1 = Math.min(1.0F, p * 2.0F);
    float p2 = Math.min(1.0F, Math.max(0.0F, (p - 0.5F) * 2.0F));
    GL11.glPushMatrix();
    getUi().mask(getX() + dw(2.0D), getY() + getHeight() * 0.15000000596046448D, dw(2.0D), getHeight() * 0.75D * p1, () -> DrawUtils.SHAPE.drawPolygon(this.color, new Vector2d[] { new Vector2d(getX() + getWidth() * 0.5D, getY() + getHeight() * 0.15000000596046448D), new Vector2d(getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.3499999940395355D), new Vector2d(getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.699999988079071D), new Vector2d(getX() + getWidth() * 0.5D, getY() + getHeight() * 0.8999999761581421D), new Vector2d(getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.699999988079071D), new Vector2d(getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.3499999940395355D) }));
    getUi().mask(getX(), getY() + getHeight() * 0.15000000596046448D + getHeight() * 0.75D * (1.0F - p2), dw(2.0D), getHeight() * 0.75D * p2, () -> DrawUtils.SHAPE.drawPolygon(this.color, new Vector2d[] { new Vector2d(getX() + getWidth() * 0.5D, getY() + getHeight() * 0.15000000596046448D), new Vector2d(getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.3499999940395355D), new Vector2d(getX() + getWidth() * 0.15000000596046448D, getY() + getHeight() * 0.699999988079071D), new Vector2d(getX() + getWidth() * 0.5D, getY() + getHeight() * 0.8999999761581421D), new Vector2d(getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.699999988079071D), new Vector2d(getX() + getWidth() * 0.8500000238418579D, getY() + getHeight() * 0.3499999940395355D) }));
    GL11.glPopMatrix();
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), this.texture);
    DrawUtils.SHAPE.drawRect(getX() + getWidth() * 0.30000001192092896D, getY() + getHeight() * 0.7400000095367432D, getWidth() * 0.4000000059604645D, getHeight() * 0.20999999344348907D, this.color);
    DrawUtils.SHAPE.drawRect(getX() + getWidth() * 0.30000001192092896D, getY() + getHeight() * 0.949999988079071D, getWidth() * 0.4000000059604645D, getHeight() * 0.029999999329447746D, this.color.darker(0.5F));
    int fontSize = (int)(getWidth() * 0.15000000596046448D);
    String level = String.valueOf((int)this.levelAnimator.getValue());
    DrawUtils.TEXT.drawText(getX() + dw(2.0D), getY() + getHeight() * 0.7799999713897705D - TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, (fontSize / 4), Color.WHITE).dh(2.0D), Text.create("LVL.", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, (fontSize / 4), Color.WHITE), Align.CENTER));
    DrawUtils.TEXT.drawText(getX() + getWidth() * 0.49000000953674316D, getY() + getHeight() * 0.8700000047683716D - TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, fontSize, Color.WHITE).dh(2.0D), Text.create(level, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, fontSize, Color.WHITE), Align.CENTER));
  }
  
  public final ProfileJobNode job(JobType type, int level, double experience) {
    double tmpExperience = experience - JobExpUtils.getNeededXpForLvl(level);
    double tmpNextLevelExperience = (JobExpUtils.getNeededXpForLvl(level + 1) - JobExpUtils.getNeededXpForLvl(level));
    if (tmpExperience > tmpNextLevelExperience)
      tmpExperience = tmpNextLevelExperience; 
    this.levelAnimator.sequence(500.0F, level, (TweenEquation)TweenEquations.SINE_INOUT).start();
    this.experienceAnimator.sequence(500.0F, (level == Integer.MAX_VALUE) ? 1.0F : ((float)tmpExperience / (float)tmpNextLevelExperience), (TweenEquation)TweenEquations.SINE_INOUT).start();
    this.texture = TEXTURES.get(type);
    this.color = COLORS.get(type);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\profile\gui\node\ProfileJobNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */