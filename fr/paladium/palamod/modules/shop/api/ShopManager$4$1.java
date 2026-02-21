package fr.paladium.palamod.modules.shop.api;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palamod.modules.achievements.types.ShopBuyAchievement;
import fr.paladium.palamod.modules.shop.utils.BigBrotherUtils;
import fr.paladium.palapass.common.quest.shop.ShopBuyQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse response) {
    if (!response.transactionSuccess()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous §cn'avez §cpas §cassez §cd'argent."));
      return;
    } 
    ItemStack copyStack = stack.func_77946_l();
    copyStack.field_77994_a = amount;
    PalaGiveManager.inst().give((EntityPlayer)player, copyStack);
    player.func_145747_a((IChatComponent)new ChatComponentText("§aVous venez d'acheter §3" + amount + "x" + stack.func_82833_r()));
    player.func_145747_a((IChatComponent)new ChatComponentText(" §ePrix §8» §7" + price + "$"));
    player.func_145747_a((IChatComponent)new ChatComponentText(" §eQuantité §8» §7" + amount + ""));
    ShopBuyAchievement.performCheck((EntityPlayer)player);
    BigBrotherUtils.buy(player, itemName, amount, price);
    ShopBuyQuest.trigger((EntityPlayer)player, stack, amount);
    ShopManager.access$100(this.this$1.this$0, player, item, amount, price, true);
    (new Notification(Notification.NotificationType.SUCCESS, "Achat effectué de " + amount + "x" + stack.func_82833_r() + " (-" + String.format("%.2f", new Object[] { Double.valueOf(this.this$1.val$price) }) + "$)", "shop")).send(player);
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {
    ShopManager.access$000(this.this$1.this$0, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager$4$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */