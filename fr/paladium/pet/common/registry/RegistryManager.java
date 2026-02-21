package fr.paladium.pet.common.registry;

import fr.paladium.pet.common.registry.impl.PetBlockRegistry;
import fr.paladium.pet.common.registry.impl.PetItemRegistry;
import java.util.ArrayList;
import java.util.List;

public class RegistryManager {
  private static RegistryManager instance;
  
  private final List<IRegistry> registries;
  
  public List<IRegistry> getRegistries() {
    return this.registries;
  }
  
  public RegistryManager() {
    instance = this;
    this.registries = new ArrayList<>();
    register((IRegistry)new PetItemRegistry());
    register((IRegistry)new PetBlockRegistry());
  }
  
  public static RegistryManager getInstance() {
    if (instance == null)
      instance = new RegistryManager(); 
    return instance;
  }
  
  public void register(IRegistry registry) {
    if (this.registries.contains(registry))
      throw new IllegalArgumentException("Registry already registered"); 
    this.registries.add(registry);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\registry\RegistryManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */