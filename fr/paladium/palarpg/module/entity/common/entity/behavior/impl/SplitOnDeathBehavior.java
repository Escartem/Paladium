package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.util.Map;
import net.minecraft.entity.Entity;

public class SplitOnDeathBehavior extends ABehavior {
  public static final String ID = "SPLIT_ON_DEATH";
  
  private final double min = -1.2D;
  
  private final double max = 1.2D;
  
  private boolean executed = false;
  
  private RPGEntityData splitEntity;
  
  private int count;
  
  public SplitOnDeathBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    this.splitEntity = (RPGEntityData)getData("entity", RPGEntityData.class);
    this.count = ((Double)getData("count", Double.valueOf(1.0D))).intValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute())
      return false; 
    return (!(getBehaviorOwner()).field_70170_p.field_72995_K && !this.executed && getBehaviorOwner().getAnimated().isDeathAnimation() && !(getBehaviorOwner()).field_70128_L);
  }
  
  public void execute() {
    this.executed = true;
    if (this.splitEntity == null)
      return; 
    getBehaviorOwner().getAnimated().getCurrentAnimation().setCallback(e -> {
          getBehaviorOwner().func_70106_y();
          for (int i = 0; i < this.count; i++) {
            RPGMobEntity mob = this.splitEntity.create((getBehaviorOwner()).field_70170_p);
            mob.func_70107_b((getBehaviorOwner()).field_70165_t, (getBehaviorOwner()).field_70163_u, (getBehaviorOwner()).field_70161_v);
            mob.field_70159_w = getRandom();
            mob.field_70179_y = getRandom();
            (getBehaviorOwner()).field_70170_p.func_72838_d((Entity)mob);
          } 
        });
  }
  
  private double getRandom() {
    getClass();
    getClass();
    getClass();
    return Math.random() * (1.2D - -1.2D + 1.0D) + -1.2D;
  }
  
  public String getID() {
    return "SPLIT_ON_DEATH";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new SplitOnDeathBehavior(behaviorData, entity);
  }
  
  public SplitOnDeathBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\SplitOnDeathBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */