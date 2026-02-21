package fr.paladium.palamod.modules.apet.server.skill.handler;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlessedGrowthSkill extends ASkillHandler {
  public static final String ID = "blessed_growth";
  
  public static final int VANILLA_MAX_STAGE = 7;
  
  public BlessedGrowthSkill() {
    super("blessed_growth");
  }
  
  private Cuboid getCuboid(EntityPlayerMP player, int radius) {
    return new Cuboid(player.field_70165_t - radius, player.field_70163_u - radius, player.field_70161_v - radius, player.field_70165_t + radius, player.field_70163_u + radius, player.field_70161_v + radius);
  }
  
  public boolean harvest(EntityPlayerMP player, int radius) {
    World world = player.field_70170_p;
    Cuboid cuboid = getCuboid(player, radius);
    for (Location location : cuboid.getLocations()) {
      int blockX = location.getBlockX();
      int blockY = location.getBlockY();
      int blockZ = location.getBlockZ();
      Block block = world.func_147439_a(blockX, blockY, blockZ);
      if (block == null || block.isAir((IBlockAccess)world, blockX, blockY, blockZ))
        continue; 
      if (!(block instanceof net.minecraft.block.BlockCrops) && !(block instanceof net.minecraft.block.BlockStem) && !(block instanceof BaseBlockCrops))
        continue; 
      int maxStage = 7;
      if (block instanceof BaseBlockCrops)
        maxStage = ((BaseBlockCrops)block).getStageMax() - 1; 
      int currentStage = world.func_72805_g(blockX, blockY, blockZ);
      if (currentStage >= maxStage || !PetUtils.canInteract((EntityPlayer)player, block, blockX, blockY, blockZ))
        continue; 
      world.func_72921_c(blockX, blockY, blockZ, maxStage, 2);
      world.func_147471_g(blockX, blockY, blockZ);
      world.func_147444_c(blockX, blockY, blockZ, block);
    } 
    return true;
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    int intValue = (int)Math.floor(value);
    return harvest(player, intValue);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\skill\handler\BlessedGrowthSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */