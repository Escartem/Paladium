package fr.paladium.pet.server.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class FeedListener {
  @SubscribeEvent
  public void onFeed(PlayerUseItemEvent.Finish event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    ItemStack stack = event.item;
    if (stack == null || !(stack.func_77973_b() instanceof ItemFood) || player.func_71024_bL().func_75116_a() == 20)
      return; 
    ItemFood food = (ItemFood)stack.func_77973_b();
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.STOMACH_ON_LEGS.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    int defaultFood = (int)(food.func_150906_h(stack) * 10.0F);
    int ret = (int)(defaultFood * (1.0D + response.getValueAsPercent(value)));
    int foodToAdd = ret - defaultFood;
    player.func_71024_bL().func_75122_a(foodToAdd, food.func_150906_h(stack));
    player.func_71024_bL().func_75118_a((EntityPlayer)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\FeedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */