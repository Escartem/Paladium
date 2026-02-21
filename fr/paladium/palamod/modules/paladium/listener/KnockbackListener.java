package fr.paladium.palamod.modules.paladium.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.common.enchantment.EnchantmentKnockbackModifierEvent;

public class KnockbackListener {
  @SubscribeEvent
  public void onKnockback(EnchantmentKnockbackModifierEvent event) {
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\listener\KnockbackListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */