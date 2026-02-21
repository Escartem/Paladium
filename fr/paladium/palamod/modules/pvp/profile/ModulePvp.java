package fr.paladium.palamod.modules.pvp.profile;

import fr.paladium.palamod.modules.pvp.profile.api.PvpApi;
import fr.paladium.palamod.modules.pvp.profile.dto.PlayerPvpData;
import fr.paladium.palamod.modules.pvp.profile.fetcher.PvpFetcher;
import fr.paladium.palamod.modules.pvp.profile.gui.UIProfilePvp;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModulePvp extends AModule<PlayerPvpData> {
  public static final String NAME = "pvp";
  
  private PvpFetcher fetcher;
  
  public ModulePvp() {
    super("pvp");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new PvpFetcher(PvpApi.class);
  }
  
  public CompletableFuture<PlayerPvpData> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid).thenApply(PlayerPvpData::new);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfilePvp.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\profile\ModulePvp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */