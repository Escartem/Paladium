package fr.paladium.palajobs.core.container;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.boss.JobBossEep;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.translate.common.texttotranslate.utils.TTTChat;
import java.util.AbstractMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<String> {
  public void onResponse(Call<String> call, Response<String> response) {
    if (!response.isSuccessful()) {
      InventoryUtils.addItem(BossSlot.access$000(BossSlot.this), itemStack);
      TTTChat.sendMessage(BossSlot.access$000(BossSlot.this), "notification.jobs.boss.slot.error", new Object[0]);
      (new Notification(Notification.NotificationType.ERROR, TTT.format(BossSlot.access$000(BossSlot.this), "notification.jobs.boss.slot.error", new Object[0]), "boss")).send((EntityPlayerMP)BossSlot.access$000(BossSlot.this));
      return;
    } 
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)BossSlot.access$000(BossSlot.this));
    if (jobsPlayer != null) {
      int lastValue = 0;
      String uuid = (JobsManager.getInstance().getBossData()).uuid.toString();
      if (jobsPlayer.getBossValue().containsKey(uuid))
        lastValue = ((Integer)((Map.Entry)jobsPlayer.getBossValue().get(uuid)).getValue()).intValue(); 
      lastValue += amount;
      jobsPlayer.getBossValue().put(uuid, new AbstractMap.SimpleEntry<>((JobsManager.getInstance().getBossData()).type, Integer.valueOf(lastValue)));
      JobBossEep eep = JobBossEep.get((Entity)BossSlot.access$000(BossSlot.this));
      eep.getBossStat((JobsManager.getInstance().getBossData()).type.getName()).increaseDeposit(amount);
    } 
    (new Notification(Notification.NotificationType.INFO, TTT.format(BossSlot.access$000(BossSlot.this), "notification.jobs.boss.slot", new Object[] { Integer.valueOf(this.val$amount) }), "boss")).send((EntityPlayerMP)BossSlot.access$000(BossSlot.this));
    JobsManager.getInstance().fetchBossDataAsync();
  }
  
  public void onFailure(Call<String> call, Throwable error) {
    error.printStackTrace();
    InventoryUtils.addItem(BossSlot.access$000(BossSlot.this), itemStack);
    TTTChat.sendMessage(BossSlot.access$000(BossSlot.this), "notification.jobs.boss.slot.error", new Object[0]);
    (new Notification(Notification.NotificationType.ERROR, TTT.format(BossSlot.access$000(BossSlot.this), "notification.jobs.boss.slot.error", new Object[0]), "boss")).send((EntityPlayerMP)BossSlot.access$000(BossSlot.this));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\container\BossSlot$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */