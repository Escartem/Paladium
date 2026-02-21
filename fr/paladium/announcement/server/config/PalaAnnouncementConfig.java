package fr.paladium.announcement.server.config;

import fr.paladium.announcement.server.AnnouncementServerProxy;
import fr.paladium.announcement.server.config.pojo.AnnouncementCategory;
import fr.paladium.announcement.server.config.pojo.GhostApi;
import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.List;

@ConfigFile(path = "announcement/api_config.json", blocking = true)
public class PalaAnnouncementConfig implements IConfig {
  private GhostApi ghostApi;
  
  private List<AnnouncementCategory> categories;
  
  private long fetchNewsFrom;
  
  public GhostApi getGhostApi() {
    return this.ghostApi;
  }
  
  public List<AnnouncementCategory> getCategories() {
    return this.categories;
  }
  
  public long getFetchNewsFrom() {
    return this.fetchNewsFrom;
  }
  
  public static PalaAnnouncementConfig get() {
    return AnnouncementServerProxy.getInstance().getApiConfig();
  }
  
  public void onLoaded() {
    PalaAnnouncementManager.getInstance().setupConfig(this.ghostApi);
  }
  
  public void onReloaded() {}
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\config\PalaAnnouncementConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */