package fr.paladium.palavoicechat.client.voip.audio;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class TalkCache {
  private static final long TIMEOUT = 500L;
  
  public Map<UUID, Long> getCache() {
    return this.cache;
  }
  
  private final Map<UUID, Long> cache = new ConcurrentHashMap<>();
  
  private final Map<UUID, Float> amplitudeCache = new ConcurrentHashMap<>();
  
  public void updateTalking(UUID player) {
    updateTalking(player, 0L);
  }
  
  public void updateTalking(UUID player, long offset) {
    this.cache.put(player, Long.valueOf(System.currentTimeMillis() - offset));
  }
  
  public void updateTalkingWithAmplitude(UUID player, float amplitude) {
    updateTalkingWithAmplitude(player, amplitude, 0L);
  }
  
  public void updateTalkingWithAmplitude(UUID player, float amplitude, long offset) {
    this.cache.put(player, Long.valueOf(System.currentTimeMillis() - offset));
    this.amplitudeCache.put(player, Float.valueOf(amplitude));
  }
  
  public void updateCache() {
    long time = System.currentTimeMillis();
    List<UUID> toRemove = new ArrayList<>();
    for (Map.Entry<UUID, Long> entry : this.cache.entrySet()) {
      if (time - ((Long)entry.getValue()).longValue() > 500L)
        toRemove.add(entry.getKey()); 
    } 
    for (UUID uuid : toRemove) {
      this.cache.remove(uuid);
      this.amplitudeCache.remove(uuid);
    } 
  }
  
  public boolean isTalking(EntityPlayer playerEntity) {
    updateCache();
    if (playerEntity.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au()) && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      double threshold = Utils.dbToPerc(PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceActivationThreshold());
      float amplitude = getAmplitude(playerEntity.func_110124_au());
      return (amplitude >= threshold);
    } 
    return this.cache.containsKey(playerEntity.func_110124_au());
  }
  
  public boolean isTalking(UUID uuid) {
    updateCache();
    if (uuid.equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au()) && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      double threshold = Utils.dbToPerc(PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceActivationThreshold());
      float amplitude = getAmplitude(uuid);
      return (amplitude >= threshold);
    } 
    return this.cache.containsKey(uuid);
  }
  
  public float getAmplitude(EntityPlayer playerEntity) {
    updateCache();
    return getAmplitude(playerEntity.func_110124_au());
  }
  
  public float getAmplitude(UUID uuid) {
    updateCache();
    return ((Float)this.amplitudeCache.getOrDefault(uuid, Float.valueOf(0.0F))).floatValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\audio\TalkCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */