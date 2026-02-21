package fr.paladium.palamod.modules.palaboss.profile;

import fr.paladium.palamod.modules.palaboss.client.profile.gui.UIProfileBoss;
import fr.paladium.palamod.modules.palaboss.profile.api.BossApi;
import fr.paladium.palamod.modules.palaboss.profile.dto.PlayerBossData;
import fr.paladium.palamod.modules.palaboss.profile.fetcher.BossFetcher;
import fr.paladium.palamod.modules.palaboss.profile.fetcher.JobBossFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class ModuleBoss extends AModule<PlayerBossData> {
  public static final String NAME = "boss";
  
  private BossFetcher fetcher;
  
  private JobBossFetcher jobFetcher;
  
  public ModuleBoss() {
    super("boss");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new BossFetcher(BossApi.class);
    this.jobFetcher = new JobBossFetcher(BossApi.class);
  }
  
  public CompletableFuture<PlayerBossData> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid).thenCombine(this.jobFetcher.fetchAsync(uuid), PlayerBossData::new);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileBoss.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\ModuleBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */