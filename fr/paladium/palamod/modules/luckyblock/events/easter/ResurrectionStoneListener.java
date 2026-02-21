package fr.paladium.palamod.modules.luckyblock.events.easter;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ResurrectionStoneListener {
  @SubscribeEvent
  public void onLivingHurt(LivingHurtEvent event) {
    EntityLivingBase entity = event.entityLiving;
    if (entity == null || entity.field_70170_p.field_72995_K || !(entity instanceof EntityPlayer))
      return; 
    float currentHealth = entity.func_110143_aJ() - event.ammount;
    if (currentHealth > 0.0D)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    ItemStack target = new ItemStack(ItemsRegister.RESURRECTION_STONE);
    if (!InventoryUtils.haveRequiredItem((EntityPlayer)player, target))
      return; 
    if (LuckyBlockUtils.isInCombat((EntityPlayer)player))
      return; 
    player.func_70606_j(8.0F);
    player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 40, 0));
    player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 40, 5));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as ressuscité mais ta pierre de résurrection a été consommée." });
    InventoryUtils.removeItems((EntityPlayer)player, target, 1);
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\easter\ResurrectionStoneListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */