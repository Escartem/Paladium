package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.tasks;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StormEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.StormData;
import java.util.HashSet;
import java.util.TimerTask;

public class StormTask extends TimerTask {
  public void run() {
    HashSet<StormData> datas = StormEvent.INSTANCE.getDatas();
    datas.removeIf(StormData::isExpired);
    datas.forEach(StormData::handleSalve);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\tasks\StormTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */