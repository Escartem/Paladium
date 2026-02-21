package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.RealButcherShopEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ButcherData {
  public static final long DURATION_MILLIS = TimeUnit.SECONDS.toMillis(60L);
  
  public static final int FIRST_REWARD_AMOUNT = 40;
  
  public static final int SECOND_REWARD_AMOUNT = 50;
  
  public static final int THIRD_REWARD_AMOUNT = 60;
  
  private long expirationMillis;
  
  private int score;
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public ButcherData(EntityPlayerMP player, List<UUID> entities) {
    this.expirationMillis = System.currentTimeMillis() + DURATION_MILLIS;
    this.score = 0;
    MonthlyUtils.startTimedHeliosEvent(player, RealButcherShopEvent.class, DURATION_MILLIS, System.currentTimeMillis());
  }
  
  public int incrementScore() {
    this.score++;
    return this.score;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public boolean giveReward(EntityPlayerMP player) {
    int score = getScore();
    if (score == 40) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(Items.field_151078_bh, 64));
      return true;
    } 
    if (score == 50) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.PALADIUM_SWORD, 1));
      return true;
    } 
    if (score == 60) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.BUTCHER_KNIFE, 1));
      return true;
    } 
    return false;
  }
  
  public boolean isExpired(long now) {
    return (now > this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\ButcherData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */