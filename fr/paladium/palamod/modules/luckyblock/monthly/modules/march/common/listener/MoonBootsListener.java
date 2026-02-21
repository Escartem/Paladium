package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MoonBootsListener {
  @SubscribeEvent
  public void onPlayerInteract(LivingEvent.LivingUpdateEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player.field_71071_by.func_70440_f(0) != null && player.field_71071_by.func_70440_f(0).func_77973_b() == ItemsRegister.MOON_BOOTS && !WorldGuardUtils.isItemEffectBlocked((Entity)player, player.field_71071_by.func_70440_f(0))) {
      int effectAmplifier = 0;
      if (player.field_71071_by.func_70440_f(0).func_77942_o() && player.field_71071_by.func_70440_f(0).func_77978_p().func_74764_b("endium"))
        effectAmplifier = 3; 
      player.func_70690_d(new PotionEffect(Potion.field_76430_j.func_76396_c(), MonthlyUtils.translateSecondsToTicks(1), effectAmplifier));
      if (player.field_71071_by.func_70440_f(0).func_77942_o() && player.field_71071_by.func_70440_f(0).func_77978_p().func_74764_b("endium") && player.field_71071_by.func_70440_f(0).func_77978_p().func_74767_n("endium"))
        return; 
      if (player.field_70173_aa % 20 == 0)
        player.field_71071_by.func_70440_f(0).func_77972_a(3, (EntityLivingBase)player); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\listener\MoonBootsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */