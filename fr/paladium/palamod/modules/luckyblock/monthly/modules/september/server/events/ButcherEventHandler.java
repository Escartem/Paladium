package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.RealButcherShopEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.ButcherData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ButcherEventHandler {
  private static final int ENCHANT_SIZE = 2;
  
  private static final int SHARPNESS_INDEX = 0;
  
  private static final int SMITE_INDEX = 1;
  
  private static final float DAMAGE_INCREMENT = 25.0F;
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    DamageSource source = event.source;
    if (source == null)
      return; 
    Entity entity = source.func_76346_g();
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    ItemStack heldItem = player.func_70694_bm();
    if (heldItem == null)
      return; 
    if (!heldItem.func_77973_b().equals(ItemsRegister.BUTCHER_KNIFE))
      return; 
    Enchantment[] wrongEnchants = new Enchantment[2];
    int sharpnessLevel = MonthlyUtils.getEnchantmentLevel(heldItem, Enchantment.field_77338_j);
    int smiteLevel = MonthlyUtils.getEnchantmentLevel(heldItem, Enchantment.field_77339_k);
    if (sharpnessLevel > 0)
      wrongEnchants[0] = Enchantment.field_77338_j; 
    if (smiteLevel > 0)
      wrongEnchants[1] = Enchantment.field_77339_k; 
    if (wrongEnchants[0] != null || wrongEnchants[1] != null)
      MonthlyUtils.removeEnchantments(player, heldItem, wrongEnchants); 
    Entity target = event.entity;
    if (!(target instanceof EntityZombie))
      return; 
    event.ammount += 25.0F;
  }
  
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (!(entity instanceof EntityZombie))
      return; 
    EntityZombie zombie = (EntityZombie)entity;
    if (event.source == null)
      return; 
    Entity source = event.source.func_76346_g();
    if (!(source instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)source;
    RealButcherShopEvent manager = RealButcherShopEvent.INSTANCE;
    ButcherData data = manager.get(player);
    if (data == null)
      return; 
    if (data.isExpired(System.currentTimeMillis())) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&aVous avez tué &c%s &azombies !", new Object[] { Integer.valueOf(data.getScore()) }) });
      manager.remove(player);
      return;
    } 
    int score = data.incrementScore();
    if (data.giveReward(player))
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez gagné une &drécompense &a!" }); 
    if (score == 60) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&aVous avez tué &c%s &azombies !", new Object[] { Integer.valueOf(score) }) });
      manager.remove(player);
      return;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\events\ButcherEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */