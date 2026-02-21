package fr.paladium.palajobs.core.entity.gecko;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.entity.gecko.animation.Animation;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import fr.paladium.palajobs.core.entity.gecko.network.SCPacketEntityPlayAnimation;
import java.util.EnumMap;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class AnimatedEntityLiving extends EntityLiving implements IAnimatedEntity, IAnimatable, IAnimationTickable {
  private final AnimationFactory animationFactory = new AnimationFactory(this);
  
  private final EnumMap<AnimationType, String[]> defaultAnimations = (EnumMap)new EnumMap<>(AnimationType.class);
  
  private Animation<Entity> currentAnimation;
  
  private boolean deathAnimation;
  
  public AnimatedEntityLiving(World world) {
    super(world);
  }
  
  public AnimatedEntityLiving(World world, double x, double y, double z) {
    this(world);
    func_70634_a(x, y, z);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(31, Byte.valueOf((byte)0));
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70170_p.field_72995_K)
      if (!isMovingAnimation() && func_70661_as().func_75505_d() != null && !func_70661_as().func_75505_d().func_75879_b()) {
        this.field_70180_af.func_75692_b(31, Byte.valueOf((byte)1));
      } else if (isMovingAnimation() && func_70661_as().func_75505_d() != null && !func_70661_as().func_75505_d().func_75879_b()) {
        this.field_70180_af.func_75692_b(31, Byte.valueOf((byte)0));
      }  
    if (this.currentAnimation == null)
      return; 
    if (System.currentTimeMillis() - this.currentAnimation.getStartedAt() > this.currentAnimation.getDuration()) {
      Consumer<Entity> callback = this.currentAnimation.getCallback();
      this.currentAnimation = null;
      if (callback != null)
        callback.accept(this); 
    } 
  }
  
  public void func_70609_aI() {
    if (!this.defaultAnimations.containsKey(AnimationType.DEATH)) {
      super.func_70609_aI();
      return;
    } 
    if (!this.deathAnimation) {
      playAnimation(getDefaultAnimation(AnimationType.DEATH), 1500L, false).setCallback(e -> func_70106_y());
      this.deathAnimation = true;
    } 
  }
  
  public void func_70665_d(DamageSource source, float value) {
    if (!this.field_70170_p.field_72995_K && value > 0.0F && !func_85032_ar() && this.defaultAnimations.containsKey(AnimationType.HURT) && !isAnimated() && !isMovingAnimation() && !isDeathAnimation())
      playAnimation(getDefaultAnimation(AnimationType.HURT), 500L, true); 
    super.func_70665_d(source, value);
  }
  
  public boolean isDeathAnimation() {
    return this.deathAnimation;
  }
  
  public boolean isMovingAnimation() {
    return (this.field_70180_af.func_75683_a(31) == 1);
  }
  
  public AnimatedEntityLiving setDefaultAnimation(AnimationType type, String... name) {
    this.defaultAnimations.put(type, name);
    return this;
  }
  
  public boolean hasDefaultAnimation(AnimationType type) {
    return this.defaultAnimations.containsKey(type);
  }
  
  public String getDefaultAnimation(AnimationType type) {
    return ((String[])this.defaultAnimations.get(type))[this.field_70170_p.field_73012_v.nextInt(((String[])this.defaultAnimations.get(type)).length)];
  }
  
  public Animation<Entity> playAnimation(String animation, long duration, boolean sendToClient) {
    this.currentAnimation = new Animation(animation, duration);
    if (sendToClient && !this.field_70170_p.field_72995_K) {
      EntityTracker tracker = ((WorldServer)this.field_70170_p).func_73039_n();
      SCPacketEntityPlayAnimation packet = new SCPacketEntityPlayAnimation(func_145782_y(), animation, duration);
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)this))
        PalaJobs.proxy.getNetwork().sendTo((IMessage)packet, (EntityPlayerMP)entityPlayer); 
    } 
    return this.currentAnimation;
  }
  
  public Animation<Entity> playAnimation(String sound, String animation, long duration, boolean sendToClient) {
    this.currentAnimation = new Animation(animation, duration);
    if (sendToClient && !this.field_70170_p.field_72995_K) {
      EntityTracker tracker = ((WorldServer)this.field_70170_p).func_73039_n();
      SCPacketEntityPlayAnimation packet = new SCPacketEntityPlayAnimation(func_145782_y(), animation, duration);
      boolean playSound = (func_70673_aS() != null && !func_70673_aS().isEmpty());
      S29PacketSoundEffect soundPacket = new S29PacketSoundEffect(sound, this.field_70165_t, this.field_70163_u, this.field_70161_v, func_70599_aP(), 1.0F);
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)this)) {
        PalaJobs.proxy.getNetwork().sendTo((IMessage)packet, (EntityPlayerMP)entityPlayer);
        if (playSound)
          ((EntityPlayerMP)entityPlayer).field_71135_a.func_147359_a((Packet)soundPacket); 
      } 
    } 
    return this.currentAnimation;
  }
  
  public void tick() {
    func_70071_h_();
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
  
  public AnimationFactory getFactory() {
    return this.animationFactory;
  }
  
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController(this, "animationController", 0.0F, this::predicateAnimations));
  }
  
  private <E extends IAnimatable> PlayState predicateAnimations(AnimationEvent<E> event) {
    if (this.currentAnimation != null) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation(this.currentAnimation.getName(), Boolean.valueOf(false)));
      return PlayState.CONTINUE;
    } 
    if (isMovingAnimation() && this.defaultAnimations.containsKey(AnimationType.WALK)) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation(getDefaultAnimation(AnimationType.WALK), Boolean.valueOf(true)));
      return PlayState.CONTINUE;
    } 
    if (this.defaultAnimations.containsKey(AnimationType.IDLE)) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation(getDefaultAnimation(AnimationType.IDLE), Boolean.valueOf(true)));
      return PlayState.CONTINUE;
    } 
    return PlayState.CONTINUE;
  }
  
  public boolean isAnimated() {
    return (this.currentAnimation != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\gecko\AnimatedEntityLiving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */