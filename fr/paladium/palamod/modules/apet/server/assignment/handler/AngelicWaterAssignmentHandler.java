package fr.paladium.palamod.modules.apet.server.assignment.handler;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AngelicWaterAssignmentHandler extends AAssignmentHandler {
  private final List<Block> blocks;
  
  public AngelicWaterAssignmentHandler() {
    super(AssignmentType.ANGELIC_WATER);
    this.blocks = Collections.singletonList(BlocksRegister.FLUIDS_ANGELICWATER);
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object o) {
    World world = player.func_130014_f_();
    int blockX = MathHelper.func_76128_c(player.field_70165_t);
    int blockY = MathHelper.func_76128_c(player.field_70163_u);
    int blockZ = MathHelper.func_76128_c(player.field_70161_v);
    Block lowerBlock = world.func_147439_a(blockX, blockY, blockZ);
    Block upperBlock = world.func_147439_a(blockX, blockY + 1, blockZ);
    if (this.blocks.contains(lowerBlock) && this.blocks.contains(upperBlock))
      return 1.0D; 
    return 0.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\assignment\handler\AngelicWaterAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */