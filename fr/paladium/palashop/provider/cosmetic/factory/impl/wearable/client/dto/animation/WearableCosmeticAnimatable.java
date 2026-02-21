package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation;

import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl.JumpingWearableCosmeticAnimationType;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl.MovingWearableCosmeticAnimationType;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.impl.SneakingWearableCosmeticAnimationType;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;

public class WearableCosmeticAnimatable extends LindwormAnimatable {
  public WearableCosmeticAnimatable(@NonNull LindwormModel model, @NonNull Entity entity) {
    super(model, entity, 10.0F, new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType(), (LindwormAnimationType)new MovingWearableCosmeticAnimationType(), (LindwormAnimationType)new JumpingWearableCosmeticAnimationType(), (LindwormAnimationType)new SneakingWearableCosmeticAnimationType() });
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\dto\animation\WearableCosmeticAnimatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */