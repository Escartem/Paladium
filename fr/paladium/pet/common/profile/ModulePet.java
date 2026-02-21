package fr.paladium.pet.common.profile;

import fr.paladium.pet.client.profile.gui.UIProfilePet;
import fr.paladium.pet.common.profile.api.PetApi;
import fr.paladium.pet.common.profile.dto.Pet;
import fr.paladium.pet.common.profile.fetcher.PetFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.concurrent.CompletableFuture;

public class ModulePet extends AModule<Pet> {
  public static final String NAME = "pet";
  
  private PetFetcher mountFetcher;
  
  public ModulePet() {
    super("pet");
  }
  
  public boolean hasVisibilitySettings() {
    return false;
  }
  
  public void registerFetchers() {
    this.mountFetcher = new PetFetcher(PetApi.class);
  }
  
  public CompletableFuture<Pet> fetch(String uuid) {
    return this.mountFetcher.fetchAsync(uuid);
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfilePet.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\profile\ModulePet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */