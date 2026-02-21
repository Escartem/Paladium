package fr.paladium.palamod.modules.shop.api;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements CresusCallback<CresusResponse> {
  public void onFail(CresusResponse response, Throwable throwable) {
    (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-5", "shop")).send(player);
  }
  
  public void onSuccess(CresusResponse response) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez reçu que §3" + (amount - fail) + "x " + stack.func_82833_r() + " §ccar votre inventaire était plein."));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */