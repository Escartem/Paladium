package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.achievements.types.BreakItemAchievement;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class BreakItemListener {
  @SubscribeEvent
  public void onBreak(PlayerDestroyItemEvent event) {
    if (event.isCanceled())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    UUID uniqueId = player.func_110124_au();
    ItemStack stack = event.original;
    if (stack == null || stack.func_77973_b() == null)
      return; 
    BreakItemAchievement.performCheck((EntityPlayer)player, stack.func_77973_b());
  }
  
  @SubscribeEvent
  public void onLivingHurt(LivingHurtEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
    for (int i = 0; i < 4; i++) {
      ItemStack armor = player.func_82169_q(i);
      if (armor != null && armor.func_77973_b() != null && 
        armor.func_77960_j() >= armor.func_77958_k() - 1)
        BreakItemAchievement.performCheck((EntityPlayer)player, armor.func_77973_b()); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\BreakItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */