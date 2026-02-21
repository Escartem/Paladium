package fr.paladium.palamod.modules.stats.profile;

import fr.paladium.palamod.modules.stats.dto.PlayerStats;
import fr.paladium.palamod.modules.stats.profile.api.StatsApi;
import fr.paladium.palamod.modules.stats.profile.fetcher.StatsFetcher;
import fr.paladium.palamod.modules.stats.profile.gui.UIProfileStats;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;

public class ModuleStats extends AModule<PlayerStats> {
  public static final String NAME = "stats";
  
  private StatsFetcher fetcher;
  
  public ModuleStats() {
    super("stats");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new StatsFetcher(StatsApi.class);
  }
  
  public CompletableFuture<PlayerStats> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileStats.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\profile\ModuleStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */