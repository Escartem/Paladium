package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

public class ExperienceEventHandler {
  @SubscribeEvent
  public void onEarn(PlayerPickupXpEvent event) {
    EntityPlayer player = event.entityPlayer;
    ItemStack held = player.func_70694_bm();
    if (held == null)
      return; 
    int enchantmentLevel = MonthlyUtils.getEnchantmentLevel(held, PLuckyBlock.EXPERIENCE);
    if (enchantmentLevel == 0)
      return; 
    event.orb.field_70530_e = getBonus(event.orb.field_70530_e, enchantmentLevel);
  }
  
  private int getBonus(int experience, int level) {
    float experienceBonus = 0.0F;
    switch (level) {
      case 1:
        experienceBonus = 0.01F;
        break;
      case 2:
        experienceBonus = 0.03F;
        break;
      case 3:
        experienceBonus = 0.05F;
        break;
    } 
    int bonus = (int)(experience * experienceBonus);
    return experience + bonus;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\events\ExperienceEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */