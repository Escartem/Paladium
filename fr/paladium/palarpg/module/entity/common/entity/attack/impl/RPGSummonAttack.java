package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.ISummonEntityAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class RPGSummonAttack extends ARPGBaseAttack<RPGMobEntity> implements ISummonEntityAttack {
  public static final String ID = "SUMMON";
  
  private static final Random RANDOM = new Random();
  
  private static final PlayerEntitySelector PLAYER_SELECTOR = new PlayerEntitySelector();
  
  private final List<Integer> addSpawned = new ArrayList<>();
  
  private List<RPGEntityData> entities;
  
  private int count;
  
  public RPGSummonAttack(RPGAttack attack, RPGMobEntity entity) {
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
    this.count = ((Double)getData("count", Double.valueOf(1.0D))).intValue();
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || this.count <= 0 || this.entities.isEmpty())
      return false; 
    List<EntityPlayer> players = getEntitiesAround(EntityPlayer.class, 10.0D, PLAYER_SELECTOR);
    if (players.isEmpty())
      return false; 
    return true;
  }
  
  public void onEnd() {
    super.onEnd();
    int i;
    label19: for (i = 0; i < this.count; i++) {
      RPGEntityData entityData = this.entities.get(RANDOM.nextInt(this.entities.size()));
      if (entityData != null) {
        double angle = RANDOM.nextDouble() * 2.0D * Math.PI;
        double r = getAttack().getRange() * Math.sqrt(RANDOM.nextDouble());
        double x = ((RPGMobEntity)getEntity()).field_70165_t + r * Math.cos(angle);
        double y = ((RPGMobEntity)getEntity()).field_70163_u;
        double z = ((RPGMobEntity)getEntity()).field_70161_v + r * Math.sin(angle);
        int maxIter = 5;
        while (!((RPGMobEntity)getEntity()).field_70170_p.func_147437_c((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z))) {
          if (maxIter <= 0)
            continue label19; 
          y++;
          maxIter--;
        } 
        RPGMobEntity entity = entityData.create(((RPGMobEntity)getEntity()).field_70170_p);
        entity.func_70107_b(x, y, z);
        ((RPGMobEntity)getEntity()).field_70170_p.func_72838_d((Entity)entity);
        if (!((RPGMobEntity)getEntity()).field_70170_p.field_72995_K)
          this.addSpawned.add(Integer.valueOf(entity.func_145782_y())); 
      } 
    } 
  }
  
  public String getID() {
    return "SUMMON";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGSummonAttack(attack, entity);
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
  
  public RPGSummonAttack() {}
  
  private static class PlayerEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof EntityPlayer))
        return false; 
      EntityPlayer player = (EntityPlayer)entity;
      return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGSummonAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */