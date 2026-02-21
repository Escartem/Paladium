package fr.paladium.palaforgeutils.lib.potion.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.potion.PotionUtils;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CommonPotionListener {
  @SubscribeEvent
  public void onUpdate(LivingEvent.LivingUpdateEvent e) {
    if (!(e.entity instanceof EntityPlayer))
      return; 
    HashMap<APotion, PotionEffect> potions = new HashMap<>();
    EntityPlayer player = (EntityPlayer)e.entity;
    player.func_70651_bq().forEach(effect -> {
          PotionEffect potionEffect = (PotionEffect)effect;
          APotion potion = PotionUtils.getPotionById(potionEffect.func_76456_a());
          if (potion != null) {
            potions.put(potion, potionEffect);
            potion.tick(player, potionEffect);
          } 
        });
    potions.forEach((potion, potionEffect) -> potion.tickAfter(player, potionEffect));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\listener\CommonPotionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */