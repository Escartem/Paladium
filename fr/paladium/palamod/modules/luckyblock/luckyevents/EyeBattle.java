package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MouseHelper;

public class EyeBattle extends ALuckyEvent {
  private int count;
  
  private MouseHelper mouseHelper;
  
  public void perform(final EntityPlayerMP player, final int x, final int y, final int z) {
    final EntityEnderman entity = new EntityEnderman(player.field_70170_p);
    entity.func_70659_e(0.0F);
    entity.func_70107_b(x, y, (z + 1));
    player.func_70107_b(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
    boolean init = false;
    final long time = System.currentTimeMillis();
    final AtomicInteger taskId = new AtomicInteger(0);
    player.func_145747_a((IChatComponent)new ChatComponentText("§eVous commencez une bataille de regard..."));
    taskId.set(PaladiumScheduler.INSTANCE.runTaskAsyncTimer(new Runnable() {
            public void run() {
              if (entity == null || entity.field_70128_L)
                return; 
              if (player == null || entity == null || System.currentTimeMillis() - time >= 30000L) {
                if (entity != null)
                  entity.func_70106_y(); 
                PaladiumScheduler.INSTANCE.cancelTask(taskId.get());
              } else {
                entity.func_70606_j(entity.func_110138_aP());
                entity.func_94058_c("§cJe t'aurais ! §e" + (30L - (
                    System.currentTimeMillis() - time) / 1000L) + "");
                player.field_71135_a.func_147364_a(x, y, z, 5.0F, -45.0F);
                entity.field_70714_bg.field_75782_a.clear();
                entity.field_70715_bh.field_75782_a.clear();
                entity.func_70624_b(null);
                entity.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v + 1.0D, -190.0F, -70.0F);
              } 
            }
          }0L, 2L).getTaskId());
  }
  
  public String getName() {
    return "Bataille de regard";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "eyebattle";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\EyeBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */