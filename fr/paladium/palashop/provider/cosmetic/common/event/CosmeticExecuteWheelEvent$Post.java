package fr.paladium.palashop.provider.cosmetic.common.event;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketExecuteWheelCosmetic;
import lombok.NonNull;
import net.minecraft.entity.Entity;

public class Post extends CosmeticExecuteWheelEvent {
  public Post(@NonNull Entity entity, @NonNull ICosmetic cosmetic, @NonNull CSPacketExecuteWheelCosmetic.HitResult target) {
    super(entity, cosmetic, target);
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\event\CosmeticExecuteWheelEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */