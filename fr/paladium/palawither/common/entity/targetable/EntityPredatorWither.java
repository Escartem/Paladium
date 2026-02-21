package fr.paladium.palawither.common.entity.targetable;

import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawither.common.entity.projectile.EntityPredatorWitherProjectile;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import fr.paladium.palawither.common.wither.condition.impl.FactionPlayerCountWitherCondition;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPredatorWither extends EntityWitherCoordinateTargetable {
  public EntityPredatorWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(source -> Integer.valueOf((source.func_76346_g() instanceof net.minecraft.entity.player.EntityPlayer) ? 20 : 0)), WitherProperties.explosion(7, false, true) });
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(450.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(12.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
  }
  
  public void breakBlocks() {
    int floorX = MathHelper.func_76128_c(this.field_70165_t);
    int floorY = MathHelper.func_76128_c(this.field_70163_u);
    int floorZ = MathHelper.func_76128_c(this.field_70161_v);
    int width = Math.round(this.field_70130_N);
    int height = Math.round(this.field_70131_O);
    boolean broken = false;
    for (int ox = -width; ox <= width; ox++) {
      for (int oz = -width; oz <= width; oz++) {
        for (int oy = -1; oy <= height + 1; oy++) {
          int blockX = floorX + ox;
          int blockY = floorY + oy;
          int blockZ = floorZ + oz;
          broken = (breakBlock(blockX, blockY, blockZ) || broken);
        } 
      } 
    } 
    if (broken)
      this.field_70170_p.func_72889_a(null, 1012, floorX, floorY, floorZ, 0); 
  }
  
  public Class<? extends EntityWitherProjectile> getProjectile() {
    return (Class)EntityPredatorWitherProjectile.class;
  }
  
  public List<IWitherCondition> getConditions() {
    return Arrays.asList(new IWitherCondition[] { (IWitherCondition)FactionPlayerCountWitherCondition.min(FactionRelationshipType.ENEMY, 2) });
  }
  
  protected boolean hasVanillaBehavior() {
    return false;
  }
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/predator.png";
  }
  
  public String getColor() {
    return "#FF8B47";
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/predator_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/predator_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/predator_wither/spawn.ogg");
  }
  
  public long getCooldown() {
    return ForgeEnv.isIDE() ? 10L : 600L;
  }
  
  public double getViewDistance() {
    return 240.0D;
  }
  
  public IWither.WitherInvokeAlert getAlert() {
    return IWither.WitherInvokeAlert.SERVER;
  }
  
  public double getMaxDistance() {
    return 1000.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\targetable\EntityPredatorWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */