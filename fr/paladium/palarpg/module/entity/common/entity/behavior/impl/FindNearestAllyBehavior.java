package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class FindNearestAllyBehavior extends ABehavior {
  public static final String ID = "FIND_NEAREST_ALLY";
  
  public final RPGEntitySelector rpgEntitySelector = new RPGEntitySelector();
  
  public RPGEntitySelector getRpgEntitySelector() {
    return this.rpgEntitySelector;
  }
  
  private final Sorter sorter = new Sorter();
  
  private RPGMobEntity targetEntity;
  
  private double range;
  
  private boolean boosted;
  
  private RPGElementType elementType;
  
  public Sorter getSorter() {
    return this.sorter;
  }
  
  public RPGMobEntity getTargetEntity() {
    return this.targetEntity;
  }
  
  public double getRange() {
    return this.range;
  }
  
  public boolean isBoosted() {
    return this.boosted;
  }
  
  public RPGElementType getElementType() {
    return this.elementType;
  }
  
  protected FindNearestAllyBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.range = ((Double)getData("range", Double.valueOf(16.0D))).doubleValue();
    this.boosted = ((Boolean)getData("boosted", Boolean.valueOf(false))).booleanValue();
    this.elementType = (RPGElementType)getData("elementType", RPGElementType.class);
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || getBehaviorOwner() == null || getBehaviorOwner().func_70638_az() != null)
      return false; 
    AxisAlignedBB checkAABB = (getBehaviorOwner()).field_70121_D.func_72314_b(this.range, 4.0D, this.range);
    List<RPGMobEntity> entitiesInRange = (getBehaviorOwner()).field_70170_p.func_82733_a(RPGMobEntity.class, checkAABB, this.rpgEntitySelector);
    if (entitiesInRange.isEmpty())
      return false; 
    Collections.sort(entitiesInRange, this.sorter);
    this.targetEntity = entitiesInRange.get(0);
    return true;
  }
  
  public void execute() {
    getBehaviorOwner().func_70624_b((EntityLivingBase)this.targetEntity);
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new FindNearestAllyBehavior(behaviorData, entity);
  }
  
  public String getID() {
    return "FIND_NEAREST_ALLY";
  }
  
  public FindNearestAllyBehavior() {}
  
  protected class RPGEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof RPGMobEntity) || entity == FindNearestAllyBehavior.this.getBehaviorOwner())
        return false; 
      RPGMobEntity rpgEntity = (RPGMobEntity)entity;
      if (rpgEntity.getBehaviorsName().stream().filter(bName -> bName.startsWith("BOOST")).findFirst().isPresent())
        return false; 
      RPGElementType elementType = FindNearestAllyBehavior.this.getElementType();
      if (elementType == null)
        return (rpgEntity.isBoosted() == FindNearestAllyBehavior.this.isBoosted()); 
      return (rpgEntity.getRPGType() == elementType && rpgEntity.isBoosted() == FindNearestAllyBehavior.this.isBoosted());
    }
  }
  
  protected class Sorter implements Comparator<Entity> {
    public int compare(Entity e1, Entity e2) {
      double d0 = FindNearestAllyBehavior.this.getBehaviorOwner().func_70068_e(e1);
      double d1 = FindNearestAllyBehavior.this.getBehaviorOwner().func_70068_e(e2);
      return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\FindNearestAllyBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */