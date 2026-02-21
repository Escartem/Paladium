package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class FlyingBehavior extends ABehavior {
  public static final String ID = "FLYING";
  
  private double height;
  
  public FlyingBehavior() {}
  
  public FlyingBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.height = ((Double)getData("height", Double.valueOf(2.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute())
      return false; 
    return !getBehaviorOwner().getAnimated().isDeathAnimation();
  }
  
  public void execute() {
    (getBehaviorOwner()).field_70122_E = true;
    (getBehaviorOwner()).field_70143_R = 0.0F;
    int groundY = (int)(getBehaviorOwner()).field_70163_u;
    Block block = (getBehaviorOwner()).field_70170_p.func_147439_a((int)(getBehaviorOwner()).field_70165_t, groundY - 1, (int)(getBehaviorOwner()).field_70161_v);
    groundY--;
    while ((block.func_149688_o() == Material.field_151579_a || (block != null && block.func_149655_b((IBlockAccess)(getBehaviorOwner()).field_70170_p, (int)(getBehaviorOwner()).field_70165_t, groundY - 1, (int)(getBehaviorOwner()).field_70161_v))) && groundY >= 0)
      block = (getBehaviorOwner()).field_70170_p.func_147439_a((int)(getBehaviorOwner()).field_70165_t, groundY - 1, (int)(getBehaviorOwner()).field_70161_v); 
    groundY++;
    double targetY = groundY + this.height;
    if ((getBehaviorOwner()).field_70163_u < targetY - 0.5D) {
      (getBehaviorOwner()).field_70181_x += 0.1D;
    } else if ((getBehaviorOwner()).field_70163_u > targetY + 0.5D) {
      (getBehaviorOwner()).field_70181_x -= 0.1D;
    } else {
      (getBehaviorOwner()).field_70181_x *= 0.6D;
    } 
  }
  
  public String getID() {
    return "FLYING";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new FlyingBehavior(behaviorData, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\FlyingBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */