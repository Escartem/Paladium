package fr.paladium.palajobs.server.managers;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    (new Notification(Notification.NotificationType.SUCCESS, "+ " + reward.quantity + "$", "paladium")).send(playerEntity);
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\managers\JobsManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */