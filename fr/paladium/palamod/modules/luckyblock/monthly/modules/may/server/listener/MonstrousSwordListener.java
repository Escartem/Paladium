package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MonstrousSwordListener {
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    if (!(event.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.source.func_76346_g();
    if (player.field_71071_by.func_70448_g() == null || !player.field_71071_by.func_70448_g().func_77973_b().equals(ItemsRegister.MONSTROUS_SWORD))
      return; 
    EntityLivingBase entityLivingBase = event.entityLiving;
    ItemStack drop = getSpecialDrop((Entity)entityLivingBase);
    if (drop != null) {
      entityLivingBase.func_70099_a(drop, 0.0F);
      ItemStack monstrousSword = player.field_71071_by.func_70448_g();
      MonthlyUtils.damageCurrentItem((EntityPlayer)player, monstrousSword, monstrousSword.func_77958_k() / 5);
    } 
  }
  
  private ItemStack getSpecialDrop(Entity mob) {
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityGoat)
      return new ItemStack(ItemsRegister.GOAT_SHOE); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnail)
      return new ItemStack(ItemsRegister.SNAIL_SHELL); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityPanda)
      return new ItemStack(ItemsRegister.PANDA_DROOL); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityCrab)
      return new ItemStack(ItemsRegister.CRAB_PLIERS); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityParrot)
      return new ItemStack(ItemsRegister.PARROT_FEATHER); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityDolphin)
      return new ItemStack(ItemsRegister.DOLPHIN_NOISE_BOX); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityJellyFish)
      return new ItemStack(ItemsRegister.MEDUSE_HOOK); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntitySnake)
      return new ItemStack(ItemsRegister.SNAKE_VENOM); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityTurtle)
      return new ItemStack(ItemsRegister.TURTLE_SCALES); 
    if (mob instanceof fr.paladium.palamod.modules.hunter.entites.EntityElephant)
      return new ItemStack(ItemsRegister.TUSK); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\server\listener\MonstrousSwordListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */