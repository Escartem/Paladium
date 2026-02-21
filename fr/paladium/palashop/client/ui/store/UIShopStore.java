package fr.paladium.palashop.client.ui.store;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.api.server.pb.dto.PBData;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.zephyrui.lib.ui.core.hook.store.UIStore;
import fr.paladium.zephyrui.lib.ui.core.hook.store.context.StoreContext;
import fr.paladium.zephyrui.lib.ui.core.hook.store.data.UIStoreData;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

@UIStoreData(id = "palashop", context = StoreContext.GLOBAL)
public class UIShopStore extends UIStore {
  private Signal<PBData> pbSignal;
  
  public Signal<PBData> getPbSignal() {
    return this.pbSignal;
  }
  
  public void init() {
    if (this.pbSignal == null)
      this.pbSignal = new Signal(new PBData(0L, 0L, 0L)); 
    PBManager.getData(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g))
      .thenAccept(pb -> {
          if (pb == null || ((PBData)this.pbSignal.getOrDefault()).equals(pb))
            return; 
          this.pbSignal.set(pb);
        }).exceptionally(throwable -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos points boutique", "palashop")).send();
          throwable.printStackTrace();
          return null;
        });
  }
  
  public void destroy() {
    this.pbSignal.getEventSet().clear();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\store\UIShopStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */