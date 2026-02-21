package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class SneakingWearableCosmeticAnimationType extends LindwormAnimationType {
  public SneakingWearableCosmeticAnimationType() {
    super("sneaking", 2, false, entity -> (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70093_af()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\animation\impl\SneakingWearableCosmeticAnimationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */