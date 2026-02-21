package fr.paladium.palapass.server.config.quest.daily;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigFile(path = "palapass/quests/daily/daily-quests.json", blocking = true)
public class DailyQuestsConfig implements IConfig {
  private final List<Quest> dailyQuests = new ArrayList<>();
  
  public List<Quest> getDailyQuests() {
    return this.dailyQuests;
  }
  
  public void onLoaded() {
    PalapassManager manager = PalapassManager.getInstance();
    this.dailyQuests.forEach(quest -> {
          if (manager.getDailyQuests().containsKey(quest.getQuestType())) {
            ((List<Quest>)manager.getDailyQuests().get(quest.getQuestType())).add(quest);
            manager.getDailyQuestsByUuid().put(quest.getQuestUUID(), quest);
          } else {
            manager.getDailyQuests().put(quest.getQuestType(), new ArrayList(Arrays.asList((Object[])new Quest[] { quest })));
            manager.getDailyQuestsByUuid().put(quest.getQuestUUID(), quest);
          } 
        });
  }
  
  public void onReloaded() {
    PalapassManager.getInstance().getDailyQuests().clear();
    PalapassManager.getInstance().getDailyQuestsByUuid().clear();
    onLoaded();
  }
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\config\quest\daily\DailyQuestsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */