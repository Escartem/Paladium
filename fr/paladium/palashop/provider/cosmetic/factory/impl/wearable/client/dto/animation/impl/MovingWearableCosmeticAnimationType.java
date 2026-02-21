package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class MovingWearableCosmeticAnimationType extends LindwormAnimationType {
  public MovingWearableCosmeticAnimationType() {
    super("moving", 1, true, entity -> (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).field_70721_aZ > 0.01F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\animation\impl\MovingWearableCosmeticAnimationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */