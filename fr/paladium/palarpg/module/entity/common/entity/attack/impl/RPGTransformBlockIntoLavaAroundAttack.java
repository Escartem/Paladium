package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import software.bernie.geckolib3.util.math.MathHelper;

public class RPGTransformBlockIntoLavaAroundAttack extends ARPGBaseAttack<RPGMobEntity> {
  private static final Random RANDOM = new Random();
  
  private final LinkedList<BlockReplacement> transformedBlocks = new LinkedList<>();
  
  private static BlockReplacementThread REPLACEMENT_THREAD = new BlockReplacementThread();
  
  private static final String ID = "TRANSFORM_BLOCK_INTO_LAVA_AROUND";
  
  private double radius;
  
  private long transformDuration;
  
  private int totalBlockTransformed;
  
  static {
    REPLACEMENT_THREAD.start();
  }
  
  public RPGTransformBlockIntoLavaAroundAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.radius = ((Double)getData("radius", Double.valueOf(16.0D))).doubleValue();
    this.transformDuration = ((Double)getData("transformDuration", Double.valueOf(5000.0D))).longValue();
    this.totalBlockTransformed = ((Integer)getData("totalBlockTransformed", Integer.valueOf(20))).intValue();
  }
  
  public boolean canPerform() {
    return (super.canPerform() && this.transformedBlocks.isEmpty());
  }
  
  public void onStart() {
    super.onStart();
    int entityX = MathHelper.floor(((RPGMobEntity)getEntity()).field_70165_t);
    int entityY = MathHelper.floor(((RPGMobEntity)getEntity()).field_70163_u);
    int entityZ = MathHelper.floor(((RPGMobEntity)getEntity()).field_70161_v);
    List<BlockLocation> candidates = new ArrayList<>();
    int r = MathHelper.floor(this.radius);
    int rSq = r * r;
    for (int dx = -r; dx <= r; dx++) {
      for (int dy = -r; dy <= r; dy++) {
        for (int dz = -r; dz <= r; dz++) {
          int distSq = dx * dx + dy * dy + dz * dz;
          if (distSq <= rSq) {
            int x = entityX + dx;
            int y = entityY + dy;
            int z = entityZ + dz;
            if (y >= 0 && y <= 255) {
              BlockLocation loc = new BlockLocation(((RPGMobEntity)getEntity()).field_70170_p, x, y, z);
              if (loc.isPresent() && !loc.getBlock().isAir((IBlockAccess)((RPGMobEntity)getEntity()).field_70170_p, x, y, z))
                candidates.add(loc); 
            } 
          } 
        } 
      } 
    } 
    if (candidates.isEmpty())
      return; 
    for (int i = 0; i < this.totalBlockTransformed; i++) {
      BlockLocation randomBlock = candidates.get(RANDOM.nextInt(candidates.size()));
      BlockReplacement replacement = new BlockReplacement(randomBlock);
      if (!this.transformedBlocks.contains(replacement))
        this.transformedBlocks.add(replacement); 
    } 
  }
  
  public void perform() {
    super.perform();
    if (getExecutingTick() < 50 && getExecutingTick() % 5 == 0) {
      int toTransform = this.totalBlockTransformed / 10;
      int counter = 0;
      int maxIter = toTransform * 2;
      while (counter < toTransform && maxIter > 0) {
        BlockReplacement replacement = this.transformedBlocks.pollFirst();
        if (replacement == null)
          break; 
        if (this.transformDuration > -1L)
          REPLACEMENT_THREAD.addBlockReplacement(replacement, System.currentTimeMillis() + this.transformDuration); 
        replacement.getLocation().setBlock(Blocks.field_150353_l);
        counter++;
        maxIter--;
      } 
    } 
  }
  
  public void onEnd() {
    super.onEnd();
    this.transformedBlocks.clear();
  }
  
  public String getID() {
    return "TRANSFORM_BLOCK_INTO_LAVA_AROUND";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGTransformBlockIntoLavaAroundAttack(attack, entity);
  }
  
  public RPGTransformBlockIntoLavaAroundAttack() {}
  
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
    private final Map<RPGTransformBlockIntoLavaAroundAttack.BlockReplacement, Long> replacementMap = new ConcurrentHashMap<>();
    
    public Map<RPGTransformBlockIntoLavaAroundAttack.BlockReplacement, Long> getReplacementMap() {
      return this.replacementMap;
    }
    
    public BlockReplacementThread() {
      setName("RPGTransformBlockIntoLavaAroundAttack/ReplacementThread");
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
    
    public void addBlockReplacement(RPGTransformBlockIntoLavaAroundAttack.BlockReplacement replacement, long endTime) {
      this.replacementMap.put(replacement, Long.valueOf(endTime));
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGTransformBlockIntoLavaAroundAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */