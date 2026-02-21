package fr.paladium.palamod.modules.luckyblock.events.halloween;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ZombieEventListener {
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entity instanceof EntityZombie))
      return; 
    EntityZombie zombie = (EntityZombie)event.entity;
    if (zombie.field_70170_p.field_72995_K)
      return; 
    if (!zombie.func_94057_bL().equals("§bZombie de sortie"))
      return; 
    ItemUtils.spawnItemInWorld(zombie.field_70170_p, zombie.field_70165_t, zombie.field_70163_u, zombie.field_70161_v, new ItemStack(ItemsRegister.PALADIUM_INGOT, 1));
  }
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    if (event.source == null || event.source.func_76346_g() == null)
      return; 
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    if (!(event.source.func_76346_g() instanceof EntityZombie))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    EntityZombie zombie = (EntityZombie)event.source.func_76346_g();
    String nameTag = zombie.func_94057_bL();
    if (nameTag == null || !nameTag.equals("§bZombie de sortie"))
      return; 
    player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 300, 2));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\halloween\ZombieEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */