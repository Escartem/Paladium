package fr.paladium.palamod.modules.apet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.pet.common.registry.impl.PetItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PetLootListener {
  private static final double SNAIL_LOOT_CHANCE = 25.0D;
  
  private static final double SNAKE_LOOT_CHANCE = 75.0D;
  
  private static final double MAX_LOOT_CHANCE = 100.0D;
  
  @SubscribeEvent
  public void onLoot(LivingDeathEvent event) {
    EntityLivingBase entity = event.entityLiving;
    if (entity == null || event.source == null || event.source.func_76346_g() == null)
      return; 
    if (!(entity instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnake) && !(entity instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnail))
      return; 
    Entity killer = event.source.func_76346_g();
    if (!(killer instanceof net.minecraft.entity.player.EntityPlayerMP))
      return; 
    double maxChance = 0.0D;
    if (entity instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnake) {
      maxChance = 75.0D;
    } else if (entity instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnail) {
      maxChance = 25.0D;
    } 
    double randomDouble = Math.random() * 100.0D;
    if (randomDouble > maxChance)
      return; 
    ItemUtils.spawnItemInWorld(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, new ItemStack((Item)PetItemRegistry.BAIT));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\listener\PetLootListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */