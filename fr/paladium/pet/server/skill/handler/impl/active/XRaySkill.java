package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.common.utils.forge.Cuboid;
import fr.paladium.pet.common.utils.forge.Location;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import fr.paladium.pet.server.skill.handler.impl.active.data.BlockTickable;
import fr.paladium.pet.server.skill.listener.tickable.TickableListener;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class XRaySkill extends ASkillHandler {
  public static final String ID = "x_ray";
  
  private final List<Block> whitelistedBlocks;
  
  public XRaySkill() {
    super("x_ray");
    this.whitelistedBlocks = Arrays.asList(new Block[] { 
          Blocks.field_150348_b, Blocks.field_150347_e, Blocks.field_150351_n, (Block)Blocks.field_150354_m, Blocks.field_150355_j, (Block)Blocks.field_150358_i, Blocks.field_150353_l, (Block)Blocks.field_150356_k, Blocks.field_150346_d, (Block)Blocks.field_150349_c, 
          Blocks.field_150322_A, Blocks.field_150424_aL });
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (CommonModule.getInstance().getConfig().getServerType() != ServerType.MINAGE || value <= 1.0D)
      return false; 
    XraySkillTask task = new XraySkillTask(player, value, this.whitelistedBlocks);
    task.start();
    return true;
  }
  
  @Schedule(async = true, repeat = 1)
  public static class XraySkillTask extends ATask {
    private final EntityPlayerMP player;
    
    private final List<Block> whitelistedBlocks;
    
    private final double value;
    
    public XraySkillTask(EntityPlayerMP player, double value, List<Block> whitelistedBlocks) {
      super("XRay-" + UUID.randomUUID());
      this.player = player;
      this.value = value;
      this.whitelistedBlocks = whitelistedBlocks;
    }
    
    private boolean isWhitelisted(Block block) {
      return this.whitelistedBlocks.contains(block);
    }
    
    private Cuboid getCuboid(EntityPlayerMP player, World world, double value) {
      return new Cuboid(world, player.field_70165_t - value, player.field_70163_u - value, player.field_70161_v - value, player.field_70165_t + value, player.field_70163_u + value, player.field_70161_v + value);
    }
    
    public void pickaxe(EntityPlayerMP player, World world, double value) {
      Cuboid cuboid = getCuboid(player, world, value);
      long now = System.currentTimeMillis();
      int increment = 0;
      int currentBlocks = 0;
      for (Location location : cuboid.getLocations()) {
        int blockX = location.getBlockX();
        int blockY = location.getBlockY();
        int blockZ = location.getBlockZ();
        if (!world.func_72899_e(blockX, blockY, blockZ))
          continue; 
        Block block = world.func_147439_a(blockX, blockY, blockZ);
        if (block instanceof net.minecraft.block.BlockAir || block instanceof net.minecraft.block.BlockOre || !isWhitelisted(block) || !PetUtils.canInteract((EntityPlayer)player, block, blockX, blockY, blockZ))
          continue; 
        TickableListener.XRAY_TICKABLES.add(BlockTickable.of(blockX, blockY, blockZ, Blocks.field_150350_a, now + 2L * increment));
        currentBlocks++;
        if (currentBlocks >= 10) {
          increment++;
          currentBlocks = 0;
        } 
      } 
    }
    
    public void run() {
      if (this.player == null || this.player.field_70128_L)
        return; 
      pickaxe(this.player, this.player.field_70170_p, this.value);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\XRaySkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */