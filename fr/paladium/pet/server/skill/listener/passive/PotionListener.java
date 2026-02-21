package fr.paladium.pet.server.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class PotionListener {
  @SubscribeEvent
  public void onConsumePotion(PlayerUseItemEvent.Finish event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    ItemStack stack = event.item;
    if (stack == null || !(stack.func_77973_b() instanceof ItemPotion))
      return; 
    ItemPotion potion = (ItemPotion)stack.func_77973_b();
    if (ItemPotion.func_77831_g(stack.func_77960_j()) || potion.func_150892_m(stack))
      return; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.HERBALIST.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    List<PotionEffect> effects = potion.func_77832_l(stack);
    if (effects == null || effects.isEmpty())
      return; 
    for (PotionEffect effect : effects) {
      if (effect == null)
        continue; 
      Item item = Item.func_150899_d(effect.func_76456_a());
      if (item == null)
        continue; 
      if (item instanceof ItemPotion)
        continue; 
      int duration = (int)(effect.func_76459_b() * (1.0D + response.getValueAsPercent(value)));
      player.func_70690_d(new PotionEffect(effect.func_76456_a(), duration, effect.func_76458_c(), effect.func_82720_e()));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\PotionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */