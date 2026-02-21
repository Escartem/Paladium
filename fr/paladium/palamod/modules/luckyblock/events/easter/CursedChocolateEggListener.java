package fr.paladium.palamod.modules.luckyblock.events.easter;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.CooldownData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palawither.common.utils.WitherUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class CursedChocolateEggListener {
  private static final long USAGE_COOLDOWN = TimeUnit.HOURS.toMillis(1L);
  
  private static final String TAG_COOLDOWN = "cooldown";
  
  private static final CooldownData COOLDOWN_DATA = CooldownData.builder()
    .name("cursed_chocolate_egg")
    .duration(TimeUnit.SECONDS.toMillis(30L))
    .build();
  
  private static final String WITHER_ON_COOLDOWN_MESSAGE = "§cCe Wither est insensible pour le moment";
  
  private static final String WITHER_NOT_FOUND_MESSAGE = "§cCette entité n'est pas un Wither";
  
  private static final String EFFECT_MESSAGE = "§aLe Wither a été soigné de 10% de ses points de vie";
  
  @SubscribeEvent
  public void onInteractWithNPC(EntityInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    ItemStack stack = player.func_70694_bm();
    if (stack == null || stack.func_77973_b() == null || !stack.func_77973_b().equals(ItemsRegister.CURSED_CHOCOLATE_EGG) || !(event.target instanceof EntityLivingBase))
      return; 
    EntityLivingBase target = (EntityLivingBase)event.target;
    itemInteractionForEntity(stack, player, target);
  }
  
  public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
    if (!WitherUtils.isWither((Entity)entity)) {
      MonthlyUtils.sendMessage(player, new String[] { "§cCette entité n'est pas un Wither" });
      return false;
    } 
    if (CooldownUtils.isOnCooldown((Entity)entity, COOLDOWN_DATA.getName())) {
      MonthlyUtils.sendMessage(player, new String[] { "§cCe Wither est insensible pour le moment" });
      return false;
    } 
    NBTTagCompound tag = getOrCreateTag(stack);
    long now = System.currentTimeMillis();
    if (isItemOnCooldown(tag)) {
      long cooldown = tag.func_74763_f("cooldown");
      MonthlyUtils.sendMessage(player, new String[] { "§cProchaine utilisation dans " + DurationConverter.fromMillisToString(cooldown - now) });
      return false;
    } 
    float maxHealth = entity.func_110138_aP();
    float health = entity.func_110143_aJ();
    float newHealth = health + maxHealth * 0.1F;
    entity.func_70606_j(Math.min(newHealth, maxHealth));
    MonthlyUtils.sendMessage(player, new String[] { "§aLe Wither a été soigné de 10% de ses points de vie" });
    MonthlyUtils.damageCurrentItem(player, stack);
    CooldownUtils.setCooldown(entity.func_110124_au(), COOLDOWN_DATA.getName(), COOLDOWN_DATA.getDuration());
    return true;
  }
  
  private boolean isItemOnCooldown(NBTTagCompound compound) {
    long now = System.currentTimeMillis();
    boolean onCooldown = false;
    if (compound.func_74764_b("cooldown")) {
      long cooldown = compound.func_74763_f("cooldown");
      onCooldown = (now < cooldown);
    } 
    if (!onCooldown)
      compound.func_74772_a("cooldown", now + USAGE_COOLDOWN); 
    return onCooldown;
  }
  
  private NBTTagCompound getOrCreateTag(ItemStack stack) {
    NBTTagCompound tag = stack.func_77978_p();
    if (tag == null) {
      tag = new NBTTagCompound();
      stack.func_77982_d(tag);
    } 
    return tag;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\easter\CursedChocolateEggListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */