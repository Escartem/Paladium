package fr.paladium.palavoicechat.server.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.server.api.pojo.PlayerPunishment;
import fr.paladium.palavoicechat.server.api.pojo.PunishmentType;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuteCacheManager {
  private static volatile Cache<UUID, Boolean> MUTE_CACHE = CacheBuilder.newBuilder().expireAfterWrite(30L, TimeUnit.SECONDS).build();
  
  private static MuteCacheManager INSTANCE;
  
  public boolean isPlayerMuted(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return isPlayerMuted(player.func_110124_au());
  }
  
  public boolean isPlayerMuted(@NonNull UUID playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    Boolean value = (Boolean)MUTE_CACHE.getIfPresent(playerUUID);
    if (value == null) {
      checkMutedState(playerUUID);
      return false;
    } 
    return value.booleanValue();
  }
  
  private void checkMutedState(@NonNull final UUID playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    UUID uuid = ForgeEnv.isIDE() ? UUIDUtils.from("427805c1-9a63-443d-8b71-f0bc93b4b82a") : playerUUID;
    Boolean value = (Boolean)MUTE_CACHE.getIfPresent(playerUUID);
    if (value == null) {
      MUTE_CACHE.put(playerUUID, Boolean.valueOf(false));
      if (PalaVoiceChatMod.getServerProxy().getModApi() == null)
        return; 
      PalaVoiceChatMod.getServerProxy().getModApi().getActivePunishment(UUIDUtils.toString(uuid), PunishmentType.MUTE).enqueue(new Callback<PlayerPunishment>() {
            public void onResponse(Call<PlayerPunishment> call, Response<PlayerPunishment> response) {
              if (response.isSuccessful())
                MuteCacheManager.MUTE_CACHE.put(playerUUID, Boolean.valueOf(true)); 
            }
            
            public void onFailure(Call<PlayerPunishment> call, Throwable t) {}
          });
    } 
  }
  
  public static MuteCacheManager getInstance() {
    if (INSTANCE == null)
      INSTANCE = new MuteCacheManager(); 
    return INSTANCE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\manager\MuteCacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */