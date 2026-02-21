package fr.paladium.palamod.modules.luckyblock.structures.sound.tasks;

import fr.paladium.helios.module.actionbar.ModuleActionBar;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.enums.SoundTurn;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class SoundWaveRunnable extends TimerTask {
  private final SoundStructure structure;
  
  private final SoundTurn turn;
  
  public SoundWaveRunnable(SoundStructure structure) {
    this.structure = structure;
    this.turn = SoundTurn.BOT;
  }
  
  public void run() {
    EntityPlayer player = this.structure.getTargetedPlayer();
    if (this.structure == null || player == null) {
      this.structure.onExpire();
      cancel();
      return;
    } 
    SoundWave currentWave = this.structure.getWave(this.structure.getCurrentWaveIndex());
    if (currentWave == null) {
      cancel();
      return;
    } 
    int timer = currentWave.getWaveTimer();
    if (timer > 0) {
      S29PacketSoundEffect packet = new S29PacketSoundEffect("random.click", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)packet);
      String message = "§c§lAttention ! §e§lUn son va être joué dans " + timer + " seconde" + ((timer > 1) ? "s" : "") + ", soyez attentif !";
      ModuleActionBar.getInstance().sendActionBar((EntityPlayerMP)player, message, TimeUnit.SECONDS.toMillis(2L));
      if (timer == 5)
        PlayerUtils.sendMessage(player, message); 
      currentWave.decrementTimer();
      return;
    } 
    LocatedBlock locatedBlock = this.structure.getRandomSoundBlock();
    if (locatedBlock == null) {
      cancel();
      return;
    } 
    int index = currentWave.getBotIndex();
    if (index == 0)
      PlayerUtils.sendMessage(player, "§c§lAttention ! §e§lUn son va être joué, soyez attentif !"); 
    if (!this.structure.sound(locatedBlock, currentWave, SoundTurn.BOT)) {
      cancel();
      return;
    } 
    if (this.structure.isCurrentWaveFinished(SoundTurn.BOT)) {
      PlayerUtils.sendMessage(player, "§cStimulation terminée ! §eC'est votre tour de jouer !");
      cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\tasks\SoundWaveRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */