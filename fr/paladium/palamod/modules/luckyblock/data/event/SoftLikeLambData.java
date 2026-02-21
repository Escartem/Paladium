package fr.paladium.palamod.modules.luckyblock.data.event;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.SoftLikeALambEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SoftLikeLambData {
  public void setDamaged(boolean damaged) {
    this.damaged = damaged;
  }
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(30L);
  
  private static final String WIN_MESSAGE = "§aTu as été doux comme un agneau ! Voici un colorant spécial, essaie-le sur un mouton.";
  
  private static final String LOSE_MESSAGE = "§cTu as été méchant, tu as infligé des dégâts à un joueur ou un mob.";
  
  private final UUID playerUniqueId;
  
  private final long expirationMillis;
  
  private boolean damaged;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public boolean isDamaged() {
    return this.damaged;
  }
  
  public SoftLikeLambData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.expirationMillis = System.currentTimeMillis() + DURATION;
    this.damaged = false;
  }
  
  public boolean isExpired(long now) {
    return (now >= this.expirationMillis || this.damaged);
  }
  
  public void handleFinish(EntityPlayerMP player) {
    if (player == null)
      return; 
    MonthlyUtils.stopHeliosEvent(player, SoftLikeALambEvent.class);
    if (this.damaged) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu as été méchant, tu as infligé des dégâts à un joueur ou un mob." });
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as été doux comme un agneau ! Voici un colorant spécial, essaie-le sur un mouton." });
    for (int i = 0; i < 3; i++)
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.RAINBOW_COLORING, 1)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\data\event\SoftLikeLambData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */