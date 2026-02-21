package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.security.SecureRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class StormLighting extends ALuckyEvent {
  private double xR;
  
  private double zR;
  
  private int count;
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oAttention, une pluie d'éclairs vont s'apparaître toutes les 3 secondes pendant 30 secondes."));
    this.xR = player.field_70165_t + 1.0D;
    this.zR = player.field_70161_v;
    this.count = 1;
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskTimer(new Runnable() {
          public void run() {
            if (StormLighting.this.count > 10 || player == null) {
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
              return;
            } 
            StormLighting.this.sendLighting(player);
            StormLighting.this.sendMessagePreLighting(player);
            StormLighting.this.count = StormLighting.this.count + 1;
          }
        },  0L, 60L).getTaskId();
  }
  
  private void sendMessagePreLighting(EntityPlayerMP player) {
    SecureRandom secureRandom = new SecureRandom();
    this.xR = (secureRandom.nextInt(8) + -4);
    this.zR = (secureRandom.nextInt(8) + -4);
    this.xR = Math.round(this.xR + player.field_70165_t);
    this.zR = Math.round(this.zR + player.field_70161_v);
    player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oAttention, il y a un éclair qui va apparaître en x:" + this.xR + " y:" + 
          
          Math.round(player.field_70163_u) + " z:" + this.zR));
  }
  
  private void sendLighting(EntityPlayerMP player) {
    if (!player.field_70170_p.field_72995_K) {
      WorldServer worldServer = (WorldServer)player.func_130014_f_();
      worldServer.func_72942_c((Entity)new EntityLightningBolt(player.field_70170_p, this.xR, player.field_70163_u, this.zR));
    } 
  }
  
  public String getName() {
    return "Danse maintenant";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "danselighting";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\StormLighting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */