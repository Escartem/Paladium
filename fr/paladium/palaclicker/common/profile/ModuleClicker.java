package fr.paladium.palaclicker.common.profile;

import fr.paladium.palaclicker.client.profile.gui.UIProfileClicker;
import fr.paladium.palaclicker.common.profile.api.ClickerApi;
import fr.paladium.palaclicker.common.profile.dto.ProfilePlayerClicker;
import fr.paladium.palaclicker.common.profile.fetcher.ClickerFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;

public class ModuleClicker extends AModule<ProfilePlayerClicker> {
  public static final String NAME = "clicker";
  
  private ClickerFetcher fetcher;
  
  public ClickerFetcher getFetcher() {
    return this.fetcher;
  }
  
  public ModuleClicker() {
    super("clicker");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new ClickerFetcher(ClickerApi.class);
  }
  
  public CompletableFuture<ProfilePlayerClicker> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileClicker.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\profile\ModuleClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */