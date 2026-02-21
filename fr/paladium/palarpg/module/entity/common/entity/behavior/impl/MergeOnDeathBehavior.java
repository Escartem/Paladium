package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.network.SCPacketUpdateRPGEntitySize;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class MergeOnDeathBehavior extends ABehavior {
  public static final String ID = "MERGE_ON_DEATH";
  
  private double range;
  
  private float scalePerStack;
  
  private double hpPerStack;
  
  private final RPGEntitySelector selector = new RPGEntitySelector();
  
  private final Sorter sorter = new Sorter();
  
  private boolean executed = false;
  
  private float mergeStack = 1.0F;
  
  private RPGMobEntity closestEntity;
  
  public MergeOnDeathBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.range = ((Double)getData("range", Double.valueOf(50.0D))).doubleValue();
    this.scalePerStack = ((Double)getData("scalePerStack", Double.valueOf(0.25D))).floatValue();
    this.hpPerStack = ((Double)getData("hpPerStack", Double.valueOf(100.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || (getBehaviorOwner()).field_70170_p.field_72995_K || this.executed || !getBehaviorOwner().getAnimated().isDeathAnimation())
      return false; 
    AxisAlignedBB alignedBB = (getBehaviorOwner()).field_70121_D.func_72314_b(this.range, 4.0D, this.range);
    List<RPGMobEntity> listMob = (getBehaviorOwner()).field_70170_p.func_82733_a(RPGMobEntity.class, alignedBB, this.selector);
    if (listMob.isEmpty())
      return false; 
    Collections.sort(listMob, this.sorter);
    this.closestEntity = listMob.get(0);
    return true;
  }
  
  public void execute() {
    WorldServer worldServer = (WorldServer)this.closestEntity.field_70170_p;
    Set<EntityPlayer> players = worldServer.func_73039_n().getTrackingPlayers((Entity)this.closestEntity);
    float actualScale = this.closestEntity.getRpgData().getSize().getScale();
    Optional<MergeOnDeathBehavior> optMergeBehavior = this.closestEntity.getBehavior("MERGE_ON_DEATH");
    optMergeBehavior.ifPresent(modB -> modB.appendMergeStack(this.mergeStack));
    this.closestEntity.playAnimation("death_fusion", 2000L, true);
    this.closestEntity.setSizeScale(actualScale + this.scalePerStack * this.mergeStack);
    this.closestEntity.addMaxHealth((float)(this.hpPerStack * this.mergeStack));
    this.executed = true;
    players.forEach(player -> (new SCPacketUpdateRPGEntitySize(this.closestEntity.func_145782_y(), this.closestEntity.getRpgData().getSize().getScale())).send((EntityPlayerMP)player));
  }
  
  public String getID() {
    return "MERGE_ON_DEATH";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new MergeOnDeathBehavior(behaviorData, entity);
  }
  
  public void appendMergeStack(float stack) {
    this.mergeStack += stack;
  }
  
  public MergeOnDeathBehavior() {}
  
  protected class RPGEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof RPGMobEntity) || entity == MergeOnDeathBehavior.this.getBehaviorOwner())
        return false; 
      RPGMobEntity owner = MergeOnDeathBehavior.this.getBehaviorOwner();
      RPGMobEntity rpgEntity = (RPGMobEntity)entity;
      if (!rpgEntity.hasBehavior("MERGE_ON_DEATH"))
        return false; 
      return (owner.getRPGType() == rpgEntity.getRPGType());
    }
  }
  
  protected class Sorter implements Comparator<Entity> {
    public int compare(Entity e1, Entity e2) {
      double d0 = MergeOnDeathBehavior.this.getBehaviorOwner().func_70068_e(e1);
      double d1 = MergeOnDeathBehavior.this.getBehaviorOwner().func_70068_e(e2);
      return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\MergeOnDeathBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */