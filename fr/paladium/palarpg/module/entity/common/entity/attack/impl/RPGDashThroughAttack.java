package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RPGDashThroughAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "DASH_THROUGH";
  
  public RPGDashThroughAttack() {}
  
  private static BlockReplacementThread REPLACEMENT_THREAD = new BlockReplacementThread();
  
  private double minDist;
  
  private boolean flameGround;
  
  static {
    REPLACEMENT_THREAD.start();
  }
  
  public RPGDashThroughAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.minDist = ((Double)getData("minDist", Double.valueOf(5.0D))).doubleValue();
    this.flameGround = ((Boolean)getData("flameGround", Boolean.valueOf(false))).booleanValue();
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null || ((RPGMobEntity)getEntity()).field_70170_p.field_72995_K || !(((RPGMobEntity)getEntity()).func_70638_az() instanceof EntityPlayer) || ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) < this.minDist)
      return false; 
    return true;
  }
  
  public void onStart() {
    super.onStart();
  }
  
  public void perform() {
    super.perform();
    if (this.flameGround && ((RPGMobEntity)getEntity()).field_70170_p.func_147437_c((int)((RPGMobEntity)getEntity()).field_70165_t, (int)((RPGMobEntity)getEntity()).field_70163_u, (int)((RPGMobEntity)getEntity()).field_70161_v)) {
      World world = ((RPGMobEntity)getEntity()).field_70170_p;
      BlockLocation location = new BlockLocation(world, (int)((RPGMobEntity)getEntity()).field_70165_t, (int)((RPGMobEntity)getEntity()).field_70163_u, (int)((RPGMobEntity)getEntity()).field_70161_v);
      BlockReplacement replacement = new BlockReplacement(location);
      REPLACEMENT_THREAD.addBlockReplacement(replacement, System.currentTimeMillis() + 3000L);
      location.setBlock((Block)Blocks.field_150480_ab);
    } 
    if (getExecutingTick() >= 21) {
      ((RPGMobEntity)getEntity()).field_70177_z = ((RPGMobEntity)getEntity()).field_70126_B;
      double speed = ((RPGMobEntity)getEntity()).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 4.0D;
      float yawRad = (float)Math.toRadians(((RPGMobEntity)getEntity()).field_70177_z);
      double dirX = -Math.sin(yawRad);
      double dirZ = Math.cos(yawRad);
      ((RPGMobEntity)getEntity()).func_70091_d(dirX * speed, 0.0D, dirZ * speed);
      List<EntityPlayer> players = ((RPGMobEntity)getEntity()).field_70170_p.field_73010_i;
      players.forEach(player -> {
            if (((RPGMobEntity)getEntity()).field_70121_D.func_72326_a(player.field_70121_D)) {
              damage((Entity)player);
              player.func_70015_d(5);
            } 
          });
    } 
  }
  
  public String getID() {
    return "DASH_THROUGH";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGDashThroughAttack(attack, entity);
  }
  
  private static class BlockReplacement {
    private final BlockLocation location;
    
    private final Block originBlock;
    
    private final int originMetadata;
    
    public BlockLocation getLocation() {
      return this.location;
    }
    
    public Block getOriginBlock() {
      return this.originBlock;
    }
    
    public int getOriginMetadata() {
      return this.originMetadata;
    }
    
    public BlockReplacement(BlockLocation loc) {
      this.location = loc;
      this.originBlock = loc.getBlock();
      this.originMetadata = loc.getBlockMetadata();
    }
    
    public int hashCode() {
      return this.location.hashCode();
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj instanceof BlockReplacement) {
        BlockReplacement object = (BlockReplacement)obj;
        return (object.getLocation() != null && object.getLocation().equals(getLocation()));
      } 
      return false;
    }
  }
  
  private static class BlockReplacementThread extends Thread {
    private final Map<RPGDashThroughAttack.BlockReplacement, Long> replacementMap = new ConcurrentHashMap<>();
    
    public Map<RPGDashThroughAttack.BlockReplacement, Long> getReplacementMap() {
      return this.replacementMap;
    }
    
    public BlockReplacementThread() {
      setName("RPGDashThroughAttack/ReplacementThread");
    }
    
    public void run() {
      while (true) {
        try {
          Thread.sleep(100L);
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        } 
        this.replacementMap.forEach((replacement, endTime) -> {
              if (endTime.longValue() < System.currentTimeMillis()) {
                this.replacementMap.remove(replacement);
                FMLServerScheduler.getInstance().add(new Runnable[] { () });
              } 
            });
      } 
    }
    
    public void addBlockReplacement(RPGDashThroughAttack.BlockReplacement replacement, long endTime) {
      this.replacementMap.put(replacement, Long.valueOf(endTime));
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGDashThroughAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */