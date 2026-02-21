package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class ReadyForEverything extends ALuckyEvent {
  private static final int ANVIL_MIN = 7;
  
  private static final int ANVIL_MAX = 15;
  
  private double xR;
  
  private double zR;
  
  private int count;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int countMax = (new Random()).nextInt(9) + 7;
    player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oAttention, une pluie enclume de dollar vont s'apparaître toutes les 3 secondes."));
    player.func_70690_d(new PotionEffect(PLuckyBlock.ANVIL_MONEY.field_76415_H, 600, 0, false));
    this.xR = player.field_70165_t + 1.0D;
    this.zR = player.field_70161_v;
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    Runnable task = () -> {
        for (int i = 0; i < countMax; i++) {
          sendAnvil(player);
          sendPreAnvil(player);
          try {
            Thread.sleep(1600L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } 
        } 
        player.func_82170_o(PLuckyBlock.ANVIL_MONEY.field_76415_H);
      };
    int delay = 50;
    scheduler.schedule(task, 50L, TimeUnit.MILLISECONDS);
    scheduler.shutdown();
  }
  
  private void sendPreAnvil(EntityPlayerMP player) {
    SecureRandom secureRandom = new SecureRandom();
    this.xR = (secureRandom.nextInt(8) + -4);
    this.zR = (secureRandom.nextInt(8) + -4);
    this.xR = Math.round(this.xR + player.field_70165_t);
    this.zR = Math.round(this.zR + player.field_70161_v);
  }
  
  private void sendAnvil(EntityPlayerMP player) {
    if (!player.field_70170_p.field_72995_K) {
      WorldServer worldServer = (WorldServer)player.func_130014_f_();
      worldServer.func_147449_b((int)this.xR, (int)player.field_70163_u + 10, (int)this.zR, BlocksRegister.ANVIL_MONEY);
    } 
  }
  
  public String getName() {
    return "Prêt à tout";
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ReadyForEverything.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */