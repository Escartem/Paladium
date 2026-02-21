package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.DontMakeMessEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCMessPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MessRunnable extends TimerTask {
  private UUID playerUniqueId;
  
  private long expirationMillis;
  
  private long nextMessMillis;
  
  private Random random;
  
  public MessRunnable(EntityPlayer player) {
    this.playerUniqueId = player.func_110124_au();
    this.expirationMillis = System.currentTimeMillis() + DontMakeMessEvent.DURATION;
    this.nextMessMillis = 0L;
    this.random = new Random();
  }
  
  public void run() {
    EntityPlayerMP player = MonthlyUtils.getPlayer(this.playerUniqueId);
    long now = System.currentTimeMillis();
    if (player == null || isExpired(now))
      cancel(); 
    if (now >= this.nextMessMillis) {
      setNextMessMillis(now);
      makeMess(player);
    } 
  }
  
  private void makeMess(EntityPlayerMP player) {
    PalaMod.network.sendTo((IMessage)new SCMessPacket(), player);
    MonthlyUtils.playSound(player, "mess");
  }
  
  public void setNextMessMillis(long now) {
    int value = MonthlyUtils.getRandomValue(this.random, 10, 30);
    this.nextMessMillis = now + value * 1000L;
  }
  
  private boolean isExpired(long now) {
    return (now >= this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tasks\MessRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */