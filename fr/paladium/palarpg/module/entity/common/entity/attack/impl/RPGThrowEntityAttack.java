package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.ISummonEntityAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;

public class RPGThrowEntityAttack extends ARPGBaseAttack<RPGMobEntity> implements ISummonEntityAttack {
  public static final String ID = "THROW_ENTITY";
  
  private static final Random RANDOM = new Random();
  
  private final List<Integer> addSpawned = new ArrayList<>();
  
  private List<RPGEntityData> entities;
  
  public RPGThrowEntityAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    this.entities = new ArrayList<>();
    int i = 0;
    while (getData("entity" + i, RPGEntityData.class) != null) {
      this.entities.add(getData("entity" + i, RPGEntityData.class));
      i++;
    } 
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null)
      return false; 
    return true;
  }
  
  public void onEnd() {
    super.onEnd();
    if (this.entities.size() == 0) {
      EntityModule.logger.error("The entities list is empty in RPGThrowEntityAttack", new Object[0]);
      return;
    } 
    RPGEntityData entityData = this.entities.get(RANDOM.nextInt(this.entities.size()));
    if (entityData == null) {
      EntityModule.logger.error("No entity data found in RPGThrowEntityAttack", new Object[0]);
      return;
    } 
    RPGMobEntity entity = entityData.create(((RPGMobEntity)getEntity()).field_70170_p);
    if (entity == null)
      EntityModule.logger.error("Entity is null for id '" + entityData.getId() + "' in RPGThrowEntityAttack", new Object[0]); 
    throwProjectile((Entity)entity, (Entity)getEntity(), (Entity)((RPGMobEntity)getEntity()).func_70638_az(), 1.0F, 1.0F);
    if (!((RPGMobEntity)getEntity()).field_70170_p.field_72995_K)
      this.addSpawned.add(Integer.valueOf(entity.func_145782_y())); 
  }
  
  public String getID() {
    return "THROW_ENTITY";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGThrowEntityAttack(attack, entity);
  }
  
  public boolean killEntitiesOnDeath() {
    return true;
  }
  
  public boolean hasEntitiesSpawned() {
    return !this.addSpawned.isEmpty();
  }
  
  public List<Entity> getEntitiesSpawned() {
    List<Entity> entities = new ArrayList<>();
    this.addSpawned.forEach(entityId -> {
          Entity entity = ((RPGMobEntity)getEntity()).field_70170_p.func_73045_a(entityId.intValue());
          if (entity != null)
            entities.add(entity); 
        });
    return entities;
  }
  
  public RPGThrowEntityAttack() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGThrowEntityAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */