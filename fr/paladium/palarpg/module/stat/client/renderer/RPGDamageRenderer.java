package fr.paladium.palarpg.module.stat.client.renderer;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.utils.tuple.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class RPGDamageRenderer {
  private static final Random RANDOM = new Random();
  
  private static final List<RPGDamage> DAMAGE = new ArrayList<>();
  
  private static final TextInfo INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE).shadow(Color.BLACK).shadow(0.0F, 2.0F);
  
  private static final Color RED = Color.decode("#FF2E00");
  
  public static void render(float partialTick) {
    DAMAGE.removeIf(damage -> {
          if (damage.getAnimator().getTimeline().isFinished())
            return true; 
          Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(damage.getEntityId());
          return (entity == null) ? false : (!(entity instanceof fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity));
        });
    DAMAGE.forEach(damage -> {
          damage.getAnimator().update();
          Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(damage.getEntityId());
          if (!(entity instanceof fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity))
            return; 
          if (damage.getOrigin() == null)
            damage.setOrigin(entity.field_70165_t, entity.field_70161_v); 
          if (damage.getPosX() == -1.0D)
            damage.setPosX(entity.field_70165_t + entity.field_70130_N / 2.0D * (RANDOM.nextBoolean() ? 1.0D : -1.0D)); 
          if (damage.getPosY() == -1.0D) {
            float y = entity.field_70131_O * 0.7F + 0.7F * RANDOM.nextFloat();
            damage.setPosY(entity.field_70163_u + y);
          } 
          if (damage.getPosZ() == -1.0D)
            damage.setPosZ(entity.field_70161_v); 
          if (damage.getAngle() == -1.0D)
            damage.setAngle(entity, partialTick); 
          String text = "-" + String.format("%.0f", new Object[] { Double.valueOf(damage.getDamage()) });
          double textWidth = INFO.getWidth(text);
          double textHeight = INFO.getHeight(text) * 0.55D;
          GLHelper.pushMatrix();
          GL11.glTranslated(((Double)damage.getOrigin().getFirst()).doubleValue() - RenderManager.field_78725_b, 0.0D, ((Double)damage.getOrigin().getSecond()).doubleValue() - RenderManager.field_78723_d);
          GL11.glRotated(damage.getAngle(), 0.0D, 1.0D, 0.0D);
          GL11.glTranslated(-(((Double)damage.getOrigin().getFirst()).doubleValue() - RenderManager.field_78725_b), 0.0D, -(((Double)damage.getOrigin().getSecond()).doubleValue() - RenderManager.field_78723_d));
          GL11.glTranslated(damage.getPosX() - RenderManager.field_78725_b, damage.getPosY() - RenderManager.field_78726_c, damage.getPosZ() - RenderManager.field_78723_d);
          GL11.glTranslated(-textWidth / 2.0D, -textHeight / 2.0D, 0.0D);
          GL11.glTranslated(textWidth / 2.0D, textHeight / 2.0D, 0.0D);
          GL11.glRotated(-(Minecraft.func_71410_x()).field_71439_g.field_70177_z - damage.getAngle(), 0.0D, 1.0D, 0.0D);
          GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
          GL11.glScaled(0.01D, 0.01D, 1.0D);
          GL11.glTranslated(-textWidth / 2.0D, -textHeight / 2.0D, 0.0D);
          Color color = damage.isCritical() ? RED : Color.WHITE;
          DrawUtils.TEXT.drawText(-2.0D, -13.0D, Text.create(text, INFO.copy().color(color.copyAlpha(damage.getAnimator().getValue())).shadow(Color.BLACK.copyAlpha(damage.getAnimator().getValue()))));
          GLHelper.popMatrix();
        });
  }
  
  public static void appendDamage(RPGDamage damage) {
    DAMAGE.add(damage);
  }
  
  public static class RPGDamage {
    private final int entityId;
    
    private final boolean isCritical;
    
    private final double damage;
    
    private final TweenAnimator animator;
    
    private final long startTime;
    
    private Tuple<Double, Double> origin;
    
    public int getEntityId() {
      return this.entityId;
    }
    
    public boolean isCritical() {
      return this.isCritical;
    }
    
    public double getDamage() {
      return this.damage;
    }
    
    public TweenAnimator getAnimator() {
      return this.animator;
    }
    
    public long getStartTime() {
      return this.startTime;
    }
    
    public Tuple<Double, Double> getOrigin() {
      return this.origin;
    }
    
    private double posX = -1.0D;
    
    public double getPosX() {
      return this.posX;
    }
    
    private double posY = -1.0D;
    
    public double getPosY() {
      return this.posY;
    }
    
    private double posZ = -1.0D;
    
    public double getPosZ() {
      return this.posZ;
    }
    
    private double angle = -1.0D;
    
    public double getAngle() {
      return this.angle;
    }
    
    public RPGDamage(int entityId, boolean isCritical, double damage) {
      this.entityId = entityId;
      this.isCritical = isCritical;
      this.damage = damage;
      this.animator = TweenAnimator.create(1.0F);
      Timeline timeline = Timeline.createSequence();
      timeline.pushPause(1000.0F);
      timeline.push(Tween.to(this.animator, 0, 500.0F).target(0.0F).ease((TweenEquation)TweenEquations.LINEAR));
      this.animator.setTimeline(timeline);
      this.animator.start();
      this.startTime = System.currentTimeMillis();
    }
    
    public void setPosX(double posX) {
      this.posX = posX;
    }
    
    public void setPosY(double posY) {
      this.posY = posY;
    }
    
    public void setPosZ(double posZ) {
      this.posZ = posZ;
    }
    
    public void setOrigin(double x, double z) {
      this.origin = new Tuple(Double.valueOf(x), Double.valueOf(z));
    }
    
    public void setAngle(Entity entity, float partialTick) {
      EntityLivingBase entityLivingBase = (Minecraft.func_71410_x()).field_71451_h;
      double vx = ((Entity)entityLivingBase).field_70169_q + (((Entity)entityLivingBase).field_70165_t - ((Entity)entityLivingBase).field_70169_q) * partialTick;
      double vz = ((Entity)entityLivingBase).field_70166_s + (((Entity)entityLivingBase).field_70161_v - ((Entity)entityLivingBase).field_70166_s) * partialTick;
      double ex = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTick;
      double ez = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTick;
      double dx = vx - ex;
      double dz = vz - ez;
      this.angle = -Math.atan2(dz, dx) * 180.0D / Math.PI - 90.0D;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\client\renderer\RPGDamageRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */