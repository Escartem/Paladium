package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class JumpingWearableCosmeticAnimationType extends LindwormAnimationType {
  public JumpingWearableCosmeticAnimationType() {
    super("jumping", 2, false, entity -> (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).field_70181_x > 0.009999999776482582D));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\animation\impl\JumpingWearableCosmeticAnimationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */