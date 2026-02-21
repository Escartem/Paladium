package fr.paladium.palawither.common.entity;

import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPassiveWither extends EntityWitherBase {
  public EntityPassiveWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(20), WitherProperties.explosion(7, false, true) });
  }
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/passive.png";
  }
  
  public String getColor() {
    return "#504440";
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/passive_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/passive_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/passive_wither/spawn.ogg");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\EntityPassiveWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */