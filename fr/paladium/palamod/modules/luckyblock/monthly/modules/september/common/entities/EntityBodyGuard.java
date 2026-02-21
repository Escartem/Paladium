package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityBodyGuard extends EntityZombie implements IAnimatable, IAnimationTickable {
  public static final String ENTITY_ID = "entityBodyGuard";
  
  private static final String EMPTY_STRING = "";
  
  private static final double DEFAULT_HEALTH = 100.0D;
  
  private AnimationFactory factory;
  
  private boolean attacking;
  
  public EntityBodyGuard(World world) {
    super(world);
    func_70105_a(0.6F, 1.95F);
    this.factory = new AnimationFactory(this);
    this.attacking = false;
  }
  
  public void spawn(World world, DoubleLocation location) {
    func_70634_a(location.getX(), location.getY(), location.getZ());
    world.func_72838_d((Entity)this);
    applyPotionEffects();
  }
  
  public void applyPotionEffects() {
    func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 0));
    func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 2400, 0));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
  }
  
  public boolean func_70652_k(Entity entity) {
    boolean flag = super.func_70652_k(entity);
    if (flag)
      this.attacking = true; 
    return flag;
  }
  
  protected String func_70639_aQ() {
    return "";
  }
  
  protected String func_70621_aR() {
    return "";
  }
  
  protected String func_70673_aS() {
    return "";
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}
  
  protected Item func_146068_u() {
    return null;
  }
  
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController(this, "movementController", 0.0F, this::predicateMovement));
  }
  
  public AnimationFactory getFactory() {
    return this.factory;
  }
  
  public void tick() {
    func_70071_h_();
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    applyPotionEffects();
  }
  
  private <E extends IAnimatable> PlayState predicateMovement(AnimationEvent<E> event) {
    if (this.field_70159_w > 0.0D || this.field_70179_y > 0.0D) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("walk", Boolean.valueOf(true)));
    } else if (this.field_70159_w == 0.0D && this.field_70179_y == 0.0D) {
      event.getController().setAnimation((new AnimationBuilder()).addAnimation("walk", Boolean.valueOf(false)));
    } 
    return PlayState.CONTINUE;
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\entities\EntityBodyGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */