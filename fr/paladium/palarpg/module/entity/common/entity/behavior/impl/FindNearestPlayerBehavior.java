package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class FindNearestPlayerBehavior extends ABehavior {
  public static final String ID = "FIND_NEAREST_PLAYER";
  
  public final PlayerEntitySelector playerEntitySelector = new PlayerEntitySelector();
  
  private final Sorter sorter = new Sorter();
  
  private EntityPlayer targetEntity;
  
  private double range;
  
  public FindNearestPlayerBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.range = ((Double)getData("range", Double.valueOf(16.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || getBehaviorOwner() == null)
      return false; 
    if (getBehaviorOwner().func_70638_az() != null && getBehaviorOwner().func_70068_e((Entity)getBehaviorOwner().func_70638_az()) <= this.range * this.range && this.playerEntitySelector.func_82704_a((Entity)getBehaviorOwner().func_70638_az()))
      return false; 
    AxisAlignedBB checkAABB = (getBehaviorOwner()).field_70121_D.func_72314_b(this.range, 4.0D, this.range);
    List<EntityPlayer> entitiesInRange = (getBehaviorOwner()).field_70170_p.func_82733_a(EntityPlayer.class, checkAABB, this.playerEntitySelector);
    if (entitiesInRange.isEmpty())
      return false; 
    Collections.sort(entitiesInRange, this.sorter);
    this.targetEntity = entitiesInRange.get(0);
    return true;
  }
  
  public void execute() {
    getBehaviorOwner().func_70624_b((EntityLivingBase)this.targetEntity);
  }
  
  public String getID() {
    return "FIND_NEAREST_PLAYER";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new FindNearestPlayerBehavior(behaviorData, entity);
  }
  
  public FindNearestPlayerBehavior() {}
  
  protected class PlayerEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof EntityPlayer))
        return false; 
      EntityPlayer player = (EntityPlayer)entity;
      return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a && FindNearestPlayerBehavior.this.getBehaviorOwner().func_70635_at().func_75522_a((Entity)player));
    }
  }
  
  protected class Sorter implements Comparator<Entity> {
    public int compare(Entity e1, Entity e2) {
      double d0 = FindNearestPlayerBehavior.this.getBehaviorOwner().func_70068_e(e1);
      double d1 = FindNearestPlayerBehavior.this.getBehaviorOwner().func_70068_e(e2);
      return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\FindNearestPlayerBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */