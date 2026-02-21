package fr.paladium.palashop.provider.cosmetic.client.event;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import lombok.NonNull;
import net.minecraft.entity.Entity;

public class Post extends CosmeticRenderEvent {
  public Post(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    super(entity, cosmetic);
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\event\CosmeticRenderEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */