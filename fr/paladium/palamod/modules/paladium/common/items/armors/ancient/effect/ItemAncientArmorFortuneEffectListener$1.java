package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse response) {
    (new Notification(Notification.NotificationType.INFO, "Vous avez gagnez " + value + "$ grâce à votre armure antique", "paladium")).send((EntityPlayerMP)player);
  }
  
  public void onFail(CresusResponse response, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorFortuneEffectListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */