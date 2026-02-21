package fr.paladium.palawither.common.utils;

import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.impl.VanillaWither;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;

public class WitherUtils {
  public static boolean isWither(Entity entity) {
    return (entity instanceof EntityWither || entity instanceof IWither);
  }
  
  public static Optional<IWither> getWither(Entity entity) {
    if (entity instanceof IWither)
      return Optional.of((IWither)entity); 
    if (entity instanceof EntityWither) {
      IWither wither = VanillaWither.getByEntity((EntityWither)entity).orElse(null);
      return Optional.ofNullable(wither);
    } 
    return Optional.empty();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\commo\\utils\WitherUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */