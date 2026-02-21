package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MLGPro extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack heldItemStack = player.func_70694_bm();
    if (heldItemStack != null) {
      int emptySlot = player.field_71071_by.func_70447_i();
      if (emptySlot == -1) {
        PlayerUtils.sendMessage((EntityPlayer)player, "Tu n'as pas de place dans ton inventaire... Dommage!");
        return;
      } 
      player.field_71071_by.func_70299_a(emptySlot, heldItemStack);
    } 
    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(Items.field_151131_as));
    final String playerId = FastUUID.toString((Entity)player);
    UsersManager.getAlmostCancelFallDamagePlayer().add(playerId);
    EventUtils.teleportPlayer(player, player.field_70165_t, 255.0D, player.field_70161_v);
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              Thread.sleep(30000L);
              if (UsersManager.getAlmostCancelFallDamagePlayer().contains(playerId))
                UsersManager.getAlmostCancelFallDamagePlayer().remove(playerId); 
            } catch (InterruptedException interruptedException) {}
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        },  0L).getTaskId();
  }
  
  public String getName() {
    return "MLG pro";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 110;
  }
  
  public String getTexture() {
    return "may/mlg_pro";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\MLGPro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */