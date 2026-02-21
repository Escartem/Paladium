package fr.paladium.palawither.common.entity;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawither.common.entity.projectile.EntityBabyWitherProjectile;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import lombok.NonNull;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBabyWither extends EntityWitherBase {
  public EntityBabyWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70105_a(0.6F, 1.8F);
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(20), WitherProperties.explosion(3, false, true), WitherProperties.projectile(EntityBabyWitherProjectile.class) });
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
  }
  
  protected int getExperienceValue() {
    return 25;
  }
  
  protected void func_70628_a(boolean recentlyHit, int looting) {}
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/baby.png";
  }
  
  public String getColor() {
    return "#44B0B5";
  }
  
  public long getCooldown() {
    return 5L;
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/baby_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/baby_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/baby_wither/spawn.ogg");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\EntityBabyWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */