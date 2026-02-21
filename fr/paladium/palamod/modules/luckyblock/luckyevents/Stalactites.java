package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class Stalactites extends ALuckyEvent {
  private int count;
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oAttention, une pluie de stalactites vont s'apparaître chaque seconde pendant 20 secondes sur toi"));
    player.func_70690_d(new PotionEffect(PLuckyBlock.STALACTITES.field_76415_H, 600, 0, false));
    this.count = 1;
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskTimer(new Runnable() {
          public void run() {
            if (Stalactites.this.count > 20) {
              player.func_82170_o(PLuckyBlock.STALACTITES.field_76415_H);
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
              return;
            } 
            Stalactites.this.tryUpdateBlock(player, (int)player.field_70165_t, (int)player.field_70163_u + 4, (int)player.field_70161_v);
            Stalactites.this.tryUpdateBlock(player, (int)player.field_70165_t, (int)player.field_70163_u + 5, (int)player.field_70161_v);
            Stalactites.this.count = Stalactites.this.count + 1;
          }
        }0L, 60L).getTaskId();
  }
  
  public boolean tryUpdateBlock(EntityPlayerMP player, int x, int y, int z) {
    World w = player.field_70170_p;
    Block b = w.func_147439_a(x, y, z);
    if (b == Blocks.field_150357_h)
      return false; 
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    w.func_147449_b(x, y, z, BlocksRegister.STALACTITES);
    return true;
  }
  
  public String getName() {
    return "SI j’étais toi je bougerais";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "Stalactites";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Stalactites.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */