package fr.paladium.pet.server.assignement.handler.impl;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemAssignmentHandler extends AAssignmentHandler {
  public ItemAssignmentHandler() {
    super(AssignmentType.ITEM);
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object object) {
    if (!(object instanceof ItemStack) || assignment.getStack() == null)
      return 0.0D; 
    ItemStack stack = (ItemStack)object;
    if (stack.func_77973_b() != assignment.getStack().func_77973_b() || stack.func_77960_j() != assignment.getStack().func_77960_j())
      return 0.0D; 
    if (stack.field_77994_a <= 0)
      return 0.0D; 
    return stack.field_77994_a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\impl\ItemAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */