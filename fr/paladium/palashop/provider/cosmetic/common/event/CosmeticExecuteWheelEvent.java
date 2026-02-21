package fr.paladium.palashop.provider.cosmetic.common.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketExecuteWheelCosmetic;
import lombok.NonNull;
import net.minecraft.entity.Entity;

public class CosmeticExecuteWheelEvent extends Event {
  private final Entity entity;
  
  private final ICosmetic cosmetic;
  
  private final CSPacketExecuteWheelCosmetic.HitResult target;
  
  public CosmeticExecuteWheelEvent(Entity entity, ICosmetic cosmetic, CSPacketExecuteWheelCosmetic.HitResult target) {
    this.entity = entity;
    this.cosmetic = cosmetic;
    this.target = target;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public ICosmetic getCosmetic() {
    return this.cosmetic;
  }
  
  public CSPacketExecuteWheelCosmetic.HitResult getTarget() {
    return this.target;
  }
  
  @Cancelable
  public static class Pre extends CosmeticExecuteWheelEvent {
    public Pre(@NonNull Entity entity, @NonNull ICosmetic cosmetic, @NonNull CSPacketExecuteWheelCosmetic.HitResult target) {
      super(entity, cosmetic, target);
      if (entity == null)
        throw new NullPointerException("entity is marked non-null but is null"); 
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
      if (target == null)
        throw new NullPointerException("target is marked non-null but is null"); 
    }
  }
  
  public static class Post extends CosmeticExecuteWheelEvent {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\event\CosmeticExecuteWheelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */