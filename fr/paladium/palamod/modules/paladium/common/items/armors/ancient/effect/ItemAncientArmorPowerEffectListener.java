package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class ItemAncientArmorPowerEffectListener {
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onSulfuricDamage(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer) || !"sulfuric_water".equals(event.source.field_76373_n))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.POWER))
      return; 
    event.setCanceled(true);
    if (ItemAncientArmor.isFull(player))
      player.func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 60, 0)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorPowerEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */