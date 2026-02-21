package fr.paladium.palajobs.core.profile;

import fr.paladium.palajobs.client.profile.gui.UIProfileJobs;
import fr.paladium.palajobs.core.profile.api.JobApi;
import fr.paladium.palajobs.core.profile.dto.PlayerJobData;
import fr.paladium.palajobs.core.profile.dto.PlayerJobRank;
import fr.paladium.palajobs.core.profile.dto.PlayerJobs;
import fr.paladium.palajobs.core.profile.fetcher.JobHistoryFetcher;
import fr.paladium.palajobs.core.profile.fetcher.JobRankFetcher;
import fr.paladium.palajobs.core.profile.fetcher.JobsFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModuleJobs extends AModule<PlayerJobData> {
  private JobsFetcher fetcher;
  
  private JobHistoryFetcher historyFetcher;
  
  private JobRankFetcher rankFetcher;
  
  public JobsFetcher getFetcher() {
    return this.fetcher;
  }
  
  public JobHistoryFetcher getHistoryFetcher() {
    return this.historyFetcher;
  }
  
  public JobRankFetcher getRankFetcher() {
    return this.rankFetcher;
  }
  
  public ModuleJobs() {
    super("jobs");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new JobsFetcher(JobApi.class);
    this.historyFetcher = new JobHistoryFetcher(JobApi.class);
    this.rankFetcher = new JobRankFetcher(JobApi.class);
  }
  
  public CompletableFuture<PlayerJobData> fetch(String uuid) {
    CompletableFuture<PlayerJobData> future = new CompletableFuture<>();
    this.fetcher.fetchAsync(uuid).thenAccept(fetcherResult -> this.historyFetcher.fetchAsync(uuid).thenAccept(()));
    return future;
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileJobs.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\ModuleJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */