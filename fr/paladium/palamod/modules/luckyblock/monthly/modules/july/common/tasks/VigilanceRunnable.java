package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.VigilanceStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class VigilanceRunnable extends TimerTask {
  public static final long DURATION_MILLIS = TimeUnit.MINUTES.toMillis(2L);
  
  private static final String FAILURE_MESSAGE = "&cVous n'avez pas réussi votre devoir de vigie !";
  
  private static final String SUCCESS_MESSAGE = "&aVous avez réussi votre devoir de vigie !";
  
  private static final ItemStack REWARD_ITEM = new ItemStack(ItemsRegister.TELESCOPE);
  
  private final long startMillis;
  
  private final VigilanceStructure structure;
  
  private final UUID playerUniqueId;
  
  private final Location topLocation;
  
  public VigilanceRunnable(EntityPlayer player, VigilanceStructure structure) {
    this.startMillis = System.currentTimeMillis();
    this.playerUniqueId = player.func_110124_au();
    this.topLocation = new Location((Entity)player);
    this.structure = structure;
  }
  
  public void run() {
    EntityPlayer player = getPlayer();
    if (player == null) {
      end();
      return;
    } 
    Location currentLocation = new Location((Entity)player);
    if (currentLocation.getDistance(this.topLocation) > 2.0D) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'avez pas réussi votre devoir de vigie !" });
      end();
      return;
    } 
    if (!isFinished())
      return; 
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez réussi votre devoir de vigie !" });
    InventoryUtils.giveOrDropitems(player, REWARD_ITEM.func_77946_l());
    end();
  }
  
  private void end() {
    if (this.structure != null)
      this.structure.onExpire(); 
    cancel();
  }
  
  private boolean isFinished() {
    return (System.currentTimeMillis() - this.startMillis >= DURATION_MILLIS);
  }
  
  private EntityPlayer getPlayer() {
    return PlayerUtils.getPlayer(this.topLocation.getWorld(), this.playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\VigilanceRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */