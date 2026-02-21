package fr.paladium.pet.server.skill.listener.active;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.server.skill.handler.impl.active.RepairSkill;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class DestroyItemListener {
  @SubscribeEvent
  public void onBreak(PlayerDestroyItemEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    UUID uniqueId = player.func_110124_au();
    ItemStack stack = event.original;
    if (stack == null || !RepairSkill.VALUES.containsKey(uniqueId))
      return; 
    if (!(stack.func_77973_b() instanceof net.minecraft.item.ItemTool) && !(stack.func_77973_b() instanceof net.minecraft.item.ItemSword))
      return; 
    double value = ((Integer)RepairSkill.VALUES.get(uniqueId)).intValue();
    double random = Math.random() * 100.0D;
    RepairSkill.VALUES.remove(uniqueId);
    if (random > value) {
      PetTranslateEnum.MESSAGE_PET_REPAIR_FAILED.message((ICommandSender)player);
      return;
    } 
    ItemStack copy = copy(stack);
    ItemUtils.spawnItemInWorld(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, copy);
    PetTranslateEnum.MESSAGE_PET_REPAIR_SUCCESS.message((ICommandSender)player);
  }
  
  public ItemStack copy(ItemStack parent) {
    ItemStack itemstack = new ItemStack(parent.func_77973_b(), 1, 0);
    if (parent.field_77990_d != null)
      itemstack.field_77990_d = (NBTTagCompound)parent.field_77990_d.func_74737_b(); 
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\active\DestroyItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */