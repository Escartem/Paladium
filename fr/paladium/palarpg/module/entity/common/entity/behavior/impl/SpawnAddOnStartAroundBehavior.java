package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;

public class SpawnAddOnStartAroundBehavior extends ABehavior {
  private static final String ID = "SPAWN_ADD_ON_START_AROUND";
  
  private boolean spawned = false;
  
  private double radius;
  
  private List<RPGEntityData> entities;
  
  private int count;
  
  private SpawnAddOnStartAroundBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    this.radius = ((Double)getData("radius", Double.valueOf(10.0D))).doubleValue();
    this.entities = new ArrayList<>();
    int i = 0;
    while (getData("entity" + i, RPGEntityData.class) != null) {
      this.entities.add(getData("entity" + i, RPGEntityData.class));
      i++;
    } 
    this.count = ((Double)getData("count", Double.valueOf(10.0D))).intValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || (getBehaviorOwner()).field_70170_p.field_72995_K || this.spawned)
      return false; 
    return true;
  }
  
  public void execute() {
    this.spawned = true;
    double min = (getBehaviorOwner()).field_70130_N / 2.0D;
    double max = this.radius;
    for (int i = 0; i < this.count; i++) {
      RPGEntityData entityData = this.entities.get(getBehaviorOwner().func_70681_au().nextInt(this.entities.size()));
      if (entityData == null)
        return; 
      RPGMobEntity entity = entityData.create((getBehaviorOwner()).field_70170_p);
      if (entity != null) {
        double offsetX = Math.random() * (max - min) + min;
        double offsetZ = Math.random() * (max - min) + min;
        if (getBehaviorOwner().func_70681_au().nextBoolean())
          offsetX = -offsetX; 
        if (getBehaviorOwner().func_70681_au().nextBoolean())
          offsetZ = -offsetZ; 
        double spawnX = (getBehaviorOwner()).field_70165_t + offsetX;
        double spawnY = (getBehaviorOwner()).field_70163_u + 0.5D;
        double spawnZ = (getBehaviorOwner()).field_70161_v + offsetZ;
        entity.func_70107_b(spawnX, spawnY, spawnZ);
        (getBehaviorOwner()).field_70170_p.func_72838_d((Entity)entity);
      } 
    } 
  }
  
  public String getID() {
    return "SPAWN_ADD_ON_START_AROUND";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new SpawnAddOnStartAroundBehavior(behaviorData, entity);
  }
  
  public SpawnAddOnStartAroundBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\SpawnAddOnStartAroundBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */