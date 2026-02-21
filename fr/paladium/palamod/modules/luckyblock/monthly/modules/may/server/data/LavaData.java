package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VaTeLaver;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class LavaData {
  public static final long DURATION = TimeUnit.MINUTES.toMillis(1L);
  
  private static final String REWARD_MESSAGE = "§aTu as réussi à te baigner dans la lave !";
  
  private static final String FAIL_MESSAGE = "§cTu n'as pas réussi à te baigner dans la lave !";
  
  private final UUID playerUniqueId;
  
  private boolean failed;
  
  private final long eventExpirationMillis;
  
  private final long lavaExpirationMillis;
  
  private DoubleLocation lavaLocation;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public boolean isFailed() {
    return this.failed;
  }
  
  public long getEventExpirationMillis() {
    return this.eventExpirationMillis;
  }
  
  public long getLavaExpirationMillis() {
    return this.lavaExpirationMillis;
  }
  
  public DoubleLocation getLavaLocation() {
    return this.lavaLocation;
  }
  
  public LavaData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.eventExpirationMillis = System.currentTimeMillis() + DURATION;
    this.lavaExpirationMillis = -1L;
    this.failed = false;
  }
  
  public boolean isExpired(EntityPlayerMP player, long now) {
    if (player == null)
      return true; 
    int x = MathHelper.func_76128_c(player.field_70165_t);
    int y = MathHelper.func_76128_c(player.field_70163_u);
    int z = MathHelper.func_76128_c(player.field_70161_v);
    Block block = player.field_70170_p.func_147439_a(x, y, z);
    if ((block.equals(Blocks.field_150353_l) || block.equals(Blocks.field_150356_k)) && 
      !this.failed) {
      MonthlyUtils.stopHeliosEvent(player, VaTeLaver.class);
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as réussi à te baigner dans la lave !" });
      return true;
    } 
    if (now >= this.eventExpirationMillis) {
      if (!this.failed) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi à te baigner dans la lave !" });
        if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
          this.lavaLocation = new DoubleLocation(x, y, z);
          this.failed = true;
          player.field_70170_p.func_147449_b(x, y, z, Blocks.field_150353_l);
        } 
        return false;
      } 
      int lavaX = MathHelper.func_76128_c(this.lavaLocation.getX());
      int lavaY = MathHelper.func_76128_c(this.lavaLocation.getY());
      int lavaZ = MathHelper.func_76128_c(this.lavaLocation.getZ());
      if (player.field_70170_p.func_147439_a(lavaX, lavaY, lavaZ).equals(Blocks.field_150353_l) && EventUtils.canInteract((EntityPlayer)player, lavaX, lavaY, lavaZ))
        player.field_70170_p.func_147468_f(lavaX, lavaY, lavaZ); 
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\server\data\LavaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */