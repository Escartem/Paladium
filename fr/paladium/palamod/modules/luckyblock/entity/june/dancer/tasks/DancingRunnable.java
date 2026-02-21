package fr.paladium.palamod.modules.luckyblock.entity.june.dancer.tasks;

import fr.paladium.helios.module.actionbar.ModuleActionBar;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class DancingRunnable extends TimerTask {
  private final EntityDancer entity;
  
  private int timer;
  
  public DancingRunnable(EntityDancer entity) {
    this.entity = entity;
    this.timer = 10;
  }
  
  public void run() {
    if (this.entity == null || this.entity.field_70128_L || this.entity.getTargetedPlayer() == null) {
      assert this.entity != null;
      if (!this.entity.field_70128_L)
        this.entity.func_70106_y(); 
      cancel();
      return;
    } 
    EntityPlayer player = this.entity.getTargetedPlayer();
    if (this.timer == 10)
      this.entity.resetPosition(); 
    if (this.timer > 0) {
      String message = "§eDébut de la danse dans §d" + this.timer + "§e secondes !";
      S29PacketSoundEffect packet = new S29PacketSoundEffect("random.click", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)packet);
      ModuleActionBar.getInstance().sendActionBar((EntityPlayerMP)player, message, TimeUnit.SECONDS.toMillis(2L));
      this.timer--;
      return;
    } 
    DanceDirection direction = this.entity.getNextDanceDirection();
    if (direction == DanceDirection.FINISHED) {
      this.timer = 10;
      this.entity.setDanceIndex(0);
      return;
    } 
    if (!this.entity.dance(direction))
      ModuleTitle.getInstance().sendTitle(new MinecraftTitle(
            PlayerUtils.getPrefix(), "§cVotre danseur est bloqué !", 250L, TimeUnit.SECONDS
            .toMillis(1L), 250L), (EntityPlayerMP)player); 
  }
  
  public void reset() {
    this.timer = 10;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\dancer\tasks\DancingRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */