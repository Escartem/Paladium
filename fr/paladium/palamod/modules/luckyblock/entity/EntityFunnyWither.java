package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import lombok.NonNull;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityFunnyWither extends EntityWitherBase {
  public EntityFunnyWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(20), WitherProperties.explosion(7, false, true) });
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(600.0D);
  }
  
  public String getBarTexture() {
    return "palamod:textures/overlay/wither/foreground/funny.png";
  }
  
  public String getColor() {
    return "#FFFF80";
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palamod", "sounds/entities/funny_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palamod", "sounds/entities/funny_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return (ResourceLocation)MCResource.of("palamod", "sounds/entities/funny_wither/spawn.ogg");
  }
  
  public String func_70005_c_() {
    return StatCollector.func_74838_a("entity.EntityFunnyWither.name");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityFunnyWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */