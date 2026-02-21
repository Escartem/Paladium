package fr.paladium.pet.common.entity;

import com.google.common.base.Strings;
import fr.paladium.pet.client.listener.PetRenderListener;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.pet.PetAdditionalData;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityDummyPet extends EntityLiving implements IAnimatable, IAnimationTickable {
  public static final String NONE_SKIN_ID = "none";
  
  public static final String BLOBFISH_SKIN_ID = "pet_blobfish";
  
  private final AnimationFactory factory;
  
  private String skinId;
  
  public EntityDummyPet(World world) {
    super(world);
    func_70105_a(1.4F, 1.6F);
    this.factory = new AnimationFactory(this);
  }
  
  public EntityDummyPet(World world, String skinId) {
    this(world);
    func_70105_a(1.4F, 1.6F);
    this.skinId = skinId;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public String getSkinId() {
    if (Strings.isNullOrEmpty(this.skinId))
      return "none"; 
    if (PetCommonProxy.APRIL_FOOL)
      return "pet_blobfish"; 
    return this.skinId;
  }
  
  public void setSkinId(String id) {
    if (Strings.isNullOrEmpty(id))
      id = "none"; 
    this.skinId = id;
  }
  
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController(this, "movementController", 0.0F, this::predicateMovement));
  }
  
  protected <E extends IAnimatable> PlayState predicateMovement(AnimationEvent<E> event) {
    AtomicBoolean modified = new AtomicBoolean(false);
    PetRenderListener.cachedRenderer.entrySet().forEach(entry -> {
          String playerUUID = (String)entry.getKey();
          EntityDummyPet targetedPet = (EntityDummyPet)entry.getValue();
          if (targetedPet == null)
            return; 
          for (Object o : (Minecraft.func_71410_x()).field_71441_e.field_72996_f) {
            Entity e = (Entity)o;
            if (e instanceof EntityPlayer) {
              EntityPlayer petOwner = (EntityPlayer)e;
              if (!petOwner.func_70005_c_().equals(playerUUID))
                continue; 
              if (targetedPet.func_110124_au() == func_110124_au()) {
                PetPlayer eep = PetPlayer.get(petOwner);
                if (eep != null && System.currentTimeMillis() - eep.getLastSkillUsage() <= 1500L) {
                  event.getController().setAnimation((new AnimationBuilder()).addAnimation("skill", Boolean.TRUE));
                  modified.set(true);
                  continue;
                } 
                if (petOwner.func_70090_H()) {
                  PetAdditionalData additionalData = PetCommonProxy.getInstance().findPet(targetedPet.getSkinId());
                  boolean shouldSwim = (additionalData == null || additionalData.isFish());
                  String animationType = shouldSwim ? "swim" : "walk";
                  event.getController().setAnimation((new AnimationBuilder()).addAnimation(animationType, Boolean.TRUE));
                  modified.set(true);
                  continue;
                } 
                if (petOwner.field_70137_T > petOwner.field_70163_u) {
                  event.getController().setAnimation((new AnimationBuilder()).addAnimation("fall", Boolean.TRUE));
                  modified.set(true);
                  continue;
                } 
                if (petOwner.field_70142_S != petOwner.field_70165_t || petOwner.field_70136_U != petOwner.field_70161_v) {
                  event.getController().setAnimation((new AnimationBuilder()).addAnimation("walk", Boolean.TRUE));
                  modified.set(true);
                } 
              } 
            } 
          } 
        });
    if (!modified.get())
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("idle", Boolean.valueOf(true))); 
    return PlayState.CONTINUE;
  }
  
  public AnimationFactory getFactory() {
    return this.factory;
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
  
  public void tick() {
    func_70071_h_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\entity\EntityDummyPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */