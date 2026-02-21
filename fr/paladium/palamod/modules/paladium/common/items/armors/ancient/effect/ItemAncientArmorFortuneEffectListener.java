package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.data.ItemAncientArmorPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ItemAncientArmorFortuneEffectListener {
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    final EntityPlayer player = event.player;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.FORTUNE) || ItemAncientArmorPlayerData.get(player).isFortuneOnCooldown())
      return; 
    ItemAncientArmorPlayerData.get(player).setFortune(60000L);
    final int value = ItemAncientArmor.isFull(player) ? 50 : 25;
    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), value, "ItemAncientArmor -> fortune", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse response) {
            (new Notification(Notification.NotificationType.INFO, "Vous avez gagnez " + value + "$ grâce à votre armure antique", "paladium")).send((EntityPlayerMP)player);
          }
          
          public void onFail(CresusResponse response, Throwable error) {
            error.printStackTrace();
          }
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorFortuneEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */