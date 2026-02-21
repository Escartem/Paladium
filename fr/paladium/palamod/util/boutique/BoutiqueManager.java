package fr.paladium.palamod.util.boutique;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.pb.PBManager;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BoutiqueManager {
  public static CompletableFuture<Boolean> addmoneyToPlayer(UUID player, BigDecimal amount, String reason) {
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    PBManager.add(UUIDUtils.toString(player), reason, 0L)
      .thenAccept(value -> future.complete(Boolean.valueOf(true)))
      .exceptionally(error -> {
          error.printStackTrace();
          future.complete(Boolean.valueOf(false));
          return null;
        });
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\boutique\BoutiqueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */