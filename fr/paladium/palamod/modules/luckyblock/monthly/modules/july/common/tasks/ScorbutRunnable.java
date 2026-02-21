package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ScorbutRunnable extends TimerTask {
  private static final int REPEAT_AMOUNT = 6;
  
  private static final PotionEffect WEAKNESS_POTION = new PotionEffect(Potion.field_76437_t.field_76415_H, 600, 0);
  
  private static final PotionEffect SLOWNESS_POTION = new PotionEffect(Potion.field_76421_d.field_76415_H, 600, 0);
  
  private static final ItemStack REWARD_ITEM = new ItemStack(ItemsRegister.SAILOR_TOOTH);
  
  private final UUID playerUniqueId;
  
  private int repeatCount;
  
  public ScorbutRunnable(EntityPlayer player) {
    this.repeatCount = 0;
    this.playerUniqueId = player.func_110124_au();
  }
  
  public void run() {
    EntityPlayer player = getPlayer();
    if (player == null) {
      cancel();
      return;
    } 
    this.repeatCount++;
    if (this.repeatCount >= 6)
      cancel(); 
    player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 600, 0));
    player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 600, 0));
    InventoryUtils.giveOrDropitems(player, REWARD_ITEM.func_77946_l());
  }
  
  private EntityPlayer getPlayer() {
    return (EntityPlayer)PlayerUtils.getPlayer(this.playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\ScorbutRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */