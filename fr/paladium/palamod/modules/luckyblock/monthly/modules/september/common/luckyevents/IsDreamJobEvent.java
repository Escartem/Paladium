package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.WorkerData;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class IsDreamJobEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le métier de rêve de la nouvelle génération ?";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "september/is_dream_job";
  
  public static IsDreamJobEvent INSTANCE;
  
  private final List<WorkerData> workers;
  
  public List<WorkerData> getWorkers() {
    return this.workers;
  }
  
  public IsDreamJobEvent() {
    INSTANCE = this;
    this.workers = new ArrayList<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    add(player);
  }
  
  public void add(EntityPlayerMP player) {
    WorkerData oldData = get(player);
    if (oldData != null)
      this.workers.remove(oldData); 
    WorkerData newData = new WorkerData(player);
    this.workers.add(newData);
  }
  
  public WorkerData get(EntityPlayerMP player) {
    return this.workers.stream()
      .filter(worker -> worker.getTargetUniqueId().equals(player.func_110124_au()))
      .findFirst()
      .orElse(null);
  }
  
  public String getName() {
    return "Le métier de rêve de la nouvelle génération ?";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "september/is_dream_job";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\IsDreamJobEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */