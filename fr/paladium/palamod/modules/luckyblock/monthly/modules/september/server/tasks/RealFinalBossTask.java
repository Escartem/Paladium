package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.tasks;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityBodyGuard;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RealFinalBossTask extends TimerTask {
  public static final int MUSIC_END_DELAY = 78;
  
  public static final int TOTAL_DELAY = 120;
  
  public static final int EXPERIENCE_AMOUNT = 2500;
  
  private final DoubleLocation spawnLocation;
  
  private final UUID playerUniqueId;
  
  private final EntityZombie entity;
  
  private int musicTimer;
  
  private int totalTimer;
  
  public RealFinalBossTask(EntityPlayerMP player) {
    this.spawnLocation = new DoubleLocation((Entity)player);
    this.playerUniqueId = player.func_110124_au();
    this.entity = spawnEntity((EntityPlayer)player);
    this.musicTimer = 78;
    this.totalTimer = 0;
  }
  
  public void run() {
    this.musicTimer++;
    this.totalTimer++;
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.playerUniqueId);
    if (player == null || player.field_70128_L) {
      stop(player, false);
      return;
    } 
    if (this.totalTimer >= 120) {
      stop(player, false);
      return;
    } 
    if (this.musicTimer >= 78) {
      MonthlyUtils.playSound(player, "real_final_boss");
      this.musicTimer = 0;
    } 
    if (this.entity == null || this.entity.field_70128_L) {
      stop(player, true);
      return;
    } 
  }
  
  private void stop(EntityPlayerMP player, boolean win) {
    if (player != null)
      MonthlyUtils.stopSound(player); 
    if (!win && this.entity != null && !this.entity.field_70128_L)
      this.entity.func_70106_y(); 
    if (win && player != null) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.BOSS_SUITCASE));
      MonthlyUtils.spawnExperience(player, 2500);
    } 
    cancel();
  }
  
  private EntityZombie spawnEntity(EntityPlayer player) {
    World world = player.field_70170_p;
    EntityBodyGuard zombie = new EntityBodyGuard(world);
    zombie.func_70634_a(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ());
    world.func_72838_d((Entity)zombie);
    return (EntityZombie)zombie;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\tasks\RealFinalBossTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */