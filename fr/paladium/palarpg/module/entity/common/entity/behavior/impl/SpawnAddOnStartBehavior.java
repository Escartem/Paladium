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

public class SpawnAddOnStartBehavior extends ABehavior {
  private static final String ID = "SPAWN_ADD_ON_START";
  
  private boolean spawned = false;
  
  private List<RPGEntityData> entities;
  
  private SpawnAddOnStartBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    this.entities = new ArrayList<>();
    int i = 0;
    while (getData("entity" + i, RPGEntityData.class) != null) {
      this.entities.add(getData("entity" + i, RPGEntityData.class));
      i++;
    } 
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || (getBehaviorOwner()).field_70170_p.field_72995_K || this.spawned)
      return false; 
    return true;
  }
  
  public void execute() {
    this.spawned = true;
    for (RPGEntityData entityData : this.entities) {
      RPGMobEntity entity = entityData.create((getBehaviorOwner()).field_70170_p);
      if (entity == null)
        continue; 
      entity.func_70107_b((getBehaviorOwner()).field_70165_t, (getBehaviorOwner()).field_70163_u, (getBehaviorOwner()).field_70161_v);
      (getBehaviorOwner()).field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getID() {
    return "SPAWN_ADD_ON_START";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new SpawnAddOnStartBehavior(behaviorData, entity);
  }
  
  public SpawnAddOnStartBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\SpawnAddOnStartBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */