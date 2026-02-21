package fr.paladium.palapass.server.config.quest.season;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeasonQuestsConfig implements IConfig {
  private final List<Quest> seasonQuests = new ArrayList<>();
  
  public List<Quest> getSeasonQuests() {
    return this.seasonQuests;
  }
  
  private int month = -1;
  
  public int getMonth() {
    return this.month;
  }
  
  public void onLoaded() {
    PalapassManager manager = PalapassManager.getInstance();
    String path = ((ConfigFile)getClass().<ConfigFile>getAnnotation(ConfigFile.class)).path();
    this.month = ConfigItemUtils.parsePathConfigToMonth(path);
    this.seasonQuests.forEach(quest -> {
          if (manager.getSeasonQuests().containsKey(Integer.valueOf(this.month))) {
            ((List<Quest>)manager.getSeasonQuests().get(Integer.valueOf(this.month))).add(quest);
            manager.getSeasonQuestsByUuid().put(quest.getQuestUUID(), quest);
          } else {
            manager.getSeasonQuests().put(Integer.valueOf(this.month), new ArrayList(Arrays.asList((Object[])new Quest[] { quest })));
            manager.getSeasonQuestsByUuid().put(quest.getQuestUUID(), quest);
          } 
        });
  }
  
  public void onReloaded() {
    PalapassManager manager = PalapassManager.getInstance();
    String path = ((ConfigFile)getClass().<ConfigFile>getAnnotation(ConfigFile.class)).path();
    this.month = ConfigItemUtils.parsePathConfigToMonth(path);
    manager.getSeasonQuests().remove(Integer.valueOf(this.month));
    manager.getSeasonQuestsByUuid().clear();
    onLoaded();
  }
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\config\quest\season\SeasonQuestsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */