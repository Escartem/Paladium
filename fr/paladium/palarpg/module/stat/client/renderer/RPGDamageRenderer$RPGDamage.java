package fr.paladium.palarpg.module.stat.client.renderer;

import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.utils.tuple.Tuple;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RPGDamage {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\client\renderer\RPGDamageRenderer$RPGDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */