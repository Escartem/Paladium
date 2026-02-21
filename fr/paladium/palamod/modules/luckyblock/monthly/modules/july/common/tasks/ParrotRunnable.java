package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks;

import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.ParrotEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ParrotRunnable extends TimerTask {
  private final UUID playerUniqueId;
  
  private final Random random;
  
  private final long expirationMillis;
  
  private boolean event;
  
  public ParrotRunnable(EntityPlayer player, boolean event) {
    this.playerUniqueId = player.func_110124_au();
    this.random = new Random();
    this.expirationMillis = System.currentTimeMillis() + ParrotEvent.PARROT_DURATION.longValue();
    this.event = event;
  }
  
  public void run() {
    EntityPlayer player = getPlayer();
    if (isExpired()) {
      cancel();
      if (player == null)
        return; 
      player.func_82170_o(PLuckyBlock.PARROT.field_76415_H);
      dropItems(player);
      return;
    } 
    if (player == null)
      return; 
    player.func_70690_d(new PotionEffect(PLuckyBlock.PARROT.field_76415_H, 620, 0));
    playParrotSound(player);
  }
  
  private void dropItems(EntityPlayer player) {
    if (!this.event)
      return; 
    InventoryUtils.giveOrDropitems(player, new ItemStack(BlocksRegister.PARROT_PLUSH));
    InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.PARROT_SEEDS, 3));
  }
  
  private void playParrotSound(EntityPlayer player) {
    if (this.random.nextInt(100) > 50)
      return; 
    String soundName = "parrot" + this.random.nextInt(6);
    MonthlyUtils.playSound((EntityPlayerMP)player, soundName);
  }
  
  private boolean isExpired() {
    return (System.currentTimeMillis() >= this.expirationMillis);
  }
  
  private EntityPlayer getPlayer() {
    return (EntityPlayer)PlayerUtils.getPlayer(this.playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tasks\ParrotRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */