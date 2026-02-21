package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionHandler {
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void imbueHandler(LivingAttackEvent event) {
    if (!event.entityLiving.field_70170_p.field_72995_K && 
      !event.isCanceled() && event.source instanceof EntityDamageSource && !(event.source instanceof net.minecraft.util.EntityDamageSourceIndirect)) {
      EntityDamageSource damageSource = (EntityDamageSource)event.source;
      if (damageSource.func_76346_g() != null && damageSource
        .func_76346_g() instanceof EntityLivingBase) {
        EntityLivingBase livingEntity = (EntityLivingBase)damageSource.func_76346_g();
        if (livingEntity.func_70644_a((Potion)PRegister_Potions.potionWither) && 
          !livingEntity.func_70644_a((Potion)PRegister_Potions.potionPoison))
          event.entityLiving.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100, 0)); 
        if (livingEntity.func_70644_a((Potion)PRegister_Potions.potionPoison) && 
          !livingEntity.func_70644_a((Potion)PRegister_Potions.potionWither))
          event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 10, 0)); 
        if (livingEntity.func_70644_a((Potion)PRegister_Potions.potionFire))
          event.entityLiving.func_70015_d(2); 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onFall(LivingHurtEvent event) {
    if (event.source != DamageSource.field_76379_h)
      return; 
    EntityLivingBase entity = event.entityLiving;
    if (entity.func_82165_m(PRegister_Potions.potionFeatherFalling.getPotionId())) {
      event.ammount = 0.0F;
      event.setCanceled(true);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\PotionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */