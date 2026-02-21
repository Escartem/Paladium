package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ChronoData {
  private static final String TELEPORT_MESSAGE = "&aVous avez été téléporté a l'emplacement de précédent de votre Chrono.";
  
  private static final String WARNING_MESSAGE = "&cATTENTION : &eVous allez être téléporté dans 30 secondes !";
  
  private static final long TELEPORTATION_TIME_MILLIS = TimeUnit.SECONDS.toMillis(30L);
  
  private final UUID playerUniqueId;
  
  private final long activateMillis;
  
  private final DoubleLocation location;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getActivateMillis() {
    return this.activateMillis;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public ChronoData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.activateMillis = System.currentTimeMillis();
    this.location = new DoubleLocation((Entity)player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cATTENTION : &eVous allez être téléporté dans 30 secondes !" });
  }
  
  public boolean isExpired() {
    return (System.currentTimeMillis() - this.activateMillis >= TELEPORTATION_TIME_MILLIS);
  }
  
  public boolean teleport(EntityPlayerMP player) {
    if (LuckyBlockUtils.isInCombat((EntityPlayer)player, true))
      return true; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez été téléporté a l'emplacement de précédent de votre Chrono." });
    player.field_70143_R = 0.0F;
    this.location.teleportServer((EntityPlayer)player);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\server\data\ChronoData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */