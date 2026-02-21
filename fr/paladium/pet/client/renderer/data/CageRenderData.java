package fr.paladium.pet.client.renderer.data;

import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.entity.EntityPetCage;
import fr.paladium.pet.common.tile.cage.TileEntityPetCage;
import net.minecraft.entity.item.EntityItem;

public class CageRenderData {
  private static final long REQUEST_DELAY = 500L;
  
  private IntLocation location;
  
  private EntityPetCage entityCage;
  
  private EntityDummyPet entityPet;
  
  private EntityItem itemBait;
  
  private CageRenderState state;
  
  private long lastRequestMillis;
  
  public void setLocation(IntLocation location) {
    this.location = location;
  }
  
  public void setEntityCage(EntityPetCage entityCage) {
    this.entityCage = entityCage;
  }
  
  public void setEntityPet(EntityDummyPet entityPet) {
    this.entityPet = entityPet;
  }
  
  public void setItemBait(EntityItem itemBait) {
    this.itemBait = itemBait;
  }
  
  public void setState(CageRenderState state) {
    this.state = state;
  }
  
  public void setLastRequestMillis(long lastRequestMillis) {
    this.lastRequestMillis = lastRequestMillis;
  }
  
  public CageRenderData(IntLocation location, EntityPetCage entityCage, EntityDummyPet entityPet, EntityItem itemBait, CageRenderState state, long lastRequestMillis) {
    this.location = location;
    this.entityCage = entityCage;
    this.entityPet = entityPet;
    this.itemBait = itemBait;
    this.state = state;
    this.lastRequestMillis = lastRequestMillis;
  }
  
  public IntLocation getLocation() {
    return this.location;
  }
  
  public EntityPetCage getEntityCage() {
    return this.entityCage;
  }
  
  public EntityDummyPet getEntityPet() {
    return this.entityPet;
  }
  
  public EntityItem getItemBait() {
    return this.itemBait;
  }
  
  public CageRenderState getState() {
    return this.state;
  }
  
  public long getLastRequestMillis() {
    return this.lastRequestMillis;
  }
  
  public boolean requestUpdate(long now) {
    if (now - this.lastRequestMillis < 500L)
      return false; 
    this.lastRequestMillis = now;
    return true;
  }
  
  public static CageRenderData of(TileEntityPetCage cage) {
    if (cage.getPet() == null)
      return null; 
    return new CageRenderData(
        IntLocation.of(cage.field_145851_c, cage.field_145848_d, cage.field_145849_e), 
        PetRenderUtils.getCageFromEnum(), 
        PetRenderUtils.getPetFromEnum(cage.getPet()), new EntityItem(cage
          .func_145831_w()), 
        CageRenderState.of(cage.getStatus()), 
        System.currentTimeMillis());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\data\CageRenderData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */