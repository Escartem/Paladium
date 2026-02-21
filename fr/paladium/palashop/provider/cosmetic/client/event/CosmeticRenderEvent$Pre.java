package fr.paladium.palashop.provider.cosmetic.client.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import lombok.NonNull;
import net.minecraft.entity.Entity;

@Cancelable
public class Pre extends CosmeticRenderEvent {
  public Pre(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    super(entity, cosmetic);
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\event\CosmeticRenderEvent$Pre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */