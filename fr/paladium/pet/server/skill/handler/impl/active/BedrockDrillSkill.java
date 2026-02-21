package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.common.utils.forge.Cuboid;
import fr.paladium.pet.common.utils.forge.Location;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BedrockDrillSkill extends ASkillHandler {
  public static final String ID = "bedrock_drill";
  
  public BedrockDrillSkill() {
    super("bedrock_drill");
  }
  
  private Cuboid getCuboid(EntityPlayerMP player, World world, double value) {
    return new Cuboid(world, player.field_70165_t + value, player.field_70163_u - 1.0D, player.field_70161_v + value, player.field_70165_t, player.field_70163_u - 1.0D, player.field_70161_v);
  }
  
  public boolean drill(EntityPlayerMP player, World world, double value) {
    Cuboid cuboid = getCuboid(player, world, value);
    for (Location location : cuboid.getLocations()) {
      int blockX = location.getBlockX();
      int blockY = location.getBlockY();
      int blockZ = location.getBlockZ();
      Block block = world.func_147439_a(blockX, blockY, blockZ);
      if (!block.equals(Blocks.field_150357_h))
        continue; 
      Block upperBlock = world.func_147439_a(blockX, blockY + 1, blockZ);
      if (upperBlock.equals(Blocks.field_150357_h) || !PetUtils.canInteractBedrock((EntityPlayer)player, blockX, blockY, blockZ))
        continue; 
      world.func_147468_f(blockX, blockY, blockZ);
    } 
    return true;
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet) - 1.0D;
    if (value <= 1.0D)
      value = 1.0D; 
    value = Math.ceil(value);
    return drill(player, player.field_70170_p, value);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\BedrockDrillSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */