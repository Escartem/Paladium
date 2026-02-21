package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DoubleCuboid;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;

public class AsteroidRainData {
  private static final int MAX_DROP_COUNT = 15;
  
  private static final long DROP_DELAY = 2000L;
  
  private static final double RADIUS = 3.0D;
  
  private static final String TELEPORT_MESSAGE = "§eVous avez été téléporté au point le plus haut";
  
  private UUID playerUniqueId;
  
  private int dropCount;
  
  private long lastDropMillis;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getDropCount() {
    return this.dropCount;
  }
  
  public long getLastDropMillis() {
    return this.lastDropMillis;
  }
  
  public AsteroidRainData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.dropCount = 0;
    this.lastDropMillis = 0L;
    teleportAtHighestBlock(player);
  }
  
  private void teleportAtHighestBlock(EntityPlayerMP player) {
    int y = (int)Math.floor(player.field_70163_u);
    while (y < 255 && !player.field_70170_p.func_147437_c((int)Math.floor(player.field_70165_t), y, (int)Math.floor(player.field_70161_v)))
      y++; 
    DoubleLocation location = new DoubleLocation(player.field_70165_t, (y + 1), player.field_70161_v);
    location.teleportServer((EntityPlayer)player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous avez été téléporté au point le plus haut" });
  }
  
  private boolean canPerform(long now) {
    return (this.dropCount < 15 && now - this.lastDropMillis >= 2000L);
  }
  
  public void perform(EntityPlayerMP player, long now) {
    if (!canPerform(now))
      return; 
    this.dropCount++;
    this.lastDropMillis = System.currentTimeMillis();
    if (player == null)
      return; 
    DoubleCuboid attackCuboid = getAttackCuboid(player);
    DoubleLocation randomLocation = attackCuboid.getRandomLocation();
    DoubleLocation attackLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u + 10.0D, player.field_70161_v);
    double attackX = randomLocation.getX() - attackLocation.getX();
    double attackY = player.field_70121_D.field_72338_b + (player.field_70131_O / 2.0F) - attackLocation.getY() + (player.field_70131_O / 2.0F);
    double attackZ = randomLocation.getZ() - attackLocation.getZ();
    if (!EventUtils.canInteract((EntityPlayer)player, randomLocation.getX(), attackLocation.getY(), randomLocation.getZ()))
      return; 
    EntityLargeFireball fireBall = new EntityLargeFireball(player.field_70170_p, (EntityLivingBase)player, attackX, attackY, attackZ);
    fireBall.field_92057_e = 1;
    fireBall.field_70165_t = attackLocation.getX();
    fireBall.field_70163_u = attackLocation.getY();
    fireBall.field_70161_v = attackLocation.getZ();
    player.field_70170_p.func_72838_d((Entity)fireBall);
  }
  
  private DoubleCuboid getAttackCuboid(EntityPlayerMP player) {
    return new DoubleCuboid(player.field_70170_p, player.field_70165_t - 3.0D, player.field_70163_u, player.field_70161_v - 3.0D, player.field_70165_t + 3.0D, player.field_70163_u + 10.0D, player.field_70161_v + 3.0D);
  }
  
  public boolean isExpired() {
    return (this.dropCount >= 15);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\AsteroidRainData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */