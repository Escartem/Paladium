package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ItemAncientArmorChaosEffectListener {
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onPlayerDamage(LivingHurtEvent event) {
    if (event.ammount <= 0.0F || !(event.entity instanceof EntityPlayer) || !(event.source.func_76364_f() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    EntityPlayer attacker = (EntityPlayer)event.source.func_76364_f();
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.CHAOS))
      return; 
    Random rand = player.func_70681_au();
    float random = rand.nextFloat();
    if (random > (ItemAncientArmor.isFull(player) ? 0.3F : 0.15F))
      return; 
    int slotA = rand.nextInt(36);
    int slotB = rand.nextInt(36);
    ItemStack itemA = attacker.field_71071_by.func_70301_a(slotA);
    ItemStack itemB = attacker.field_71071_by.func_70301_a(slotB);
    attacker.field_71071_by.func_70299_a(slotA, itemB);
    attacker.field_71071_by.func_70299_a(slotB, itemA);
    attacker.field_71071_by.func_70296_d();
    attacker.field_71069_bz.func_75142_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorChaosEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */