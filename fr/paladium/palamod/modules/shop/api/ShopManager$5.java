package fr.paladium.palamod.modules.shop.api;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.achievements.types.ShopSellAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopSellCountAchievement;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.palamod.modules.shop.utils.BigBrotherUtils;
import fr.paladium.palapass.common.quest.shop.ShopSellQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse response) {
    if (response.transactionSuccess()) {
      player.field_71071_by.field_70459_e = true;
      ShopSellQuest.trigger((EntityPlayer)player, stack, amount);
      BigBrotherUtils.sell(player, itemName, amount, price);
      player.func_145747_a((IChatComponent)new ChatComponentText("§aVous venez de vendre §3" + amount + "x" + stack.func_82833_r() + "."));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §eMontant §8» §7" + price + "$"));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §eQuantité §8» §7" + amount + ""));
      ShopSellAchievement.performCheck((EntityPlayer)player);
      ShopSellCountAchievement.performCheck((EntityPlayer)player, price);
      (new Notification(Notification.NotificationType.SUCCESS, "Vente effectuée de " + amount + "x" + stack.func_82833_r() + " (+" + String.format("%.2f", new Object[] { Double.valueOf(this.val$price) }) + "$)", "shop")).send(player);
      ShopManager.access$100(ShopManager.this, player, item, amount, price, false);
    } else {
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-3", "shop")).send(player);
    } 
  }
  
  public void onFail(CresusResponse response, Throwable throwable) {
    (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-4", "shop")).send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */