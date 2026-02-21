package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener;

import com.google.common.util.concurrent.AtomicDouble;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemUniversalDollarStone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DollarStoneUpgradedListener {
  private final ConcurrentHashMap<UUID, Double> pendingDollarStoneMoney = new ConcurrentHashMap<>();
  
  @SubscribeEvent
  public void playerTickEvent(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START && !event.player.field_70170_p.field_72995_K) {
      final EntityPlayer player = event.player;
      ItemStack itemInLastSlot = event.player.field_71071_by.field_70462_a[8];
      if (itemInLastSlot == null || !(itemInLastSlot.func_77973_b() instanceof ItemUniversalDollarStone))
        return; 
      AtomicDouble moulaStoneMoney = new AtomicDouble();
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
        ItemStack itemstack = player.field_71071_by.field_70462_a[i];
        if (itemstack != null)
          if (itemInLastSlot.func_77973_b() instanceof ItemUniversalDollarStone) {
            ItemUniversalDollarStone moula_stone = (ItemUniversalDollarStone)itemInLastSlot.func_77973_b();
            moulaStoneMoney.addAndGet(moula_stone.check(event, itemInLastSlot, itemstack, player));
          }  
      } 
      double moulaStoneEffectiveMoney = moulaStoneMoney.get();
      if (this.pendingDollarStoneMoney.containsKey(player.func_110124_au())) {
        double newAmount = ((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).doubleValue() + moulaStoneEffectiveMoney;
        this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(newAmount));
        if (((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).doubleValue() >= 100.0D) {
          final int winningAmount = ((Double)this.pendingDollarStoneMoney.get(player.func_110124_au())).intValue();
          CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), winningAmount, "UPGRADED_DOLLAR_STONE", new CresusCallback<CresusResponse>() {
                public void onSuccess(CresusResponse arg0) {
                  player.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez de récupérer " + winningAmount + "$ avec votre Dollar Stone améliorée."));
                }
                
                public void onFail(CresusResponse arg0, Throwable arg1) {
                  player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Dollar Stone§8] §6Impossible de récupérer §e" + winningAmount + "$"));
                }
              });
          this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(0.0D));
        } 
      } else {
        this.pendingDollarStoneMoney.put(player.func_110124_au(), Double.valueOf(moulaStoneEffectiveMoney));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\listener\DollarStoneUpgradedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */