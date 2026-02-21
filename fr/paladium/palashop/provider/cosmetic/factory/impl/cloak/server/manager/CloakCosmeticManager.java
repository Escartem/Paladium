package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.manager;

import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.dto.CloakCosmetic;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;

public class CloakCosmeticManager {
  public static final Object LOCK = new Object();
  
  private static Map<String, CloakCosmetic> cosmetics;
  
  public static void load() {
    long start = System.currentTimeMillis();
    System.out.println("[CloakCosmeticManager] Loading cosmetics...");
    synchronized (LOCK) {
      try {
        cosmetics = new HashMap<>();
        ((Set)CloakCosmeticFactory.getServer().getApi().cosmetics().execute().body()).forEach(cosmetic -> (CloakCosmetic)cosmetics.put(cosmetic.getId(), cosmetic));
        System.out.println("[CloakCosmeticManager] Loaded " + cosmetics.size() + " cosmetics in " + (System.currentTimeMillis() - start) + "ms");
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
    } 
  }
  
  @NonNull
  public static Optional<CloakCosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    synchronized (LOCK) {
      return Optional.ofNullable(cosmetics.get(id));
    } 
  }
  
  @NonNull
  public static Set<CloakCosmetic> getCosmetics() {
    synchronized (LOCK) {
      return new HashSet<>(cosmetics.values());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\server\manager\CloakCosmeticManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */