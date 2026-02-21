package fr.paladium.pet.server.assignement.handler.impl;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;

public class DarkAssignmentHandler extends AAssignmentHandler {
  public DarkAssignmentHandler() {
    super(AssignmentType.DARK);
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object object) {
    int blockX = MathHelper.func_76128_c(player.field_70165_t);
    int blockY = MathHelper.func_76128_c(player.field_70163_u);
    int blockZ = MathHelper.func_76128_c(player.field_70161_v);
    float lightLevel = player.field_70170_p.func_72801_o(blockX, blockY + 1, blockZ);
    if (lightLevel <= AssignmentConfig.get().getMaxDarkAssignmentLightLevel())
      return 1.0D; 
    return 0.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\impl\DarkAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */