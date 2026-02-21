package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.animation;

import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.animation.impl.SpawnKillAnimationCosmeticAnimationType;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class KillAnimationCosmeticAnimatable extends LindwormAnimatable {
  public KillAnimationCosmeticAnimatable(@NonNull LindwormModel model, @NonNull Entity entity) {
    super(model, entity, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new SpawnKillAnimationCosmeticAnimationType() });
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\dto\animation\KillAnimationCosmeticAnimatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */