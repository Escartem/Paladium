package fr.paladium.palashop.provider.cosmetic.client.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public class CosmeticRenderEvent extends Event {
  private final Entity entity;
  
  private final ICosmetic cosmetic;
  
  public CosmeticRenderEvent(Entity entity, ICosmetic cosmetic) {
    this.entity = entity;
    this.cosmetic = cosmetic;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public ICosmetic getCosmetic() {
    return this.cosmetic;
  }
  
  @NonNull
  public static Pre execute(@NonNull Entity entity, @NonNull ICosmetic cosmetic, Consumer<Pre> event) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    Pre pre = (new Pre(entity, cosmetic)).<Pre>call();
    if (!pre.isCanceled()) {
      event.accept(pre);
      (new Post(entity, cosmetic)).call();
    } 
    return pre;
  }
  
  @NonNull
  public <T extends CosmeticRenderEvent> T call() {
    MinecraftForge.EVENT_BUS.post(this);
    return (T)this;
  }
  
  @Cancelable
  public static class Pre extends CosmeticRenderEvent {
    public Pre(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
      super(entity, cosmetic);
      if (entity == null)
        throw new NullPointerException("entity is marked non-null but is null"); 
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
    }
  }
  
  public static class Post extends CosmeticRenderEvent {
    public Post(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
      super(entity, cosmetic);
      if (entity == null)
        throw new NullPointerException("entity is marked non-null but is null"); 
      if (cosmetic == null)
        throw new NullPointerException("cosmetic is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\event\CosmeticRenderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */