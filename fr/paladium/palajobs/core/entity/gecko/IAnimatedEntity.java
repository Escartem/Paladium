package fr.paladium.palajobs.core.entity.gecko;

import fr.paladium.palajobs.core.entity.gecko.animation.Animation;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import net.minecraft.entity.Entity;

public interface IAnimatedEntity {
  Entity setDefaultAnimation(AnimationType paramAnimationType, String... paramVarArgs);
  
  Animation<Entity> playAnimation(String paramString, long paramLong, boolean paramBoolean);
  
  Animation<Entity> playAnimation(String paramString1, String paramString2, long paramLong, boolean paramBoolean);
  
  boolean isAnimated();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\gecko\IAnimatedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */