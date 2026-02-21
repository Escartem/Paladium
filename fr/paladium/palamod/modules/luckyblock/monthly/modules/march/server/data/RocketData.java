package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class RocketData {
  public static final String SOUND_NAME = "like_a_rocket";
  
  public static final long DURATION_MILLIS = TimeUnit.SECONDS.toMillis(15L);
  
  private final UUID playerUniqueId;
  
  private final long endMillis;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getEndMillis() {
    return this.endMillis;
  }
  
  public RocketData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.endMillis = System.currentTimeMillis() + DURATION_MILLIS;
  }
  
  public void playSound(EntityPlayerMP player) {
    if (player == null)
      return; 
    MonthlyUtils.playSound(player, "like_a_rocket");
  }
  
  public void giveItem(EntityPlayerMP player) {
    if (player == null)
      return; 
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.SUPERSONIC_ROCKET, 1));
  }
  
  public boolean isExpired(long now) {
    return (now >= this.endMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\RocketData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */