package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEndiumMagicBell extends TileEntity {
  public static final int MAX_USAGE_AMOUNT = 10;
  
  public void setUsageAmount(int usageAmount) {
    this.usageAmount = usageAmount;
  }
  
  public void setLastUsedMillis(long lastUsedMillis) {
    this.lastUsedMillis = lastUsedMillis;
  }
  
  public static final long USAGE_COOLDOWN = TimeUnit.HOURS.toMillis(6L);
  
  private static final int RADIUS = 100;
  
  private static final String SOUND_NAME = "endium_bell";
  
  private static final String ON_COOLDOWN_MESSAE = "§cVous devez attendre %s avant de sonner à nouveau cette cloche.";
  
  public static final String TAG_USAGE_AMOUNT = "usageAmount";
  
  public static final String TAG_LAST_USED_MILLIS = "lastUsedMillis";
  
  private int usageAmount;
  
  private long lastUsedMillis;
  
  public int getUsageAmount() {
    return this.usageAmount;
  }
  
  public long getLastUsedMillis() {
    return this.lastUsedMillis;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    if (this.usageAmount >= 10)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("usageAmount") || !compound.func_74764_b("lastUsedMillis"))
      return; 
    this.usageAmount = compound.func_74762_e("usageAmount");
    this.lastUsedMillis = compound.func_74763_f("lastUsedMillis");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("usageAmount", this.usageAmount);
    compound.func_74772_a("lastUsedMillis", this.lastUsedMillis);
  }
  
  private boolean isOnCooldown(long now) {
    return (now - this.lastUsedMillis < USAGE_COOLDOWN);
  }
  
  public void use(EntityPlayerMP player) {
    long now = System.currentTimeMillis();
    if (isOnCooldown(now)) {
      long remaining = USAGE_COOLDOWN - now - this.lastUsedMillis;
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("§cVous devez attendre %s avant de sonner à nouveau cette cloche.", new Object[] { DurationConverter.fromMillisToString(remaining) }) });
      return;
    } 
    this.usageAmount++;
    this.lastUsedMillis = now;
    List<EntityLivingBase> entities = this.field_145850_b.func_72872_a(EntityLivingBase.class, 
        
        AxisAlignedBB.func_72330_a((this.field_145851_c - 100), (this.field_145848_d - 100), (this.field_145849_e - 100), (this.field_145851_c + 100), (this.field_145848_d + 100), (this.field_145849_e + 100)));
    for (EntityLivingBase entity : entities) {
      List<PotionEffect> effects = Arrays.asList(new PotionEffect[] { new PotionEffect(Potion.field_76440_q.field_76415_H, 
              MonthlyUtils.translateSecondsToTicks(5), 5), new PotionEffect(Potion.field_76431_k.field_76415_H, 
              MonthlyUtils.translateSecondsToTicks(5), 5), new PotionEffect(Potion.field_76421_d.field_76415_H, 
              MonthlyUtils.translateSecondsToTicks(5), 5) });
      effects.forEach(effect -> effect.setCurativeItems(new ArrayList()));
      if (entity instanceof EntityPlayer)
        MonthlyUtils.playSound((EntityPlayerMP)entity, "endium_bell"); 
      for (PotionEffect effect : effects)
        entity.func_70690_d(effect); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEndiumMagicBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */