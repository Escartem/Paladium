package fr.paladium.palamod.modules.paladium.common.legendarystone;

import fr.paladium.palamod.modules.paladium.client.profile.gui.UIProfileLegendaryStone;
import fr.paladium.palamod.modules.paladium.common.legendarystone.api.LegendaryStoneApi;
import fr.paladium.palamod.modules.paladium.common.legendarystone.dto.LegendaryStoneData;
import fr.paladium.palamod.modules.paladium.common.legendarystone.fetcher.LegendaryStoneFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModuleLegendaryStone extends AModule<LegendaryStoneData> {
  public static final String NAME = "legendarystone";
  
  private LegendaryStoneFetcher fetcher;
  
  public ModuleLegendaryStone() {
    super("legendarystone");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new LegendaryStoneFetcher(LegendaryStoneApi.class);
  }
  
  public CompletableFuture<LegendaryStoneData> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid).thenApply(LegendaryStoneData::new);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileLegendaryStone.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\legendarystone\ModuleLegendaryStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */