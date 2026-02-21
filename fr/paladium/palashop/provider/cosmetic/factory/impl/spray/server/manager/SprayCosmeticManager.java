package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.manager;

import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto.SprayCosmetic;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;

public class SprayCosmeticManager {
  public static final Object LOCK = new Object();
  
  private static Map<String, SprayCosmetic> cosmetics;
  
  public static void load() {
    long start = System.currentTimeMillis();
    System.out.println("[SprayCosmeticManager] Loading cosmetics...");
    synchronized (LOCK) {
      try {
        cosmetics = new HashMap<>();
        ((Set)SprayCosmeticFactory.getServer().getApi().cosmetics().execute().body()).forEach(cosmetic -> (SprayCosmetic)cosmetics.put(cosmetic.getId(), cosmetic));
        System.out.println("[SprayCosmeticManager] Loaded " + cosmetics.size() + " cosmetics in " + (System.currentTimeMillis() - start) + "ms");
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
    } 
  }
  
  @NonNull
  public static Optional<SprayCosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    synchronized (LOCK) {
      return Optional.ofNullable(cosmetics.get(id));
    } 
  }
  
  @NonNull
  public static Set<SprayCosmetic> getCosmetics() {
    synchronized (LOCK) {
      return new HashSet<>(cosmetics.values());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\manager\SprayCosmeticManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */