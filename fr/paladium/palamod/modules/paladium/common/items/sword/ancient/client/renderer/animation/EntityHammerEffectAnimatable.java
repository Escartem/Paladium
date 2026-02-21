package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.client.renderer.animation;

import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.client.renderer.animation.impl.AttackAncientHammerAnimationType;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;

public class EntityHammerEffectAnimatable extends LindwormAnimatable {
  public EntityHammerEffectAnimatable(@NonNull LindwormModel<LindwormAnimatable> model, @NonNull Entity entity) {
    super(model, entity, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new AttackAncientHammerAnimationType() });
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\client\renderer\animation\EntityHammerEffectAnimatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */