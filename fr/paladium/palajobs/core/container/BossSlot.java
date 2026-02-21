package fr.paladium.palajobs.core.container;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.boss.BossState;
import fr.paladium.palajobs.core.pojo.boss.JobBossEep;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.translate.common.texttotranslate.utils.TTTChat;
import java.util.AbstractMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BossSlot extends Slot {
  private EntityPlayer player;
  
  private ItemStack filter;
  
  public BossSlot(IInventory inv, int slot, int x, int y, EntityPlayer player, ItemStack filter) {
    super(inv, slot, x, y);
    this.player = player;
    this.filter = filter;
  }
  
  public boolean func_75214_a(ItemStack itemStack) {
    return itemStack.func_77969_a(this.filter);
  }
  
  public ItemStack func_75209_a(int count) {
    return super.func_75209_a(count);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void func_75218_e() {
    final ItemStack itemStack = func_75211_c();
    int multiplier = 0;
    if (itemStack == null || this.player == null)
      return; 
    if (itemStack.func_77969_a(this.filter))
      multiplier = 1; 
    if (multiplier < 1)
      return; 
    final int amount = itemStack.field_77994_a * multiplier;
    func_75215_d(null);
    if (!this.player.field_70170_p.field_72995_K && this.player instanceof EntityPlayerMP && JobsManager.getInstance().getApi() != null && JobsManager.getInstance().getBossData() != null && (JobsManager.getInstance().getBossData()).state == BossState.OPEN)
      JobsManager.getInstance().getApi().add(amount).enqueue(new Callback<String>() {
            public void onResponse(Call<String> call, Response<String> response) {
              if (!response.isSuccessful()) {
                InventoryUtils.addItem(BossSlot.this.player, itemStack);
                TTTChat.sendMessage(BossSlot.this.player, "notification.jobs.boss.slot.error", new Object[0]);
                (new Notification(Notification.NotificationType.ERROR, TTT.format(BossSlot.this.player, "notification.jobs.boss.slot.error", new Object[0]), "boss")).send((EntityPlayerMP)BossSlot.this.player);
                return;
              } 
              JobsPlayer jobsPlayer = JobsPlayer.get((Entity)BossSlot.this.player);
              if (jobsPlayer != null) {
                int lastValue = 0;
                String uuid = (JobsManager.getInstance().getBossData()).uuid.toString();
                if (jobsPlayer.getBossValue().containsKey(uuid))
                  lastValue = ((Integer)((Map.Entry)jobsPlayer.getBossValue().get(uuid)).getValue()).intValue(); 
                lastValue += amount;
                jobsPlayer.getBossValue().put(uuid, new AbstractMap.SimpleEntry<>((JobsManager.getInstance().getBossData()).type, Integer.valueOf(lastValue)));
                JobBossEep eep = JobBossEep.get((Entity)BossSlot.this.player);
                eep.getBossStat((JobsManager.getInstance().getBossData()).type.getName()).increaseDeposit(amount);
              } 
              (new Notification(Notification.NotificationType.INFO, TTT.format(BossSlot.this.player, "notification.jobs.boss.slot", new Object[] { Integer.valueOf(this.val$amount) }), "boss")).send((EntityPlayerMP)BossSlot.this.player);
              JobsManager.getInstance().fetchBossDataAsync();
            }
            
            public void onFailure(Call<String> call, Throwable error) {
              error.printStackTrace();
              InventoryUtils.addItem(BossSlot.this.player, itemStack);
              TTTChat.sendMessage(BossSlot.this.player, "notification.jobs.boss.slot.error", new Object[0]);
              (new Notification(Notification.NotificationType.ERROR, TTT.format(BossSlot.this.player, "notification.jobs.boss.slot.error", new Object[0]), "boss")).send((EntityPlayerMP)BossSlot.this.player);
            }
          }); 
  }
  
  public void func_82870_a(EntityPlayer p, ItemStack itemStack) {
    func_75208_c(itemStack);
    super.func_82870_a(p, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\container\BossSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */