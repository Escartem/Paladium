package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class EyePatchRunnable extends TimerTask {
  private final UUID playerUniqueId;
  
  public EyePatchRunnable(EntityPlayer player) {
    this.playerUniqueId = player.func_110124_au();
  }
  
  public void run() {
    EntityPlayer player = getPlayer();
    if (player == null) {
      cancel();
      return;
    } 
    if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.EYE_PATCH)) {
      cancel();
      return;
    } 
    InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.EYE_PATCH));
  }
  
  private EntityPlayer getPlayer() {
    return (EntityPlayer)PlayerUtils.getPlayer(this.playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\EyePatchRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */