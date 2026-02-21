package fr.paladium.palamod.modules.shop.api;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.palamod.modules.shop.PShop;
import fr.paladium.palamod.modules.shop.network.SCPacketShopItemListReply;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<Double> {
  public void onSuccess(Double money) {
    SCPacketShopItemListReply packet = new SCPacketShopItemListReply((short)0, new HashMap<>(), money.doubleValue());
    PShop.network.sendTo((IMessage)packet, player);
  }
  
  public void onFail(Double money, Throwable arg1) {
    ZUIServer.close(player);
    arg1.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */