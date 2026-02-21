package fr.paladium.palamod.modules.luckyblock.profile;

import fr.paladium.palamod.modules.luckyblock.profile.api.LuckyBlockApi;
import fr.paladium.palamod.modules.luckyblock.profile.dto.LuckyBlockData;
import fr.paladium.palamod.modules.luckyblock.profile.fetcher.LuckyBlockFetcher;
import fr.paladium.palamod.modules.luckyblock.profile.gui.UIProfileLuckyBlock;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModuleLuckyBlocks extends AModule<LuckyBlockData> {
  public static final String NAME = "luckyblocks";
  
  private LuckyBlockFetcher fetcher;
  
  public ModuleLuckyBlocks() {
    super("luckyblocks");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.fetcher = new LuckyBlockFetcher(LuckyBlockApi.class);
  }
  
  public CompletableFuture<LuckyBlockData> fetch(String uuid) {
    return this.fetcher.fetchAsync(uuid).thenApply(LuckyBlockData::new);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileLuckyBlock.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\ModuleLuckyBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */